package br.com.brainweb.interview.core.features.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DataValidationException extends RuntimeException {

	private static final long serialVersionUID = 9030534970488504465L;

	public DataValidationException(Exception e) {
		super(e);
	}

	public DataValidationException(String msg) {
		super(msg);
	}

	public DataValidationException() {

	}


}
