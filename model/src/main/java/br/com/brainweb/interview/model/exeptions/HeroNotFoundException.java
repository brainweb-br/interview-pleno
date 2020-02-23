package br.com.brainweb.interview.model.exeptions;

public class HeroNotFoundException extends RuntimeException {

    public HeroNotFoundException(String uuid) {
        super("Hero not found exception " + uuid);

    }
}
