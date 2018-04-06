package com.guilherme.desafio.concrete.desafiojava.application;

import java.util.List;

import com.guilherme.desafio.concrete.desafiojava.model.User;

public interface UserService{
	
	User createUser(User user);
	
	List<User> getAll();
	
}
