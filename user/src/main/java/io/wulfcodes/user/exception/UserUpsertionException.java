package io.wulfcodes.user.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class UserUpsertionException extends UserException {
    private static final Integer STATUS_CODE = INTERNAL_SERVER_ERROR.value();

    public UserUpsertionException(String message) {
        super(STATUS_CODE, message);
    }
}
