package io.wulfcodes.common.exmap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.wulfcodes.common.model.exchange.GenericResponse;
import io.wulfcodes.common.exception.UserException;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    public ResponseEntity<GenericResponse> handleUserException(UserException ex) {
        return new ResponseEntity<>(GenericResponse.errorResponse(ex.getMessage()), HttpStatus.valueOf(ex.getStatusCode()));
    }

}
