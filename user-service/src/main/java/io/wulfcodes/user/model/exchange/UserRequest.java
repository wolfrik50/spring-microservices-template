package io.wulfcodes.user.model.exchange;

import io.wulfcodes.user.model.data.UserData;

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
