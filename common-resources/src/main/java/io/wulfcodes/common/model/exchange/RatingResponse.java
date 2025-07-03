package io.wulfcodes.common.model.exchange;

import java.util.List;
import java.util.Map;
import io.wulfcodes.common.model.data.RatingData;

public record RatingResponse(String status, String message, Object payload, Object details) {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    public static RatingResponse successResponse(RatingData payload) {
        return new RatingResponse(SUCCESS, null, payload, null);
    }

    public static RatingResponse errorResponse(RatingData payload) {
        return new RatingResponse(ERROR, null, payload, null);
    }

    public static RatingResponse successResponse(List<RatingData> payload) {
        return new RatingResponse(SUCCESS, null, payload, null);
    }

    public static RatingResponse errorResponse(List<RatingData> payload) {
        return new RatingResponse(ERROR, null, payload, null);
    }

    public static RatingResponse successResponse(String message, RatingData payload) {
        return new RatingResponse(SUCCESS, message, payload, null);
    }

    public static RatingResponse errorResponse(String message, RatingData payload) {
        return new RatingResponse(ERROR, message, payload, null);
    }

    public static RatingResponse successResponse(String message, List<RatingData> payload) {
        return new RatingResponse(SUCCESS, message, payload, null);
    }

    public static RatingResponse errorResponse(String message, List<RatingData> payload) {
        return new RatingResponse(ERROR, message, payload, null);
    }

    public static RatingResponse successResponse(String message, RatingData payload, Map<String, Object> details) {
        return new RatingResponse(SUCCESS, message, payload, details);
    }

    public static RatingResponse errorResponse(String message, RatingData payload, Map<String, Object> details) {
        return new RatingResponse(ERROR, message, payload, details);
    }

    public static RatingResponse successResponse(String message, List<RatingData> payload, Map<String, Object> details) {
        return new RatingResponse(SUCCESS, message, payload, details);
    }

    public static RatingResponse errorResponse(String message, List<RatingData> payload, Map<String, Object> details) {
        return new RatingResponse(ERROR, message, payload, details);
    }

}
