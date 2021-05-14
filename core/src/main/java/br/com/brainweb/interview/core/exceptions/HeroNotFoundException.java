package br.com.brainweb.interview.core.exceptions;

public class HeroNotFoundException extends RuntimeException {
    public HeroNotFoundException(String msg){
        super(msg);
    }
}
