package com.pdk.orderservice.Service;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pdk.orderservice.Dto.OrderLineItemsDto;
import com.pdk.orderservice.Dto.OrderRequest;
import com.pdk.orderservice.Model.Order;
import com.pdk.orderservice.Model.OrderLineItems;
import com.pdk.orderservice.Repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();

        String OrderNumber = UUID.randomUUID().toString();

        order.setOrderNumber(OrderNumber);

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList().stream().map(this::mapToDto)
                .toList();

        order.setOrderLineItems(orderLineItems);

        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();

        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());

        return orderLineItems;
    }
}
