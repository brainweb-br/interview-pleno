package br.com.brainweb.interview.core.exceptionhandler;

import org.apache.logging.log4j.core.tools.picocli.CommandLine.IExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import java.util.Map;
import static org.springframework.http.ResponseEntity.status;


@ControllerAdvice
public class ExceptionHandler {

    public static final String GENERIC_MSG_ERROR
            = "Senhor Wayne do céu, as coisas aqui não saíram como o esperado. Tente novamente e se "
            + "o problema persistir, nós, seus pupilos iremos entrar em ação!";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(ExceptionController.class)
    public ResponseEntity<?> handleControllerException(ExceptionController exception) {
        return status(exception.getStatus()).body(Map.of(
                "mensagem", exception.getLocalizedMessage()));
    }

}
