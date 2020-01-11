package br.com.brainweb.interview.exceptions;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.ResponseEntity.status;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class Exceptions {

    @ExceptionHandler(value = {Exception.class})
    ResponseEntity<String> handleGeneralException(Exception e) {
        log.error("Uncaught exception, message={}", e.getMessage(), e);
        return status(INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(value = {IllegalArgumentException.class, MethodArgumentTypeMismatchException.class,NotFoundException.class})
    ResponseEntity<String> handleInvalidRequest(Exception e) {
        return status(BAD_REQUEST).body(e.getMessage());
    }


}
