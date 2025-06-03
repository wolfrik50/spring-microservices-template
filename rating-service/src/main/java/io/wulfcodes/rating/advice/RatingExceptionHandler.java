package io.wulfcodes.rating.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.wulfcodes.rating.exception.RatingException;
import io.wulfcodes.rating.model.exchange.GenericResponse;

@RestControllerAdvice
public class RatingExceptionHandler {

    @ExceptionHandler(value = RatingException.class)
    public ResponseEntity<GenericResponse> handleRatingException(RatingException ex) {
        return new ResponseEntity<>(GenericResponse.errorResponse(ex.getMessage()), HttpStatus.valueOf(ex.getStatusCode()));
    }

}
