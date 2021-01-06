package br.com.brainweb.interview.core.features.hero.exception;

public class NoContentError extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public NoContentError(String message) {
        super(message);
    }
}
