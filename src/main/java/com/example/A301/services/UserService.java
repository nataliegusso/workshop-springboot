package com.example.A301.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.A301.entities.User;
import com.example.A301.repositories.UserRepository;
import com.example.A301.services.exceptions.DatabaseException;
import com.example.A301.services.exceptions.ResourceNotFoundException;



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
		//return obj.get(); //Gera exceção 500. Quero a personalizada
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User insert(User obj) {
		return repository.save(obj);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);			
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public User update(Long id, User obj) {
		User entity = repository.getOne(id);  //getOne: instancia um usuário, mas não vai no BD, só é monitorado no JPA p uma operação posterior. É mais eficiente.
		updateData(entity, obj);
		return repository.save(entity);
	}

	private void updateData(User entity, User obj) {
		entity.setName(obj.getName());
		entity.setEmail(obj.getEmail());
		entity.setPhone(obj.getPhone());
	}
}