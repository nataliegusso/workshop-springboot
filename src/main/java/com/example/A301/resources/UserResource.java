package com.example.A301.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.A301.entities.User;
import com.example.A301.services.UserService;

@RestController						//implementado por um controlador rest
@RequestMapping(value = "/users")	//nome p recurso
public class UserResource {

	@Autowired
	private UserService service;  //injeção de dependência p o UserService que cria o método findAll
	
/*	@GetMapping		//método que responde à requisição
	public ResponseEntity<User> findAll() {	//teste para ver se funciona  //ResponseEntity<User>: resposta da requisição do tipo classe user
		User u = new User(1L, "Mari", "maria@gmail.com", "99999999", "12345");  //Manual é só no início p testar
		return ResponseEntity.ok().body(u);	//retornar resposta de sucesso  (ResponseEntity.ok) com o corpo da resposta o u (body(u))
		//digita localhost:8080/users/ e tem que aparecer na tela
*/		
	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {  //Por causa do parâmentro value = id, p Spring aceitar, tenho que declarar @PathVariable
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping  //Qdo insere, usa o @PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj){  //Retorna User e recebe User  //@RequestBody garante a deserialização p objeto (transforma bit em objeto Java)
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId()).toUri();  //Texto personalizado p mensagem 201
		//return ResponseEntity.ok().body(obj); //Texto ok
		return ResponseEntity.created(uri).body(obj);
	}
}

//Componet Registration: qdo tem um obj que poderá ser injetado pelo mecanismo de injeção de independência do Spring, 
//a classe do obj tem que estar registrada no mecanismo de injeção de independência. Com todo framework é assim.
//O UserResource depende do UserService, que é injetado automaticamento pelo @Autowired. Mas para funcionar, 
//a classe UserService tem que estar registrada como um componente do String através de annotations. 
//Olhe lá na classe UserService.