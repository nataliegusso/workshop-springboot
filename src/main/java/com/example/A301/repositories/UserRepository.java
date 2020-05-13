package com.example.A301.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.A301.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {  //Instancia o Repository que tem várias funções prontas //Tipo da entidade: User, tipo do ID: Long

} 