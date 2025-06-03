package io.wulfcodes.rating.exception;

import lombok.Getter;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public class RatingAbsenceException extends RatingException {
    private static final Integer STATUS_CODE = NOT_FOUND.value();

    private final String ratingId;

    public RatingAbsenceException(String ratingId) {
        super(STATUS_CODE, "Rating with id '%s' not found!".formatted(ratingId));
        this.ratingId = ratingId;
    }
}
