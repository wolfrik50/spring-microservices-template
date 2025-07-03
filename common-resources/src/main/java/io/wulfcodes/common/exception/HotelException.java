package io.wulfcodes.common.exception;

public abstract class HotelException extends ApplicationException {
    protected HotelException(Integer statusCode) {
        super(statusCode);
    }

    protected HotelException(Integer statusCode, String message) {
        super(statusCode, message);
    }
}