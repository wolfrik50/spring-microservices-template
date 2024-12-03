package io.wulfcodes.user.exception;

import lombok.Getter;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
public class UserAbsenceException extends UserException {
    private static final Integer STATUS_CODE = NOT_FOUND.value();

    private final String userId;

    public UserAbsenceException(String userId) {
        super(STATUS_CODE, "User with id %s not found!".formatted(userId));
        this.userId = userId;
    }
}
