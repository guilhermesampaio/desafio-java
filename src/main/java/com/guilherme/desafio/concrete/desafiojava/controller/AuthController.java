package com.guilherme.desafio.concrete.desafiojava.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.desafio.concrete.desafiojava.application.IAuthService;
import com.guilherme.desafio.concrete.desafiojava.exception.InvalidCredentialsException;
import com.guilherme.desafio.concrete.desafiojava.model.Login;
import com.guilherme.desafio.concrete.desafiojava.model.User;

@RestController
public class AuthController {

	@Autowired
	IAuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Login login) {

		try {
			User user = this.authService.login(login);
			return ResponseEntity.ok(user);
		} catch (InvalidCredentialsException e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.UNAUTHORIZED);
		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}

}
