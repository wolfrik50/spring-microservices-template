package io.wulfcodes.user.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.wulfcodes.user.datastore.UserDatastore;
import io.wulfcodes.user.exception.UserAbsenceException;
import io.wulfcodes.user.exception.UserUpsertionException;
import io.wulfcodes.user.model.data.UserData;
import io.wulfcodes.user.service.api.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDatastore userDatastore;

    @Override
    public String addUser(UserData newUser) {
        String userId = UUID.randomUUID().toString();

        try {
            newUser.setUserId(userId);
            UserData savedUser = userDatastore.addUser(newUser);
            return savedUser.getUserId();
        } catch (Exception ex) {
            throw new UserUpsertionException("Something wrong happened while creating user!");
        }
    }

    @Override
    public List<UserData> getUsers() {
        return userDatastore.getUsers();
    }

    @Override
    public UserData getUserById(String id) {
        return userDatastore.getUserById(id)
                            .orElseThrow(() -> new UserAbsenceException(id));
    }

    @Override
    public String updateUser(String id, UserData updatedUser) {
        if (!userDatastore.updateUser(id, updatedUser))
            throw new UserUpsertionException("Something wrong happened while updating user!");
        return id;
    }

    @Override
    public String modifyUser(String id, String fields, UserData updatedUser) {
        if (!userDatastore.modifyUser(id, Arrays.stream(fields.split(",")).toList(), updatedUser))
            throw new UserUpsertionException("Something wrong happened while updating user!");
        return id;
    }

    @Override
    public String modifyUser(String id, UserData updatedUser) {
        updatedUser.setUserId(id);
        if (!userDatastore.modifyUser(id, updatedUser))
            throw new UserUpsertionException("Something wrong happened while updating user!");
        return id;
    }

    @Override
    public String deleteUser(String id) {
        userDatastore.deleteUser(id);
        return id;
    }
}
