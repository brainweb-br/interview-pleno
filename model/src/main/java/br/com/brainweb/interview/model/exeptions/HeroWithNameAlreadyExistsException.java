package br.com.brainweb.interview.model.exeptions;

public class HeroWithNameAlreadyExistsException extends RuntimeException {

    public HeroWithNameAlreadyExistsException(String name) {
        super("Hero with the name " + name + " already exists");

    }
}
