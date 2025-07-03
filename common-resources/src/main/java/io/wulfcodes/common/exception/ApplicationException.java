package io.wulfcodes.common.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public abstract class ApplicationException extends RuntimeException {
    private final Integer statusCode;

    protected ApplicationException(Integer statusCode) {
        super();
        this.statusCode = statusCode;
    }

    protected ApplicationException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    protected ApplicationException(String message) {
        super(message);
        this.statusCode = INTERNAL_SERVER_ERROR.value();
    }

    public int getStatusCode() {
        return statusCode;
    }
}
