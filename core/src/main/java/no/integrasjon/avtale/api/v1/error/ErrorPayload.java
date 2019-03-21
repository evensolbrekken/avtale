package no.integrasjon.avtale.api.v1.error;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.Validate.notNull;


@SuppressWarnings("WeakerAccess")
public class ErrorPayload {

    private HttpStatus httpStatus;
    private String message;
    private Map<String, String> fieldErrorMessages;

    public ErrorPayload(final HttpStatus httpStatus, final String message) {
        notNull(httpStatus, "Parameter httpStatus is mandatory!");

        this.httpStatus = httpStatus;
        this.message = message;
        this.fieldErrorMessages = new HashMap<>();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return this.message;
    }

    public Map<String, String> getFieldErrorMessages() {
        return this.fieldErrorMessages;
    }
}
