package com.example.A301.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.A301.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}