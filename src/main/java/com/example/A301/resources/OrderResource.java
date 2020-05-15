package com.example.A301.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.A301.entities.Order;
import com.example.A301.services.OrderService;

@RestController						//implementado por um controlador rest
@RequestMapping(value = "/orders")	//nome p recurso
public class OrderResource {

	@Autowired
	private OrderService service;  //injeção de dependência p o OrderService que cria o método findAll
	
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {
		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {  //Por causa do parâmentro value = id, p Spring aceitar, tenho que declarar @PathVariable
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}