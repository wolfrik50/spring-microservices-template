package io.wulfcodes.user.datastore;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import io.wulfcodes.common.model.entity.User;
import io.wulfcodes.user.repository.UserRepository;

@Component
public class UserDatastore {
    private static final String MODIFY_USER_QUERY = "UPDATE users SET u_name = ?, u_email = ?, u_about = ? WHERE u_id = ?";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User addUser(User newUser) {
        try {
            return userRepository.save(newUser);
        } catch (Exception ex) {
            throw ex;
        }
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(String id) {
        return userRepository.findById(id);

    }

    public boolean updateUser(String id, User updatableUser) {
        try {
            return userRepository.existsById(id) && userRepository.update(id, updatableUser);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean modifyUser(String id, List<String> fields, User modifiableUser) {
        if (!userRepository.existsById(id))
            return false;
        if (fields.isEmpty())
            return true;

        User currentUser = userRepository.findById(id).get();
        String name = fields.contains("name") ? modifiableUser.getName() : currentUser.getName();
        String email = fields.contains("email") ? modifiableUser.getEmail() : currentUser.getEmail();
        String about = fields.contains("about") ? modifiableUser.getAbout() : currentUser.getAbout();

        int rowsAffected = jdbcTemplate.update(MODIFY_USER_QUERY, name, email, about, id);

        return rowsAffected == 1;
    }

    public boolean modifyUser(String id, User modifiableUser) {
        return userRepository.existsById(id) && userRepository.modify(modifiableUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }
}
