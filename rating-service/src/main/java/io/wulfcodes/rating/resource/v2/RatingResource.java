package io.wulfcodes.rating.resource.v2;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.wulfcodes.common.model.data.HotelData;
import io.wulfcodes.common.model.data.RatingCompositeData;
import io.wulfcodes.common.model.data.UserData;
import io.wulfcodes.common.model.exchange.RatingResponse;
import io.wulfcodes.rating.service.api.RatingService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.apache.commons.lang3.BooleanUtils.FALSE;
import static io.wulfcodes.common.constant.WebConstant.MatrixParam.INCLUDE_HOTEL;
import static io.wulfcodes.common.constant.WebConstant.MatrixParam.INCLUDE_USER;
import static io.wulfcodes.common.constant.WebConstant.PathParam.HOTEL_ID;
import static io.wulfcodes.common.constant.WebConstant.PathParam.USER_ID;

@RestController("ratingsV2")
@RequestMapping(
    path = "/v2/ratings",
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
)
public class RatingResource {

    @Autowired
    private RatingService ratingService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> fetchAllRatingsByUserId(
        @PathVariable(USER_ID)
        String userId,
        @MatrixVariable(name = INCLUDE_USER, pathVar = USER_ID, required = false, defaultValue = FALSE)
        Boolean includeUser
    ) {
        try {
            RatingCompositeData ratingCompositeData = ratingService.getRatingsByUserId(userId, includeUser);

            Map<String, Object> details = !includeUser ? Collections.emptyMap() : Collections.singletonMap("user", ratingCompositeData.getUserData());

            return ResponseEntity.ok(RatingResponse.successResponse("Ratings fetched successfully.", ratingCompositeData.getRatingsData(), details));
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/hotel/{hotelId}")
    public ResponseEntity<?> fetchAllRatingsByHotelId(
        @PathVariable(HOTEL_ID)
        String hotelId,
        @MatrixVariable(name = INCLUDE_HOTEL, pathVar = HOTEL_ID, required = false, defaultValue = FALSE)
        Boolean includeHotel
    ) {
        try {
            RatingCompositeData ratingCompositeData = ratingService.getRatingsByHotelId(hotelId, includeHotel);

            Map<String, Object> details = !includeHotel ? Collections.emptyMap() : Collections.singletonMap("hotel", ratingCompositeData.getHotelData());

            return ResponseEntity.ok(RatingResponse.successResponse("Ratings fetched successfully.", ratingCompositeData.getRatingsData(), details));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/user/{userId}/hotel/{hotelId}")
    public ResponseEntity<?> fetchAllRatingsByUserId(
        @PathVariable(USER_ID)
        String userId,
        @MatrixVariable(name = INCLUDE_USER, pathVar = USER_ID, required = false, defaultValue = FALSE)
        Boolean includeUser,
        @PathVariable(HOTEL_ID)
        String hotelId,
        @MatrixVariable(name = INCLUDE_HOTEL, pathVar = HOTEL_ID, required = false, defaultValue = FALSE)
        Boolean includeHotel
    ) {

        try {
            RatingCompositeData ratingCompositeData = ratingService.getRatingsByUserIdAndHotelId(userId, includeUser, hotelId, includeHotel);

            Map<String, Object> details = new HashMap<>();

            UserData userData = ratingCompositeData.getUserData();
            if (Objects.nonNull(userData))
                details.put("user", userData);

            HotelData hotelData = ratingCompositeData.getHotelData();
            if (Objects.nonNull(hotelData))
                details.put("hotel", hotelData);

            return ResponseEntity.ok(RatingResponse.successResponse(
                "Ratings fetched successfully.",
                ratingCompositeData.getRatingsData(),
                details
            ));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
