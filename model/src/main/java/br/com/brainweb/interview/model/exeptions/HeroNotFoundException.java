package br.com.brainweb.interview.model.exeptions;

public class HeroNotFoundException extends RuntimeException {

    public HeroNotFoundException(int id) {
        super("hero not found exception " + id);

    }
}
