package com.example.A301.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.A301.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}