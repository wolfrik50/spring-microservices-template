package io.wulfcodes.hotel.model.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class HotelData {
    private String hotelId;
    private String name;
    private String location;
    private String description;
}
