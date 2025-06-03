package io.wulfcodes.user.exception;

public abstract class UserException extends RuntimeException {
    private final Integer statusCode;

    protected UserException(Integer statusCode) {
        super();
        this.statusCode = statusCode;
    }

    protected UserException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}