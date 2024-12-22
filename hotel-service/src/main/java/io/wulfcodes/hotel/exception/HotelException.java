package io.wulfcodes.hotel.exception;

public abstract class HotelException extends RuntimeException {
    private final Integer statusCode;

    protected HotelException(Integer statusCode) {
        super();
        this.statusCode = statusCode;
    }

    protected HotelException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}