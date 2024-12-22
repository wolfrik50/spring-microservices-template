package io.wulfcodes.hotel.datastore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.wulfcodes.hotel.mapper.HotelMapper;
import io.wulfcodes.hotel.model.entity.Hotel;
import io.wulfcodes.hotel.model.data.HotelData;
import io.wulfcodes.hotel.repository.HotelRepository;

@Component
public class HotelDatastore {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;

    public HotelData addHotel(HotelData newHotelData) {
        try {
            Hotel newHotel = hotelMapper.toEntity(newHotelData);
            // newHotel.setNewHotel(true);
            System.out.println("Created Hotel " + newHotel);
            Hotel savedHotel = hotelRepository.save(newHotel);
            return hotelMapper.toData(savedHotel);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<HotelData> getHotels() {
        Iterable<Hotel> allHotels = hotelRepository.findAll();
        return hotelMapper.toDataList(allHotels);
    }

    public Optional<HotelData> getHotelById(String id) {
        return hotelRepository.findById(UUID.fromString(id))
                              .map(hotelMapper::toData);

    }

    public void deleteHotel(String id) {
        hotelRepository.deleteById(UUID.fromString(id));
    }

}
