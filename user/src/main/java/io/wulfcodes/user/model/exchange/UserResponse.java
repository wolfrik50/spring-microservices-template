package io.wulfcodes.user.model.exchange;

import java.util.List;
import io.wulfcodes.user.model.data.UserData;

public record UserResponse(String status, String message, Object payload) {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    public static UserResponse successResponse(UserData payload) {
        return new UserResponse(SUCCESS, null, payload);
    }

    public static UserResponse errorResponse(UserData payload) {
        return new UserResponse(ERROR, null, payload);
    }

    public static UserResponse successResponse(List<UserData> payload) {
        return new UserResponse(SUCCESS, null, payload);
    }

    public static UserResponse errorResponse(List<UserData> payload) {
        return new UserResponse(ERROR, null, payload);
    }

    public static UserResponse successResponse(String message, UserData payload) {
        return new UserResponse(SUCCESS, message, payload);
    }

    public static UserResponse errorResponse(String message, UserData payload) {
        return new UserResponse(ERROR, message, payload);
    }

    public static UserResponse successResponse(String message, List<UserData> payload) {
        return new UserResponse(SUCCESS, message, payload);
    }

    public static UserResponse errorResponse(String message, List<UserData> payload) {
        return new UserResponse(ERROR, message, payload);
    }

}
