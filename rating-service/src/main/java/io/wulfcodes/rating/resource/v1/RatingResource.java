package io.wulfcodes.rating.resource.v1;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.wulfcodes.rating.model.data.RatingData;
import io.wulfcodes.rating.model.exchange.GenericResponse;
import io.wulfcodes.rating.model.exchange.RatingRequest;
import io.wulfcodes.rating.model.exchange.RatingResponse;
import io.wulfcodes.rating.service.api.RatingService;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController("ratingsV1")
@RequestMapping(
    path = "/v1/ratings",
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
)
public class RatingResource {

    @Autowired
    private RatingService ratingService;

    @PostMapping
    public ResponseEntity<GenericResponse> insertRating(
        @RequestBody
        RatingRequest newRating
    ) {
        try {
            String ratingId = ratingService.addRating(newRating.getRatingData());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                      .path("/{ratingId}")
                                                      .buildAndExpand(ratingId)
                                                      .toUri();

            return ResponseEntity.created(location).body(GenericResponse.successResponse(String.format("Rating created successfully with id '%s'.", ratingId)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{ratingId}")
    public ResponseEntity<RatingResponse> fetchRating(@PathVariable("ratingId") String ratingId) {
        RatingData fetchedRating = ratingService.getRatingByRatingId(ratingId);
        return ResponseEntity.ok(RatingResponse.successResponse(fetchedRating));
    }

    @GetMapping
    public ResponseEntity<?> fetchAllRatings(
        @RequestParam(name = "userId", required = false)
        String userId,
        @RequestParam(name = "hotelId", required = false)
        String hotelId
    ) {
        try {
            if (StringUtils.hasLength(userId) && StringUtils.hasLength(hotelId))
                return ResponseEntity.unprocessableEntity()
                                     .body(GenericResponse.errorResponse("Unique identifier required to identify the resource."));

            List<RatingData> ratings;
            if (StringUtils.hasLength(userId))
                ratings = ratingService.getRatingsByUserId(userId);
            else if (StringUtils.hasLength(hotelId))
                ratings = ratingService.getRatingsByHotelId(hotelId);
            else
                ratings = ratingService.getRatings();

            return ratings.isEmpty()
                   ? ResponseEntity.noContent().build()
                   : ResponseEntity.ok(RatingResponse.successResponse(ratings));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{ratingId}")
    public ResponseEntity<GenericResponse> removeRating(@PathVariable("ratingId") String ratingId) {
        try {
            ratingService.deleteRating(ratingId);
            return ResponseEntity.accepted()
                                 .header(LOCATION, ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                                 .body(GenericResponse.successResponse("Rating deletion request is accepted."));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }


}
