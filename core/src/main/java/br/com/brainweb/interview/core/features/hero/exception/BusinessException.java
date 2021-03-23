package br.com.brainweb.interview.core.features.hero.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus httpStatus;
	
	public static BusinessException create(HttpStatus httpStatus, String message) {
		BusinessException businessException = new BusinessException();
		businessException.message = message;
		businessException.httpStatus = httpStatus;
		return businessException;
	}
}
