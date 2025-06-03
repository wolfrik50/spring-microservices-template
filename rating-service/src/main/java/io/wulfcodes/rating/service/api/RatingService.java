package io.wulfcodes.rating.service.api;

import java.util.List;
import io.wulfcodes.rating.model.data.RatingData;

public interface RatingService {

    String addRating(RatingData newRating);

    RatingData getRatingByRatingId(String ratingId);

    List<RatingData> getRatings();

    List<RatingData> getRatingsByUserId(String userId);

    List<RatingData> getRatingsByHotelId(String hotelId);

    String deleteRating(String ratingId);

}
