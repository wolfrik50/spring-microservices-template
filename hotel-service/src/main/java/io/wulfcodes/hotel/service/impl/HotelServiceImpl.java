package io.wulfcodes.hotel.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.wulfcodes.hotel.datastore.HotelDatastore;
import io.wulfcodes.hotel.exception.HotelAbsenceException;
import io.wulfcodes.hotel.exception.HotelUpsertionException;
import io.wulfcodes.hotel.model.data.HotelData;
import io.wulfcodes.hotel.service.api.HotelService;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDatastore hotelDatastore;

    @Override
    public String addHotel(HotelData newHotel) {
        try {
            HotelData savedHotel = hotelDatastore.addHotel(newHotel);
            return savedHotel.getHotelId();
        } catch (Exception ex) {
            throw new HotelUpsertionException("Something wrong happened while creating hotel!");
        }
    }

    @Override
    public List<HotelData> getHotels() {
        return hotelDatastore.getHotels();
    }

    @Override
    public HotelData getHotelById(String id) {
        return hotelDatastore.getHotelById(id)
                             .orElseThrow(() -> new HotelAbsenceException(id));
    }

    @Override
    public String deleteHotel(String id) {
        hotelDatastore.deleteHotel(id);
        return id;
    }

}
