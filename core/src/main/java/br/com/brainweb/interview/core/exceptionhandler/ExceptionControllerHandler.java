package br.com.brainweb.interview.core.exceptionhandler;

import org.apache.logging.log4j.core.tools.picocli.CommandLine.IExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.Map;
import static org.springframework.http.ResponseEntity.status;


@ControllerAdvice
public class ExceptionControllerHandler extends ResponseEntityExceptionHandler {

    public static final String GENERIC_MSG_ERROR
            = "Senhor Wayne do céu, as coisas aqui não saíram como o esperado. Tente novamente e se "
            + "o problema persistir, nós, seus pupilos iremos entrar em ação!";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ExceptionController.class)
    public ResponseEntity<?> handleControllerException(ExceptionController exception, WebRequest request) {

        String detail = exception.getMessage();
        HttpStatus status = exception.getStatus();
        ProblemType problemType = problemSwitch(exception.getStatus());
        Problem problem = createProblem(status, problemType, detail)
                .userMessage(detail)
                .build();

        return handleExceptionInternal(exception, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Problem.builder()
                    .timestamp(LocalDateTime.now())
                    .status(status.value())
                    .userMessage(GENERIC_MSG_ERROR)
                    .build();
        } else if (body instanceof String) {
            body = Problem.builder()
                    .timestamp(LocalDateTime.now())
                    .status(status.value())
                    .userMessage(GENERIC_MSG_ERROR)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Problem.ProblemBuilder createProblem(HttpStatus status, ProblemType problemType, String detail){
        return Problem.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .type(problemType.getUri())
                .title(problemType.getTitle())
                .detail(detail);
    }
    private ProblemType problemSwitch(HttpStatus status) {
        ProblemType problemType;
        switch (status){
        case CONFLICT:
            problemType = ProblemType.CONFLICT;
        break;
        case NOT_FOUND:
            problemType = ProblemType.RESOURCE_NOT_FOUND;
        break;
        default:
            problemType = ProblemType.SYSTEM_ERROR;
            break;
    }
    return problemType;
    }

}
