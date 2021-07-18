package br.com.brainweb.interview.core.features.hero.exception;

import br.com.brainweb.interview.core.features.shared.exception.DefaultException;

public class HeroNotFoundException extends DefaultException {

    public HeroNotFoundException() {
        super("No heroes found", 404);
    }
}
