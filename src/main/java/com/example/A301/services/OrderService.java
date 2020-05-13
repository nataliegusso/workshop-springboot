package com.example.A301.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.A301.entities.Order;
import com.example.A301.repositories.OrderRepository;

@Service
public class OrderService {  

	@Autowired	//Injeção de dependência
	private OrderRepository repository;

	public List<Order> findAll() {
		return repository.findAll();
	}

	public Order findById(Long id) {
		Optional<Order> obj = repository.findById(id);
		return obj.get();
	}
}