package com.guilherme.desafio.concrete.desafiojava.application;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.desafio.concrete.desafiojava.exception.InvalidCredentialsException;
import com.guilherme.desafio.concrete.desafiojava.model.Login;
import com.guilherme.desafio.concrete.desafiojava.model.User;
import com.guilherme.desafio.concrete.desafiojava.repository.IUserRepository;

@Service
public class AuthService implements IAuthService {
	
	@Autowired
	IUserRepository repository;
		
	public User login(Login login) throws InvalidCredentialsException {
		
		Optional<User> user = this.repository.getByEmailAndPassword(login.getEmail(), login.getPassword());
		
		if(!user.isPresent()) {
			throw new InvalidCredentialsException("Usuário/e ou senha inválidos");			
		}
		
		User existingUser = user.get();		
		String token = UUID.randomUUID().toString();
		existingUser.setToken(token);
		existingUser.setLastLogin(LocalDateTime.now());	
		
		return this.repository.save(existingUser);
	}
}
