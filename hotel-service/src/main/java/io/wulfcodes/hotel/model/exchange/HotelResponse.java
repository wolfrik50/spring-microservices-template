package io.wulfcodes.hotel.model.exchange;

import java.util.List;
import io.wulfcodes.hotel.model.data.HotelData;

public record HotelResponse(String status, String message, Object payload) {

    private static final String SUCCESS = "success";
    private static final String ERROR = "error";

    public static HotelResponse successResponse(HotelData payload) {
        return new HotelResponse(SUCCESS, null, payload);
    }

    public static HotelResponse errorResponse(HotelData payload) {
        return new HotelResponse(ERROR, null, payload);
    }

    public static HotelResponse successResponse(List<HotelData> payload) {
        return new HotelResponse(SUCCESS, null, payload);
    }

    public static HotelResponse errorResponse(List<HotelData> payload) {
        return new HotelResponse(ERROR, null, payload);
    }

    public static HotelResponse successResponse(String message, HotelData payload) {
        return new HotelResponse(SUCCESS, message, payload);
    }

    public static HotelResponse errorResponse(String message, HotelData payload) {
        return new HotelResponse(ERROR, message, payload);
    }

    public static HotelResponse successResponse(String message, List<HotelData> payload) {
        return new HotelResponse(SUCCESS, message, payload);
    }

    public static HotelResponse errorResponse(String message, List<HotelData> payload) {
        return new HotelResponse(ERROR, message, payload);
    }

}
