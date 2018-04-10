package com.guilherme.desafio.concrete.desafiojava.application;

import com.guilherme.desafio.concrete.desafiojava.exception.InvalidCredentialsException;
import com.guilherme.desafio.concrete.desafiojava.model.Login;
import com.guilherme.desafio.concrete.desafiojava.model.User;

public interface IAuthService {
	
	User login(Login login) throws InvalidCredentialsException;
	
}
