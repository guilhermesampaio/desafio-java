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

import com.guilherme.desafio.concrete.desafiojava.model.User;
import com.guilherme.desafio.concrete.desafiojava.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UserController {

	@Autowired
	private UsuarioRepository repository;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody User usuario) throws Exception {
		try {
			User userCreated = repository.save(usuario);
			return new ResponseEntity<User>(userCreated, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping
	public List<User> getAll() {
		return repository.findAll();
	}
}
