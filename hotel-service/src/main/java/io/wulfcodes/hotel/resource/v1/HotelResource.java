package io.wulfcodes.hotel.resource.v1;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.wulfcodes.common.model.data.HotelData;
import io.wulfcodes.common.model.exchange.GenericResponse;
import io.wulfcodes.common.model.exchange.HotelRequest;
import io.wulfcodes.common.model.exchange.HotelResponse;
import io.wulfcodes.hotel.service.api.HotelService;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.apache.commons.lang3.BooleanUtils.FALSE;
import static io.wulfcodes.common.constant.WebConstant.PathParam.HOTEL_ID;
import static io.wulfcodes.common.constant.WebConstant.QueryParam.INCLUDE_RATINGS;

@RestController("hotelsV1")
@RequestMapping(
        path = "/v1/hotels",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
)
public class HotelResource {

    @Autowired
    private HotelService hotelService;

    @PostMapping
    public ResponseEntity<GenericResponse> insertHotel(
            @RequestBody
            HotelRequest newHotel
    ) {
        try {
            String hotelId = hotelService.addHotel(newHotel.getHotelData());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                      .path("/{hotelId}")
                                                      .buildAndExpand(hotelId)
                                                      .toUri();

            return ResponseEntity.created(location).body(GenericResponse.successResponse(String.format("Hotel created successfully with id '%s'.", hotelId)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<HotelResponse<List<HotelData>>> fetchAllHotels(
        @RequestParam(name = INCLUDE_RATINGS, defaultValue = FALSE)
        Boolean includeRatings
    ) {
        try {
            List<HotelData> hotels = hotelService.getHotels(includeRatings);
            return hotels.isEmpty()
                   ? ResponseEntity.noContent().build()
                   : ResponseEntity.ok(HotelResponse.successResponse(hotels));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{hotelId}")
    public ResponseEntity<HotelResponse<HotelData>> fetchHotel(
        @PathVariable(HOTEL_ID)
        String id,
        @RequestParam(name = INCLUDE_RATINGS, defaultValue = FALSE)
        Boolean includeRatings
    ) {
        HotelData fetchedHotel = hotelService.getHotelById(id, includeRatings);
        return ResponseEntity.ok(HotelResponse.successResponse(fetchedHotel));
    }

    @DeleteMapping("/{hotelId}")
    public ResponseEntity<GenericResponse> removeHotel(@PathVariable(HOTEL_ID) String id) {
        try {
            String hotelId = hotelService.deleteHotel(id);
            return ResponseEntity.accepted()
                                 .header(LOCATION, ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                                 .body(GenericResponse.successResponse("Hotel deletion request is accepted."));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
