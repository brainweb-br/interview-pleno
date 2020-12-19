package br.com.brainweb.interview.core.exceptionhandler;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Getter
public class ExceptionController  extends RuntimeException {

    @Autowired
    private final HttpStatus status;

    public ExceptionController(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public static ExceptionController notFound(String message) {
        return new ExceptionController(HttpStatus.NOT_FOUND, message);
    }
    public static ExceptionController conflict(String message) {
        return new ExceptionController(HttpStatus.CONFLICT, message);
    }
}
