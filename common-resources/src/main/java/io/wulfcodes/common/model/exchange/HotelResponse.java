package io.wulfcodes.common.model.exchange;

import java.util.List;
import io.wulfcodes.common.model.data.HotelData;

public record HotelResponse<P>(String status, String message, P payload) {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    public static HotelResponse<HotelData> successResponse(HotelData payload) {
        return new HotelResponse<>(SUCCESS, null, payload);
    }

    public static HotelResponse<HotelData> errorResponse(HotelData payload) {
        return new HotelResponse<>(ERROR, null, payload);
    }

    public static HotelResponse<List<HotelData>> successResponse(List<HotelData> payload) {
        return new HotelResponse<>(SUCCESS, null, payload);
    }

    public static HotelResponse<List<HotelData>> errorResponse(List<HotelData> payload) {
        return new HotelResponse<>(ERROR, null, payload);
    }

    public static HotelResponse<HotelData> successResponse(String message, HotelData payload) {
        return new HotelResponse<>(SUCCESS, message, payload);
    }

    public static HotelResponse<HotelData> errorResponse(String message, HotelData payload) {
        return new HotelResponse<>(ERROR, message, payload);
    }

    public static HotelResponse<List<HotelData>> successResponse(String message, List<HotelData> payload) {
        return new HotelResponse<>(SUCCESS, message, payload);
    }

    public static HotelResponse<List<HotelData>> errorResponse(String message, List<HotelData> payload) {
        return new HotelResponse<>(ERROR, message, payload);
    }
}
