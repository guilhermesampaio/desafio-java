package com.guilherme.desafio.concrete.desafiojava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.desafio.concrete.desafiojava.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {

	Boolean existsByEmail(String email);
	
	Optional<User> getByEmailAndPassword(String email, String password);
}
