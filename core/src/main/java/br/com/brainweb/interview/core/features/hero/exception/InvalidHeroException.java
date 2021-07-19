package br.com.brainweb.interview.core.features.hero.exception;

import br.com.brainweb.interview.core.features.shared.exception.DefaultException;

public class InvalidHeroException extends DefaultException {
    public InvalidHeroException(String message) {
        super(message, 400);
    }
}
