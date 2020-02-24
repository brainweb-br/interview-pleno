package br.com.brainweb.interview.model.exeptions;

public class NameAlreadyExistsException extends RuntimeException {

    public NameAlreadyExistsException(String name) {
        super("Hero with the name " + name + " already exists");

    }
}
