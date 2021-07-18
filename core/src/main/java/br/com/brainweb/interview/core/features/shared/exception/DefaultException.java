package br.com.brainweb.interview.core.features.shared.exception;

public class DefaultException extends RuntimeException {

    private final int code;

    public DefaultException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }


}
