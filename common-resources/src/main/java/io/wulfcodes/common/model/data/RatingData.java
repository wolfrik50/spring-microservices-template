package io.wulfcodes.common.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RatingData {
    private String ratingId;
    private String userId;
    private String hotelId;
    private HotelData hotelData;
    private Integer rating;
    private String remark;
}
