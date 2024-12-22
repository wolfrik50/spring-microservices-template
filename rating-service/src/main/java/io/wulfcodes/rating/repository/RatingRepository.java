package io.wulfcodes.rating.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import io.wulfcodes.rating.model.entity.Rating;

public interface RatingRepository extends MongoRepository<Rating, String> {

    @Query("{ 'hotelId': ?0 }")
    List<Rating> findByHotelId(String hotelId);

    @Query("{ 'userId': ?0 }")
    List<Rating> findByUserId(String userId);

}
