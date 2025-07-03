package io.wulfcodes.common.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class RatingAbsenceException extends RatingException {
    private static final Integer STATUS_CODE = NOT_FOUND.value();

    public RatingAbsenceException() {
        super(STATUS_CODE, "Unable to fetch ratings!");
    }

    public RatingAbsenceException(String ratingId) {
        super(STATUS_CODE, "Rating with id '%s' not found!".formatted(ratingId));
    }
}
