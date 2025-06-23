package io.wulfcodes.common.model.exchange;

import io.wulfcodes.common.model.data.UserData;

public record UserRequest(
    String name,
    String email,
    String about
) {

    public UserData getUserData() {
        return UserData.builder()
                       .name(name)
                       .email(email)
                       .about(about)
                       .build();
    }

}
