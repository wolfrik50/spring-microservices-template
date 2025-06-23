package io.wulfcodes.hotel.resource.v1;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.wulfcodes.common.model.exchange.GenericResponse;
import io.wulfcodes.common.model.exchange.HotelResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController("staffsV1")
@RequestMapping(
    path = "/v1/staffs",
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
)
public class StaffResource {

    @GetMapping
    public ResponseEntity<GenericResponse> fetchAllStaffs() {
        return ResponseEntity.ok(GenericResponse.successResponse("Ram is the only staff"));
    }

}
