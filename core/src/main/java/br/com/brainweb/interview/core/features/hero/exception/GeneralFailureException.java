package br.com.brainweb.interview.core.features.hero.exception;

public class GeneralFailureException extends RuntimeException {
	
	private String message;
	
	public GeneralFailureException(String message) {
		this.message = message;
	}
}
