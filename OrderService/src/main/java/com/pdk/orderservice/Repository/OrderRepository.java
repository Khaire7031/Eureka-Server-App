package com.pdk.orderservice.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pdk.orderservice.Model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
