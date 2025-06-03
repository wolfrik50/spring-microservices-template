package io.wulfcodes.rating.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class RatingUpsertionException extends RatingException {

    private static final Integer STATUS_CODE = INTERNAL_SERVER_ERROR.value();

    public RatingUpsertionException(String message) {
        super(STATUS_CODE, message);
    }
    
}
