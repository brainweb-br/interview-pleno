package br.com.brainweb.interview.core.configuration;

import br.com.brainweb.interview.core.features.shared.exception.DefaultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionConfiguration {

    @ExceptionHandler(value = DefaultException.class)
    public ResponseEntity<Object> exception(DefaultException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.valueOf(ex.getCode()));
    }

}
