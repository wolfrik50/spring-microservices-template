package io.wulfcodes.common.exception;

public abstract class RatingException extends ApplicationException {
    protected RatingException(Integer statusCode) {
        super(statusCode);
    }

    protected RatingException(Integer statusCode, String message) {
        super(statusCode, message);
    }
}
