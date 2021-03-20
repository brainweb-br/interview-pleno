package br.com.brainweb.interview.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final long serialVersionUID = -3759369818391219130L;

    public NotFoundException(String message) {
        super(message);
    }

}
