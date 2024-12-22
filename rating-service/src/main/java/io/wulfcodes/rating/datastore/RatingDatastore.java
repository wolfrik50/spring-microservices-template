package io.wulfcodes.rating.datastore;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.wulfcodes.rating.mapper.RatingMapper;
import io.wulfcodes.rating.model.data.RatingData;
import io.wulfcodes.rating.model.entity.Rating;
import io.wulfcodes.rating.repository.RatingRepository;

@Component
public class RatingDatastore {

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private RatingMapper ratingMapper;

    public RatingData addRating(RatingData newRatingData) {
        try {
            Rating newRating = ratingMapper.toEntity(newRatingData);
            Rating savedRating = ratingRepository.save(newRating);
            return ratingMapper.toData(savedRating);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Optional<RatingData> getRatingByRatingId(String ratingId) {
        return ratingRepository.findById(ratingId)
                               .map(ratingMapper::toData);
    }

    public List<RatingData> getAllRatings() {
        return ratingMapper.toDataList(ratingRepository.findAll());
    }

    public List<RatingData> getRatingsByUserId(String userId) {
        return ratingMapper.toDataList(ratingRepository.findByUserId(userId));
    }

    public List<RatingData> getRatingsByHotelId(String hotelId) {
        return ratingMapper.toDataList(ratingRepository.findByHotelId(hotelId));
    }

    public void deleteRating(String ratingId) {
        ratingRepository.deleteById(ratingId);
    }
}
