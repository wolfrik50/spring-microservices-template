package io.wulfcodes.common.model.exchange;

import io.wulfcodes.common.model.data.RatingData;

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
