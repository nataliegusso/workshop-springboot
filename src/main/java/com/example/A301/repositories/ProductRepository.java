package com.example.A301.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.A301.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}