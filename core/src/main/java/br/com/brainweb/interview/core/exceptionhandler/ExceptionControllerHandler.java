package br.com.brainweb.interview.core.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    public static final String GENERIC_MSG_ERROR
            = "Senhor Wayne do céu, as coisas aqui não saíram como o esperado. Tente novamente e se "
            + "o problema persistir, nós, seus pupilos iremos entrar em ação!";

    @ExceptionHandler(ExceptionController.class)
    public ResponseEntity<?> handleControllerException(ExceptionController exception, WebRequest request) {
        return ResponseEntity.status(exception.getStatus()).body(Map.of(
                "mensagem", exception.getLocalizedMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ProblemType problemType = ProblemType.SYSTEM_ERROR;
        String detail = GENERIC_MSG_ERROR;

        ex.printStackTrace();

        Problem problem = Problem.builder()
                .title(problemType.getTitle())
                .status(status.value())
                .type(problemType.getUri())
                .timestamp(OffsetDateTime.now())
                .userMessage(detail)
                .build();

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (body == null) {
            if (body == null) {
                body = Problem.builder()
                        .timestamp(OffsetDateTime.now())
                        .title(status.getReasonPhrase())
                        .status(status.value())
                        .userMessage(GENERIC_MSG_ERROR)
                        .build();
            }
        } else if (body instanceof String) {
            body = Problem.builder()
                    .timestamp(OffsetDateTime.now())
                    .title((String) body)
                    .status(status.value())
                    .userMessage(GENERIC_MSG_ERROR)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        ProblemType problemType = ProblemType.INVALID_DATA;
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

        BindingResult bindingResult = ex.getBindingResult();

        List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());

                    String name = objectError.getObjectName();

                    if (objectError instanceof FieldError) {
                        name = ((FieldError) objectError).getField();
                    }

                    return Problem.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());

        Problem problem = Problem.builder()
                .title(problemType.getTitle())
                .status(status.value())
                .objects(problemObjects)
                .type(problemType.getUri())
                .timestamp(OffsetDateTime.now())
                .userMessage(detail)
                .build();


        return handleExceptionInternal(ex, problem, headers, status, request);
    }
}

