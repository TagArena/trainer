package com.tagarena.trainer.service.model.exception;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TrainerNotFoundException extends RuntimeException {

	public TrainerNotFoundException(String message) {
		super(message);
	}
}
