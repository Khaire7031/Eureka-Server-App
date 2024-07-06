package com.pdk.orderservice.Service;

import java.util.UUID;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.pdk.orderservice.Dto.InventoryResponse;
import com.pdk.orderservice.Dto.OrderLineItemsDto;
import com.pdk.orderservice.Dto.OrderRequest;
import com.pdk.orderservice.Model.Order;
import com.pdk.orderservice.Model.OrderLineItems;
import com.pdk.orderservice.Repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();

        String OrderNumber = UUID.randomUUID().toString();

        order.setOrderNumber(OrderNumber);

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto)
                .toList();

        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems().stream().map(orderLineItem -> orderLineItem.getSkuCode())
                .toList();
        // Call Inventory Service and place order if product is in Stock
        InventoryResponse[] inventoryResponsesArray = webClient.get()
                .uri("http://localhost:3003/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductInStock = Arrays.stream(inventoryResponsesArray)
                .allMatch(inventoryResponses -> inventoryResponses.getIsInStock());
        if (allProductInStock) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Product is-not in stock, Please try again.");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
