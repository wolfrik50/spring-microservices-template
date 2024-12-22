package io.wulfcodes.user.resource.v1;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import io.wulfcodes.user.model.data.UserData;
import io.wulfcodes.user.model.exchange.GenericResponse;
import io.wulfcodes.user.model.exchange.UserRequest;
import io.wulfcodes.user.model.exchange.UserResponse;
import io.wulfcodes.user.service.api.UserService;

import static org.springframework.http.HttpHeaders.LOCATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController("usersV1")
@RequestMapping(
    path = "/v1/users",
    consumes = APPLICATION_JSON_VALUE,
    produces = APPLICATION_JSON_VALUE
)
public class UserResource {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<GenericResponse> insertUser(
            @RequestBody
            UserRequest newUser
    ) {
        try {
            String userId = userService.addUser(newUser.getUserData());
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                                      .path("/{userId}")
                                                      .buildAndExpand(userId)
                                                      .toUri();

            return ResponseEntity.created(location).body(GenericResponse.successResponse(String.format("User created successfully with id '%s'.", userId)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<UserResponse> fetchAllUsers() {
        try {
            List<UserData> users = userService.getUsers();
            return users.isEmpty()
                   ? ResponseEntity.noContent().build()
                   : ResponseEntity.ok(UserResponse.successResponse(users));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> fetchUser(@PathVariable("userId") String id) {
        UserData fetchedUser = userService.getUserById(id);
        return ResponseEntity.ok(UserResponse.successResponse(fetchedUser));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<GenericResponse> changeUser(@PathVariable("userId") String id, @RequestBody UserData updatedUser) {
        try {
            String userId = userService.updateUser(id, updatedUser);
            return ResponseEntity.accepted()
                                 .header(LOCATION, ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                                 .body(GenericResponse.successResponse("User updation request is accepted."));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<GenericResponse> reviseUser(
            @PathVariable("userId")
            String id,
            @RequestParam("fields")
            String fields,
            @RequestBody
            UserData updatedUser
    ) {
        try {
            String userId = userService.modifyUser(id, fields, updatedUser);
            return ResponseEntity.accepted()
                                 .header(LOCATION, ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                                 .body(GenericResponse.successResponse("User updation request is accepted."));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<GenericResponse> removeUser(@PathVariable("userId") String id) {
        try {
            String userId = userService.deleteUser(id);
            return ResponseEntity.accepted()
                                 .header(LOCATION, ServletUriComponentsBuilder.fromCurrentRequest().toUriString())
                                 .body(GenericResponse.successResponse("User deletion request is accepted."));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}


