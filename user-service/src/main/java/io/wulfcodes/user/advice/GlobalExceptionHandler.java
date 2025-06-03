package io.wulfcodes.user.advice;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import io.wulfcodes.user.model.exchange.GenericResponse;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<GenericResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> data = new HashMap<>();

        for (FieldError error : ex.getFieldErrors())
            data.putIfAbsent(error.getField(), error.getDefaultMessage());

        return new ResponseEntity<>(GenericResponse.errorResponse("Invalid Input Provided"), UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {HttpMediaTypeException.class, HttpRequestMethodNotSupportedException.class})
    public <E extends ErrorResponse> ResponseEntity<GenericResponse> handleHttpException(E ex) {

        Map<String, Object> data = Map.of("info", Objects.requireNonNull(ex.getBody().getDetail()));

        return new ResponseEntity<>(GenericResponse.errorResponse(ex.getBody().getTitle()), ex.getStatusCode());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<GenericResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return new ResponseEntity<>(GenericResponse.errorResponse("Query param '%s' is missing!".formatted(ex.getMethodParameter().getParameterName())), UNPROCESSABLE_ENTITY);
    }

}
