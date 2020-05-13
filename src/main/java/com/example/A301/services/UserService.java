package com.example.A301.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.A301.entities.User;
import com.example.A301.repositories.UserRepository;



//@Component  //Annotation que registra a classe como componente do Spring, podendo fazer a injeção automática.
@Service  //é a mesma anotação acima, mas específica p service. Tem tbm a @Repository. Olhe lá no UserRepository.
public class UserService {  

	@Autowired	//Injeção de dependência
	private UserRepository repository;	//p criar os métodos de busca, fazer dependência p o UserRepository

	public List<User> findAll() {  //Método p retornar todos os usuários do BD
		return repository.findAll();  //repassa a chamada p o findAll  //Atualizar no UserResource que está manual
	}

	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);  //findById retorna um Optional  //https://blog.caelum.com.br/chega-de-nullpointerexception-trabalhe-com-o-java-util-optional
		return obj.get();
	}
}