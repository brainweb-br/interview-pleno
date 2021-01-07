package br.com.brainweb.interview.core.features.hero.exception;

public class BadRequestError extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BadRequestError(String message) {
        super(message);
    }
}
