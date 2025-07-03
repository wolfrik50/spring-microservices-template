package io.wulfcodes.common.model.data;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class RatingCompositeData {
    private RatingData ratingData;
    private List<RatingData> ratingsData;
    private UserData userData;
    private HotelData hotelData;
}
