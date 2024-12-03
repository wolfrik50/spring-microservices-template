package io.wulfcodes.user.model.exchange;

public record GenericResponse(String status, String message) {
    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    public static GenericResponse successResponse(String message) {
        return new GenericResponse(SUCCESS, message);
    }

    public static GenericResponse errorResponse(String message) {
        return new GenericResponse(ERROR, message);
    }
}
