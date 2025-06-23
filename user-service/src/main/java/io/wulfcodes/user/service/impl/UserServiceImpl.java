package io.wulfcodes.user.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.wulfcodes.common.model.data.HotelData;
import io.wulfcodes.common.model.data.RatingData;
import io.wulfcodes.common.model.exchange.HotelResponse;
import io.wulfcodes.common.exception.UserAbsenceException;
import io.wulfcodes.common.exception.UserUpsertionException;
import io.wulfcodes.common.model.data.UserData;
import io.wulfcodes.user.client.HotelClient;
import io.wulfcodes.user.client.RatingClient;
import io.wulfcodes.user.datastore.UserDatastore;
import io.wulfcodes.user.service.api.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDatastore userDatastore;

    @Autowired
    private RatingClient ratingClient;

    @Autowired
    private HotelClient hotelClient;

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
    public List<UserData> getUsers(boolean includeRatings) {
        List<UserData> users = userDatastore.getUsers();
        if (includeRatings)
            for (UserData user : users) {
                List<RatingData> ratings = ratingClient.getAllRatings(user.getUserId());
                user.setRatings(ratings);
            }
        return users;
    }

    @Override
    public UserData getUserById(String id, boolean includeRatings) {
        UserData user = userDatastore.getUserById(id)
                                     .orElseThrow(() -> new UserAbsenceException(id));
        if (includeRatings) {
            List<RatingData> ratings = ratingClient.getAllRatings(id);
            for (RatingData rating : ratings) {
                HotelResponse<HotelData> hotelResponse = hotelClient.fetchHotel(rating.getHotelId()).getBody();
                HotelData hotelData = hotelResponse.payload();
                rating.setHotelData(hotelData);
            }
            user.setRatings(ratings);
        }

        return user;
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
