package io.wulfcodes.rating.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.wulfcodes.rating.datastore.RatingDatastore;
import io.wulfcodes.rating.exception.RatingAbsenceException;
import io.wulfcodes.rating.exception.RatingUpsertionException;
import io.wulfcodes.rating.model.data.RatingData;
import io.wulfcodes.rating.service.api.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDatastore ratingDatastore;

    @Override
    public String addRating(RatingData newRating) {
        try {
            return ratingDatastore.addRating(newRating).getRatingId();
        } catch (Exception ex) {
            throw new RatingUpsertionException("Something wrong happened while inserting rating!");
        }
    }

    @Override
    public RatingData getRatingByRatingId(String ratingId) {
        return ratingDatastore.getRatingByRatingId(ratingId)
                              .orElseThrow(() -> new RatingAbsenceException(ratingId));
    }

    @Override
    public List<RatingData> getRatings() {
        return ratingDatastore.getAllRatings();
    }

    @Override
    public List<RatingData> getRatingsByUserId(String userId) {
        return ratingDatastore.getRatingsByUserId(userId);
    }

    @Override
    public List<RatingData> getRatingsByHotelId(String hotelId) {
        return ratingDatastore.getRatingsByHotelId(hotelId);
    }


    @Override
    public String deleteRating(String ratingId) {
        ratingDatastore.deleteRating(ratingId);
        return ratingId;
    }
}
