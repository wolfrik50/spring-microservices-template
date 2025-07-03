package io.wulfcodes.user.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.wulfcodes.common.model.data.RatingData;
import io.wulfcodes.common.model.entity.User;
import io.wulfcodes.common.exception.UserAbsenceException;
import io.wulfcodes.common.exception.UserUpsertionException;
import io.wulfcodes.common.model.data.UserData;
import io.wulfcodes.user.client.RatingClient;
import io.wulfcodes.user.datastore.UserDatastore;
import io.wulfcodes.user.mapper.UserMapper;
import io.wulfcodes.user.service.api.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDatastore userDatastore;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RatingClient ratingClient;

    @Override
    public String addUser(UserData newUserData) {
        String userId = UUID.randomUUID().toString();

        try {
            User newUser = userMapper.toEntity(newUserData);
            newUser.setId(userId);
            newUser.setNewUser(true);

            User savedUser = userDatastore.addUser(newUser);
            return savedUser.getId();
        } catch (Exception ex) {
            throw new UserUpsertionException("Something wrong happened while creating user!");
        }
    }

    @Override
    public List<UserData> getUsers(boolean includeRatings) {
        Iterable<User> users = userDatastore.getUsers();
        List<UserData> usersData = userMapper.toDataList(users);

        if (includeRatings)
            for (UserData user : usersData) {
                List<RatingData> ratings = ratingClient.getAllRatingsByUserId(user.getUserId());
                user.setRatings(ratings);
            }

        return usersData;
    }

    @Override
    public UserData getUserById(String id, boolean includeRatings) {
        UserData user = userDatastore.getUserById(id)
                                     .map(userMapper::toData)
                                     .orElseThrow(() -> new UserAbsenceException(id));

        if (includeRatings) {
            List<RatingData> ratings = ratingClient.getAllRatingsByUserId(id);
            user.setRatings(ratings);
        }

        return user;
    }

    @Override
    public String updateUser(String id, UserData updatableUserData) {
        User updatableUser = userMapper.toEntity(updatableUserData);
        if (!userDatastore.updateUser(id, updatableUser))
            throw new UserUpsertionException("Something wrong happened while updating user!");
        return id;
    }

    @Override
    public String modifyUser(String id, String fields, UserData modifiableUserData) {
        User modifiableUser = userMapper.toEntity(modifiableUserData);
        if (!userDatastore.modifyUser(id, Arrays.stream(fields.split(",")).toList(), modifiableUser))
            throw new UserUpsertionException("Something wrong happened while updating user!");
        return id;
    }

    @Override
    public String modifyUser(String id, UserData modifiableUserData) {
        User modifiableUser = userMapper.toEntity(modifiableUserData);
        modifiableUser.setId(id);
        if (!userDatastore.modifyUser(id, modifiableUser))
            throw new UserUpsertionException("Something wrong happened while updating user!");
        return id;
    }

    @Override
    public String deleteUser(String id) {
        userDatastore.deleteUser(id);
        return id;
    }
}
