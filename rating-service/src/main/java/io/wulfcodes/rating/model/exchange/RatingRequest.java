package io.wulfcodes.rating.model.exchange;

import io.wulfcodes.rating.model.data.RatingData;

public record RatingRequest(
    String userId,
    String hotelId,
    Integer rating,
    String remark
) {

    public RatingData getRatingData() {
        return RatingData.builder()
                         .userId(userId)
                         .hotelId(hotelId)
                         .rating(rating)
                         .remark(remark)
                         .build();
    }

}
