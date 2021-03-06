package com.example.A301.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.A301.entities.Category;
import com.example.A301.entities.Order;
import com.example.A301.entities.OrderItem;
import com.example.A301.entities.Payment;
import com.example.A301.entities.Product;
import com.example.A301.entities.User;
import com.example.A301.entities.enums.OrderStatus;
import com.example.A301.repositories.CategoryRepository;
import com.example.A301.repositories.OrderItemRepository;
import com.example.A301.repositories.OrderRepository;
import com.example.A301.repositories.ProductRepository;
import com.example.A301.repositories.UserRepository;

@Configuration  //Classe específica de configuração
@Profile("test")  //Específica para teste, tem que ser o nome declarado em application.properties
public class TestConfig implements CommandLineRunner {  //Vai popular o BD (database seeding), mas tem que ter acesso ao BD que está na classe UserRepository (precisa injeção de independência)
	//CommandLineRunner: executa a classe qdo o programa é iniciado

	@Autowired  //dependência do UserRepository, mas deve ser fraca, desaclopada. P framework é automático, é só declarar @Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {  //tudo que colocar aqui dentro será executado qdo o programa for iniciado

	Category cat1 = new Category(null, "Electronics"); 
	Category cat2 = new Category(null, "Books"); 
	Category cat3 = new Category(null, "Computers"); 

	Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, ""); 
	Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
	Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
	Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
	Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, ""); 

	categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
	productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
		
	//Orientada a objetos, mas no BD é relacional
	p1.getCategories().add(cat2);  //Associar produtos com categorias
	p2.getCategories().add(cat1);
	p2.getCategories().add(cat3);
	p3.getCategories().add(cat3);
	p4.getCategories().add(cat3);
	p5.getCategories().add(cat2);
	
	productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));  //Salvar no jpa

	User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
	User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

	Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);  //z = UTC
	Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
	Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);
	
	userRepository.saveAll(Arrays.asList(u1, u2));	//salva no BD
	orderRepository.saveAll(Arrays.asList(o1, o2, o3));
	
	OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice()); 
	OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice()); 
	OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice()); 
	OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());
	
	orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));
	
	Payment pay1 = new Payment(null, Instant.parse("2019-06-20T21:53:07Z"), o1);
	o1.setPayment(pay1);//P salvar não é o repositroy do próprio objeto, é uma associação de mão dupla com o pedido
	orderRepository.save(o1);
	}	
}