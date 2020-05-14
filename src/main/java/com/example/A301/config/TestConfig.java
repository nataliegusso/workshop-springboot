package com.example.A301.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.A301.entities.Order;
import com.example.A301.entities.User;
import com.example.A301.entities.enums.OrderStatus;
import com.example.A301.repositories.OrderRepository;
import com.example.A301.repositories.UserRepository;

@Configuration  //Classe específica de configuração
@Profile("test")  //Específica para teste, tem que ser o nome declarado em application.properties
public class TestConfig implements CommandLineRunner {  //Vai popular o BD (database seeding), mas tem que ter acesso ao BD que está na classe UserRepository (precisa injeção de independência)
	//CommandLineRunner: executa a classe qdo o programa é iniciado

	@Autowired  //dependência do UserRepository, mas deve ser fraca, desaclopada. P framework é automático, é só declarar @Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public void run(String... args) throws Exception {  //tudo que colocar aqui dentro será executado qdo o programa for iniciado
	User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
	User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

	Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);  //z = UTC
	Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
	Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
	
	userRepository.saveAll(Arrays.asList(u1, u2));	//salva no BD
	orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	}	
}