package com.guilherme.desafio.concrete.desafiojava.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.desafio.concrete.desafiojava.model.User;
import com.guilherme.desafio.concrete.desafiojava.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService{

	
	@Autowired
	UserRepository repository;
	
	@Override
	public User createUser(User user) {
		return this.repository.save(user);
	}

	@Override
	public List<User> getAll() {
		return this.repository.findAll();
	}
	

}