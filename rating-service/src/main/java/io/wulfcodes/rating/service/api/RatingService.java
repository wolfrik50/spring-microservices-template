package io.wulfcodes.rating.service.api;

import java.util.List;
import io.wulfcodes.common.model.data.RatingCompositeData;
import io.wulfcodes.common.model.data.RatingData;

public interface RatingService {

    String addRating(RatingData newRating);

    RatingData getRatingByRatingId(String ratingId);

    List<RatingData> getRatings();

    RatingCompositeData getRatingsByUserId(String userId, boolean includeUser);

    RatingCompositeData getRatingsByHotelId(String hotelId, boolean includeHotel);

    RatingCompositeData getRatingsByUserIdAndHotelId(String userId, boolean includeUser, String hotelId, boolean includeHotel);

    String deleteRating(String ratingId);

}
