package br.com.brainweb.interview.core.exceptions;

public class DuplicatedHeroNameException extends RuntimeException {
    public DuplicatedHeroNameException(String msg) {
        super(msg);
    }
}
