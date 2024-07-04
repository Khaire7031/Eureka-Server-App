package com.pdk.orderservice.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pdk.orderservice.Dto.OrderRequest;
import com.pdk.orderservice.Service.OrderService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    @Autowired
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderRequest orderRequest) {
        System.out.println("=========> : " + orderRequest);
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }

}
