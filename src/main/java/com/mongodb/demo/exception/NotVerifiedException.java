package com.mongodb.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotVerifiedException extends RuntimeException {
	/**
	 *
	 */
	@Serial
	private static final long serialVersionUID = 1L;

	public NotVerifiedException(String message) {
		super(message);
	}

	public NotVerifiedException(String message, Throwable cause) {
		super(message, cause);
	}
}
