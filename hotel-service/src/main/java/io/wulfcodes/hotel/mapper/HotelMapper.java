package io.wulfcodes.hotel.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import io.wulfcodes.hotel.model.data.HotelData;
import io.wulfcodes.hotel.model.entity.Hotel;

@Component
@Mapper(componentModel = "spring")
public interface HotelMapper {

    @Mapping(source = "hotelId", target = "id")
    Hotel toEntity(HotelData hotelData);

    @Mapping(source = "id", target = "hotelId")
    HotelData toData(Hotel hotel);

    List<HotelData> toDataList(Iterable<Hotel> hotels);
}
