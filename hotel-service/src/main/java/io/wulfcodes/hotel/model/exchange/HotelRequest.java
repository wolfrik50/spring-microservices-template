package io.wulfcodes.hotel.model.exchange;

import io.wulfcodes.hotel.model.data.HotelData;

public record HotelRequest(
    String name,
    String location,
    String description
) {

    public HotelData getHotelData() {
        return HotelData.builder()
                       .name(name)
                       .location(location)
                       .description(description)
                       .build();
    }

}
