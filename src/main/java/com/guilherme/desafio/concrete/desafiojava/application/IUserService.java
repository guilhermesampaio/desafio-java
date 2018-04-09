package com.guilherme.desafio.concrete.desafiojava.application;

import java.util.List;

import com.guilherme.desafio.concrete.desafiojava.exception.EmailAlreadyInUseException;
import com.guilherme.desafio.concrete.desafiojava.model.User;

public interface IUserService {
	
	User createUser(User user) throws EmailAlreadyInUseException;
	
	List<User> getAll();

	Boolean UserExists(Long id);

	User get(Long id);
	
	
}
