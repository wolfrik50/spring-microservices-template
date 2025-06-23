package io.wulfcodes.common.exmap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.wulfcodes.common.exception.ApplicationException;
import io.wulfcodes.common.model.exchange.GenericResponse;

@RestControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<GenericResponse> handleApplicationException(ApplicationException ex) {
        return new ResponseEntity<>(GenericResponse.errorResponse(ex.getMessage()), HttpStatus.valueOf(ex.getStatusCode()));
    }

}
