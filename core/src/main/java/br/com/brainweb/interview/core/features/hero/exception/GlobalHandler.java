package br.com.brainweb.interview.core.features.hero.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.brainweb.interview.core.features.hero.dto.ResponseDto;

@ControllerAdvice
public class GlobalHandler extends ResponseEntityExceptionHandler {
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		ResponseDto response = new ResponseDto();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            response.addMessage(error.getDefaultMessage());
        }

        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            response.addMessage(error.getDefaultMessage());
        }

        return super.handleExceptionInternal(ex, response, headers, status,request);
    }
	
	@ExceptionHandler({BusinessException.class})
	protected ResponseEntity<Object> handleMethodArgumentNotValid(BusinessException ex, WebRequest request) {
		ResponseDto response = new ResponseDto();

        response.addMessage(ex.getMessage());
        HttpStatus httpStatus = ex.getHttpStatus();	
        
        return super.handleExceptionInternal(ex, response, new HttpHeaders(), httpStatus, request);
    }
}
