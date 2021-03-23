package br.com.brainweb.interview.core.features.hero.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class GeneralFailureException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus httpStatus;
	
	public static GeneralFailureException create(HttpStatus httpStatus, String message) {
		GeneralFailureException generalException = new GeneralFailureException();
		generalException.message = message;
		generalException.httpStatus = httpStatus;
		return generalException;
	}
	
	public GeneralFailureException(String message) {
		this.message = message;
	}
	
	public GeneralFailureException() {
	}
}
