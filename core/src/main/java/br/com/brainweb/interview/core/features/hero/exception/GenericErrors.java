package br.com.brainweb.interview.core.features.hero.exception;

public class GenericErrors extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GenericErrors(String message) {
        super(message);
    }
}
