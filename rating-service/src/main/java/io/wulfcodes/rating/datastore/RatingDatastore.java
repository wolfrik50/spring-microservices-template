package io.wulfcodes.rating.datastore;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.wulfcodes.common.model.entity.Rating;
import io.wulfcodes.rating.repository.RatingRepository;

@Component
public class RatingDatastore {

    @Autowired
    private RatingRepository ratingRepository;

    public Rating addRating(Rating newRating) {
        try {
            return ratingRepository.save(newRating);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Optional<Rating> getRatingByRatingId(String ratingId) {
        return ratingRepository.findById(ratingId);
    }

    public List<Rating> getAllRatings() {
        return ratingRepository.findAll();
    }

    public List<Rating> getRatingsByUserId(String userId) {
        return ratingRepository.findByUserId(userId);
    }

    public List<Rating> getRatingsByHotelId(String hotelId) {
        return ratingRepository.findByHotelId(hotelId);
    }

    public List<Rating> getRatingsByUserIdAndHotelId(String userId, String hotelId) {
        return ratingRepository.findByUserIdAndHotelId(userId, hotelId);
    }

    public void deleteRating(String ratingId) {
        ratingRepository.deleteById(ratingId);
    }
}
