package io.wulfcodes.hotel.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.wulfcodes.hotel.exception.HotelException;
import io.wulfcodes.hotel.model.exchange.GenericResponse;

@RestControllerAdvice
public class HotelExceptionHandler {

    @ExceptionHandler(value = HotelException.class)
    public ResponseEntity<GenericResponse> handleHotelException(HotelException ex) {
        return new ResponseEntity<>(GenericResponse.errorResponse(ex.getMessage()), HttpStatus.valueOf(ex.getStatusCode()));
    }

}
