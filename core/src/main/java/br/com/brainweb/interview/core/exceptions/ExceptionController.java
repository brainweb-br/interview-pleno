package br.com.brainweb.interview.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = {DuplicatedHeroNameException.class})
    public ResponseEntity<ErrorMessage> duplicatedHeroNameException(DuplicatedHeroNameException ex, WebRequest request) {
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HeroNotFoundException.class})
    public ResponseEntity<ErrorMessage> heroNotFoundException(HeroNotFoundException ex, WebRequest request) {
        return new ResponseEntity<ErrorMessage>(new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false)), HttpStatus.NOT_FOUND);
    }

}
