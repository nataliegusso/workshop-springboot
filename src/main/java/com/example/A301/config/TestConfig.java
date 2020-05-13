package com.example.A301.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.A301.entities.User;
import com.example.A301.repositories.UserRepository;

@Configuration  //Classe específica de configuração
@Profile("test")  //Específica para teste, tem que ser o nome declarado em application.properties
public class TestConfig implements CommandLineRunner {  //Vai popular o BD (database seeding), mas tem que ter acesso ao BD que está na classe UserRepository (precisa injeção de independência)
	//CommandLineRunner: executa a classe qdo o programa é iniciado

	@Autowired  //dependência do UserRepository, mas deve ser fraca, desaclopada. P framework é automático, é só declarar @Autowired
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {  //tudo que colocar aqui dentro será executado qdo o programa for iniciado
	User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
	User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

	userRepository.saveAll(Arrays.asList(u1, u2));	//salva no BD
	}	
}