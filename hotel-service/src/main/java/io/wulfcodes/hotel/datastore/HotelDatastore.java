package io.wulfcodes.hotel.datastore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import io.wulfcodes.common.model.entity.Hotel;
import io.wulfcodes.hotel.repository.HotelRepository;

@Component
public class HotelDatastore {

    @Autowired
    private HotelRepository hotelRepository;

    @Transactional
    public Hotel addHotel(Hotel newHotel) {
        try {
            return hotelRepository.save(newHotel);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public List<Hotel> getHotels() {
        return hotelRepository.findAll();
    }

    public Optional<Hotel> getHotelById(String id) {
        return hotelRepository.findById(UUID.fromString(id));

    }

    @Transactional
    public void deleteHotel(String id) {
        hotelRepository.deleteById(UUID.fromString(id));
    }

}
