package br.com.brainweb.interview.core.features.hero.exception;

public class ConflictError extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ConflictError(String message) {
        super(message);
    }
}
