package br.com.brainweb.interview.core.features.hero.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.time.ZoneId;

@ControllerAdvice
public class ApiHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConflictError.class)
    public ResponseEntity<Object> conflictError(ConflictError ex, WebRequest request) {
        var status = HttpStatus.CONFLICT;

        var error = new ErrorResponse();
        error.setStatus(status.value());
        error.setTitle(ex.getMessage());
        error.setDate(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")).toString());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NoContentError.class)
    public ResponseEntity<Object> noContentError(NoContentError ex, WebRequest request) {
        var status = HttpStatus.NO_CONTENT;

        var error = new ErrorResponse();
        error.setStatus(status.value());
        error.setTitle(ex.getMessage());
        error.setDate(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")).toString());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(NotFoundEntity.class)
    public ResponseEntity<Object> notFoundEntity(NotFoundEntity ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;

        var error = new ErrorResponse();
        error.setStatus(status.value());
        error.setTitle(ex.getMessage());
        error.setDate(OffsetDateTime.now(ZoneId.of("America/Sao_Paulo")).toString());

        return handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }
}
