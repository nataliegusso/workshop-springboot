package com.example.A301.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.A301.entities.User;

@RestController						//implementado por um controlador rest
@RequestMapping(value = "/users")	//nome p recurso
public class UserResource {

	@GetMapping		//método que responde à requisição
	public ResponseEntity<User> findAll() {	//teste para ver se funciona  //ResponseEntity<User>: resposta da requisição do tipo classe user
		User u = new User(1L, "Mari", "maria@gmail.com", "99999999", "12345");
		return ResponseEntity.ok().body(u);	//retornar resposta de sucesso  (ResponseEntity.ok) com o corpo da resposta o u (body(u))
		//digita localhost:8080/users/ e tem que aparecer na tela
	}
}