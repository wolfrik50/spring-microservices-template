package io.wulfcodes.hotel.service.api;

import java.util.List;
import io.wulfcodes.common.model.data.HotelData;

public interface HotelService {
    
    String addHotel(HotelData newHotel);

    List<HotelData> getHotels(boolean includeRatings);

    HotelData getHotelById(String id, boolean includeRatings);

    String deleteHotel(String id);

}
