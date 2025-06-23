package io.wulfcodes.common.model.exchange;

import io.wulfcodes.common.model.data.HotelData;

public record HotelRequest(
    String name,
    String location,
    String description,
    Boolean roomsAvailable
) {

    public HotelData getHotelData() {
        return HotelData.builder()
                        .name(name)
                        .location(location)
                        .description(description)
                        .roomsAvailable(roomsAvailable)
                        .build();
    }

}
