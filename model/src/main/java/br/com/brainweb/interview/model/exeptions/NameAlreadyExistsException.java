package br.com.brainweb.interview.model.exeptions;

public class NameAlreadyExistsException extends RuntimeException {

    public NameAlreadyExistsException(String name) {
        super(String.format("Hero with the name %s already exists", name));

    }
}
