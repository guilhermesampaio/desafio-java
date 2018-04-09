package com.guilherme.desafio.concrete.desafiojava.exception;

public class EmailAlreadyInUseException extends Exception {
	
	private static final long serialVersionUID = -1213338076579748080L;

	public EmailAlreadyInUseException(String message) {
		super(message);
	}
}
