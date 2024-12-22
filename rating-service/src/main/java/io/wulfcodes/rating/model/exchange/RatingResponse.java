package io.wulfcodes.rating.model.exchange;

import java.util.List;
import io.wulfcodes.rating.model.data.RatingData;

public record RatingResponse(String status, String message, Object payload) {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    public static RatingResponse successResponse(RatingData payload) {
        return new RatingResponse(SUCCESS, null, payload);
    }

    public static RatingResponse errorResponse(RatingData payload) {
        return new RatingResponse(ERROR, null, payload);
    }

    public static RatingResponse successResponse(List<RatingData> payload) {
        return new RatingResponse(SUCCESS, null, payload);
    }

    public static RatingResponse errorResponse(List<RatingData> payload) {
        return new RatingResponse(ERROR, null, payload);
    }

    public static RatingResponse successResponse(String message, RatingData payload) {
        return new RatingResponse(SUCCESS, message, payload);
    }

    public static RatingResponse errorResponse(String message, RatingData payload) {
        return new RatingResponse(ERROR, message, payload);
    }

    public static RatingResponse successResponse(String message, List<RatingData> payload) {
        return new RatingResponse(SUCCESS, message, payload);
    }

    public static RatingResponse errorResponse(String message, List<RatingData> payload) {
        return new RatingResponse(ERROR, message, payload);
    }

}
