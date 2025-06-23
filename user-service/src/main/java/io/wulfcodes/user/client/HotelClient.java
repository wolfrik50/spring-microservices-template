package io.wulfcodes.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.wulfcodes.common.model.data.HotelData;
import io.wulfcodes.common.model.exchange.HotelResponse;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@FeignClient(name = "HOTEL-SERVICE", path = "/api/v1/hotels")
public interface HotelClient {

    @GetMapping(path = "/{hotelId}", produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
    ResponseEntity<HotelResponse<HotelData>> fetchHotel(@PathVariable("hotelId") String id);

}
