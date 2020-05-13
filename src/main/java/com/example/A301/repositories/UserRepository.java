package com.example.A301.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.A301.entities.User;

@Repository //Não precisa desta annotation pq o extends já está registrando o UserRepository como componente do Spring
public interface UserRepository extends JpaRepository<User, Long> {  //Instancia o Repository que tem várias funções prontas //Tipo da entidade: User, tipo do ID: Long

}