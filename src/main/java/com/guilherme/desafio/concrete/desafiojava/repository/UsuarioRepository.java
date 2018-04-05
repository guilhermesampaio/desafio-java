package com.guilherme.desafio.concrete.desafiojava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.guilherme.desafio.concrete.desafiojava.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
