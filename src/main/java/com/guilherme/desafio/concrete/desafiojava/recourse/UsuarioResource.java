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

import com.guilherme.desafio.concrete.desafiojava.model.Usuario;
import com.guilherme.desafio.concrete.desafiojava.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository repository;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		try {
			Usuario userCreated = repository.save(usuario);
			return new ResponseEntity<Usuario>(userCreated, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<ErrorResponse>(new ErrorResponse(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping
	public List<Usuario> getAll() {
		return repository.findAll();
	}
}
