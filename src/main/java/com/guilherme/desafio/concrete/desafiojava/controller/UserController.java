package com.guilherme.desafio.concrete.desafiojava.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.guilherme.desafio.concrete.desafiojava.application.IUserService;
import com.guilherme.desafio.concrete.desafiojava.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private IUserService userService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) {
		try {
			User userCreated = this.userService.createUser(user);
			return new ResponseEntity<User>(userCreated, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/profile/{id}")
	public ResponseEntity<?> profile(@RequestHeader("Authorization") String requestToken, @PathVariable Long id) {
		try {
			User user = this.userService.get(id);
			
			if(user == null) {				
				return Unauthorized("Não autorizado");
			}
			
			if(!user.getToken().equals(requestToken)) {				
				return Unauthorized("Não autorizado");
			}
			
			Date tokenExpiration = new Date();
			tokenExpiration.setMinutes(-1);
			
			if(tokenExpiration.compareTo(user.getLastLogin()) == 1) {				
				return Unauthorized("Sessão inválida");
			}

			return ResponseEntity.ok(user);
		} catch (Exception e) {
			return InternalServerError(e.getMessage());
		}
		
	}

	@GetMapping
	public List<User> getAll() {
		return userService.getAll();
	}
	
	private ResponseEntity<ErrorResponse> Unauthorized(String message){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(message), HttpStatus.UNAUTHORIZED);
	}
	
	private ResponseEntity<ErrorResponse> InternalServerError(String message){
		return new ResponseEntity<ErrorResponse>(new ErrorResponse(message), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
