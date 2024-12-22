package io.wulfcodes.rating.exception;

public abstract class RatingException extends RuntimeException {
    private final Integer statusCode;

    protected RatingException(Integer statusCode) {
        super();
        this.statusCode = statusCode;
    }

    protected RatingException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
