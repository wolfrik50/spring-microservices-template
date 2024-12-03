package io.wulfcodes.user.resource.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.wulfcodes.user.model.data.UserData;
import io.wulfcodes.user.model.exchange.GenericResponse;
import io.wulfcodes.user.service.api.UserService;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController("usersV2")
@RequestMapping(
        path = "/v2/users",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
)
public class UserResource {

    @Autowired
    private UserService userService;

    @PatchMapping("/{userId}")
    public ResponseEntity<GenericResponse> reviseUser(
            @PathVariable("userId")
            String id,
            @RequestBody
            UserData updatedUser
    ) {
        try {
            String userId = userService.modifyUser(id, updatedUser);
            return ResponseEntity.accepted()
                                 .header(LOCATION, ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                                 .body(GenericResponse.successResponse("User updation request is accepted."));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
