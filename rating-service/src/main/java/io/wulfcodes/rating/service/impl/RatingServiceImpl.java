package io.wulfcodes.rating.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import io.wulfcodes.common.model.data.HotelData;
import io.wulfcodes.common.model.data.RatingCompositeData;
import io.wulfcodes.common.model.data.UserData;
import io.wulfcodes.common.model.entity.Rating;
import io.wulfcodes.common.model.exchange.HotelResponse;
import io.wulfcodes.common.model.exchange.UserResponse;
import io.wulfcodes.rating.client.HotelClient;
import io.wulfcodes.rating.client.UserClient;
import io.wulfcodes.rating.datastore.RatingDatastore;
import io.wulfcodes.common.exception.RatingAbsenceException;
import io.wulfcodes.common.exception.RatingUpsertionException;
import io.wulfcodes.common.model.data.RatingData;
import io.wulfcodes.rating.mapper.RatingMapper;
import io.wulfcodes.rating.service.api.RatingService;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingDatastore ratingDatastore;

    @Autowired
    private RatingMapper ratingMapper;

    @Autowired
    private UserClient userClient;

    @Autowired
    private HotelClient hotelClient;

    @Override
    public String addRating(RatingData newRatingData) {
        try {
            return ratingDatastore.addRating(ratingMapper.toEntity(newRatingData)).getRatingId();
        } catch (Exception ex) {
            throw new RatingUpsertionException("Something wrong happened while inserting rating!");
        }
    }

    @Override
    public RatingData getRatingByRatingId(String ratingId) {
        return ratingDatastore.getRatingByRatingId(ratingId)
                              .map(ratingMapper::toData)
                              .orElseThrow(() -> new RatingAbsenceException(ratingId));
    }

    @Override
    public List<RatingData> getRatings() {
        return ratingMapper.toDataList(ratingDatastore.getAllRatings());
    }

    @Override
    public RatingCompositeData getRatingsByUserId(String userId, boolean includeUser) {
        List<RatingData> ratingsData = ratingMapper.toDataList(ratingDatastore.getRatingsByUserId(userId));

        RatingCompositeData ratingCompositeData = new RatingCompositeData();
        ratingCompositeData.setRatingsData(ratingsData);

        if (includeUser) {
            ResponseEntity<UserResponse<UserData>> userResponse = userClient.fetchUser(userId);
            UserData userData = userResponse.getBody().payload();
            ratingCompositeData.setUserData(userData);
        }

        return ratingCompositeData;
    }

    @Override
    public RatingCompositeData getRatingsByHotelId(String hotelId, boolean includeHotel) {
        List<RatingData> ratingsData = ratingMapper.toDataList(ratingDatastore.getRatingsByHotelId(hotelId));

        RatingCompositeData ratingCompositeData = new RatingCompositeData();
        ratingCompositeData.setRatingsData(ratingsData);

        if (includeHotel) {
            ResponseEntity<HotelResponse<HotelData>> hotelResponse = hotelClient.fetchHotel(hotelId);
            HotelData hotelData = hotelResponse.getBody().payload();
            ratingCompositeData.setHotelData(hotelData);
        }

        return ratingCompositeData;
    }

    @Override
    public RatingCompositeData getRatingsByUserIdAndHotelId(
        String userId, boolean includeUser,
        String hotelId, boolean includeHotel
    ) {
        List<RatingData> ratingsData = ratingMapper.toDataList(ratingDatastore.getRatingsByUserIdAndHotelId(userId, hotelId));

        RatingCompositeData ratingCompositeData = new RatingCompositeData();
        ratingCompositeData.setRatingsData(ratingsData);

        if (includeUser) {
            ResponseEntity<UserResponse<UserData>> userResponse = userClient.fetchUser(userId);
            UserData userData = userResponse.getBody().payload();
            ratingCompositeData.setUserData(userData);
        }

        if (includeHotel) {
            ResponseEntity<HotelResponse<HotelData>> hotelResponse = hotelClient.fetchHotel(hotelId);
            HotelData hotelData = hotelResponse.getBody().payload();
            ratingCompositeData.setHotelData(hotelData);
        }

        return ratingCompositeData;
    }


    @Override
    public String deleteRating(String ratingId) {
        ratingDatastore.deleteRating(ratingId);
        return ratingId;
    }
}
