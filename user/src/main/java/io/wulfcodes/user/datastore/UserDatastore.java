package io.wulfcodes.user.datastore;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import io.wulfcodes.user.mapper.UserMapper;
import io.wulfcodes.user.model.data.UserData;
import io.wulfcodes.user.model.entity.User;
import io.wulfcodes.user.repository.UserRepository;

@Component
public class UserDatastore {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public UserData addUser(UserData newUserData) {
        try {
            User newUser = userMapper.toEntity(newUserData);
            newUser.setNewUser(true);
            System.out.println("Created User " + newUser);
            User savedUser = userRepository.save(newUser);
        } catch (Exception ex) {
            throw ex;
        }
        return newUserData;
    }

    public List<UserData> getUsers() {
        Iterable<User> allUsers = userRepository.findAll();
        return userMapper.toDataList(allUsers);
    }

    public Optional<UserData> getUserById(String id) {
        return userRepository.findById(id)
                             .map(userMapper::toData);

    }

    public boolean updateUser(String id, UserData updatedUserData) {
        try {
            User updatedUser = userMapper.toEntity(updatedUserData);
            return userRepository.existsById(id) && userRepository.update(id, updatedUser);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean modifyUser(String id, List<String> fields, UserData updatedUser) {
        if (!userRepository.existsById(id))
            return false;
        if (fields.isEmpty())
            return true;

        User user = userRepository.findById(id).get();
        String name = fields.contains("name") ? updatedUser.getName() : user.getName();
        String email = fields.contains("email") ? updatedUser.getEmail() : user.getEmail();
        String about = fields.contains("about") ? updatedUser.getAbout() : user.getAbout();

        String query = "UPDATE users SET u_name = ?, u_email = ?, u_about = ? WHERE u_id = ?";
        int rowsAffected = jdbcTemplate.update(query, name, email, about, id);

        return rowsAffected == 1;
    }

    public boolean modifyUser(String id, UserData modifiedUserData) {
        User modifiedUser = userMapper.toEntity(modifiedUserData);
        return userRepository.existsById(id) && userRepository.modify(modifiedUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
