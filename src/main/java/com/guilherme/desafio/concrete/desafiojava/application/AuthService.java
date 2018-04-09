package com.guilherme.desafio.concrete.desafiojava.application;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guilherme.desafio.concrete.desafiojava.exception.InvalidCredentialsException;
import com.guilherme.desafio.concrete.desafiojava.exception.InvalidSessionException;
import com.guilherme.desafio.concrete.desafiojava.exception.UnauthorizedException;
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
		existingUser.setLastLogin(new Date());	
		
		return this.repository.save(existingUser);
	}

	@Override
	public boolean validateToken(User user, String requestToken) throws UnauthorizedException, InvalidSessionException {
				
		if(!user.getToken().equals(requestToken)) {
			throw new UnauthorizedException("Usuário não autorizado.");
		}
		
		Date sessionTimeout = new Date();
		sessionTimeout.setMinutes(-30);
		
		if(sessionTimeout.compareTo(user.getLastLogin()) == -1) {
			throw new InvalidSessionException("Sessão inválida.");
		}
		
		return true;
	}

}
