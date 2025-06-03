package io.wulfcodes.user.service.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import io.wulfcodes.user.model.data.UserData;

public interface UserService {

    String addUser(UserData newUser);

    List<UserData> getUsers(boolean includeRatings);

    UserData getUserById(String id, boolean includeRatings);

    String updateUser(String id, UserData updatedUser);

    String modifyUser(String id, String fields, UserData updatedUser);

    String modifyUser(String id, UserData updatedUser);

    String deleteUser(String id);

}
