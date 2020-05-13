package com.example.A301.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.A301.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}