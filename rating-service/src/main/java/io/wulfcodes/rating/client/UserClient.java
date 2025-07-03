package io.wulfcodes.rating.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.wulfcodes.common.model.data.UserData;
import io.wulfcodes.common.model.exchange.UserResponse;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;
import static io.wulfcodes.common.constant.WebConstant.PathParam.USER_ID;

@FeignClient(name = "USER-SERVICE", path = "/api/v1/users")
public interface UserClient {

    @GetMapping(path = "/{userId}", produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
    ResponseEntity<UserResponse<UserData>> fetchUser(@PathVariable(USER_ID) String id);

}
