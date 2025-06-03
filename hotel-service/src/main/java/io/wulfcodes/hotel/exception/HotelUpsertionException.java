package io.wulfcodes.hotel.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class HotelUpsertionException extends HotelException {
    private static final Integer STATUS_CODE = INTERNAL_SERVER_ERROR.value();

    public HotelUpsertionException(String message) {
        super(STATUS_CODE, message);
    }
}
