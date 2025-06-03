package io.wulfcodes.hotel.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import io.wulfcodes.hotel.model.entity.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, UUID> {}
