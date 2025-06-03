package io.wulfcodes.hotel.service.api;

import java.util.List;
import io.wulfcodes.hotel.model.data.HotelData;

public interface HotelService {
    
    String addHotel(HotelData newHotel);

    List<HotelData> getHotels();

    HotelData getHotelById(String id);

    String deleteHotel(String id);

}
