package io.wulfcodes.common.constant;

public interface WebConstant {

    interface PathParam {
        String USER_ID = "userId";
        String HOTEL_ID = "hotelId";
        String RATING_ID = "ratingId";
    }

    interface QueryParam {
        String INCLUDE_RATINGS = "include-ratings";
        String USER_ID = "user-id";
        String HOTEL_ID = "hotel-id";
    }

    interface MatrixParam {
        String INCLUDE_USER = "include_user";
        String INCLUDE_HOTEL = "include_hotel";
    }

}
