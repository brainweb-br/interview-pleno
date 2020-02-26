package br.com.brainweb.interview.model.exeptions;

public class HeroNotFoundException extends RuntimeException {

    public HeroNotFoundException(String uuid) {
        super(String.format("Hero with id %s does not exists ", uuid));
    }
}
