package br.com.brainweb.interview.core.features.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = -4438072051802392231L;

	public NotFoundException() {

	}

	public NotFoundException(String msg) {
		super(msg);
	}

	public NotFoundException(Exception e) {
		super(e);
	}

}