package com.guilherme.desafio.concrete.desafiojava.recourse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.guilherme.desafio.concrete.desafiojava.application.UserService;
import com.guilherme.desafio.concrete.desafiojava.model.User;

@RestController
@RequestMapping("/usuario")
public class UserController{

	@Autowired
	private UserService userService;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody User user) throws Exception {
		try {
			User userCreated = userService.createUser(user);
			return new ResponseEntity<User>(userCreated, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public List<User> getAll() {
		return userService.getAll();
	}
}
