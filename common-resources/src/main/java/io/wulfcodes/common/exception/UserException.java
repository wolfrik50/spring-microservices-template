package io.wulfcodes.common.exception;

public abstract class UserException extends ApplicationException {
    protected UserException(Integer statusCode) {
        super(statusCode);
    }

    protected UserException(Integer statusCode, String message) {
        super(statusCode, message);
    }
}