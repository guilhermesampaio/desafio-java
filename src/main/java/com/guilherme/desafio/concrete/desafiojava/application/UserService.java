package com.guilherme.desafio.concrete.desafiojava.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.desafio.concrete.desafiojava.exception.EmailAlreadyInUseException;
import com.guilherme.desafio.concrete.desafiojava.model.User;
import com.guilherme.desafio.concrete.desafiojava.repository.IUserRepository;

@Service
public class UserService implements IUserService{
	
	@Autowired
	IUserRepository repository;
	
	public User createUser(User user) throws EmailAlreadyInUseException {
		
		if(this.repository.existsByEmail(user.getEmail()))
			throw new EmailAlreadyInUseException("E-mail já existente");
		
		return this.repository.save(user);
	}
	
	public List<User> getAll() {
		return this.repository.findAll();
	}

	public Boolean UserExists(Long id) {		
		return this.repository.exists(id);
	}

	
	public User get(Long id) {		
		return this.repository.findOne(id);
	}
}