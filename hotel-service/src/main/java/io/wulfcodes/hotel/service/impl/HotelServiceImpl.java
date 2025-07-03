package io.wulfcodes.hotel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.wulfcodes.common.model.data.RatingData;
import io.wulfcodes.common.model.entity.Hotel;
import io.wulfcodes.hotel.client.RatingClient;
import io.wulfcodes.hotel.datastore.HotelDatastore;
import io.wulfcodes.common.exception.HotelAbsenceException;
import io.wulfcodes.common.exception.HotelUpsertionException;
import io.wulfcodes.common.model.data.HotelData;
import io.wulfcodes.hotel.mapper.HotelMapper;
import io.wulfcodes.hotel.service.api.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDatastore hotelDatastore;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private RatingClient ratingClient;

    @Override
    public String addHotel(HotelData newHotelData) {
        try {
            Hotel newHotel = hotelMapper.toEntity(newHotelData);
            Hotel savedHotel = hotelDatastore.addHotel(newHotel);
            return savedHotel.getId().toString();
        } catch (Exception ex) {
            throw new HotelUpsertionException("Something wrong happened while creating hotel!");
        }
    }

    @Override
    public List<HotelData> getHotels(boolean includeRatings) {
        List<HotelData> hotelsData = hotelMapper.toDataList(hotelDatastore.getHotels());

        if (includeRatings)
            for (HotelData hotelData : hotelsData)
                hotelData.setRatings(ratingClient.getAllRatingsByHotelId(hotelData.getHotelId()));

        return hotelsData;
    }

    @Override
    public HotelData getHotelById(String id, boolean includeRatings) {
        return hotelDatastore.getHotelById(id)
                             .map(hotel -> {
                                 HotelData hotelData = hotelMapper.toData(hotel);
                                 if (includeRatings)
                                     hotelData.setRatings(ratingClient.getAllRatingsByHotelId(hotel.getId().toString()));
                                 return hotelData;
                             })
                             .orElseThrow(() -> new HotelAbsenceException(id));
    }

    @Override
    public String deleteHotel(String id) {
        hotelDatastore.deleteHotel(id);
        return id;
    }

}
