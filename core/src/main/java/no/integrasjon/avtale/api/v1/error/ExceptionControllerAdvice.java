package no.integrasjon.avtale.api.v1.error;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.util.Assert.notNull;


@ControllerAdvice
public class ExceptionControllerAdvice {

    private final static Logger LOGGER = getLogger(ExceptionControllerAdvice.class);
    private final MessageSource messageSource;


    @Autowired
    public ExceptionControllerAdvice(final MessageSource messageSource) {
        notNull(messageSource, "MessageSource kan ikke være null!");
        this.messageSource = messageSource;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorPayload> methodArgumentNotValidExceptionHandler(final MethodArgumentNotValidException e) {
        final ErrorPayload error = new ErrorPayload(UNPROCESSABLE_ENTITY, "Valideringsfeil!");
        e.getBindingResult().getFieldErrors().forEach(fieldError -> error.getFieldErrorMessages().put(fieldError.getField(), this.messageSource.getMessage(fieldError, null)));
        LOGGER.warn("Valideringsfeil! {}, {}, {}", UNPROCESSABLE_ENTITY, error.getMessage(), error.getFieldErrorMessages());

        return status(UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorPayload> internalServerExceptionHandler(final Exception e) {
        LOGGER.error(e.getMessage(), e);
        return status(INTERNAL_SERVER_ERROR).body(new ErrorPayload(INTERNAL_SERVER_ERROR, e.getMessage()));
    }
}
