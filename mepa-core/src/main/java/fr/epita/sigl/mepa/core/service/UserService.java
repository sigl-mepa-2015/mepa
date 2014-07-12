package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.User;

import java.util.List;

/**
 * Created by SushiBalboha on 12/07/2014.
 */
public interface UserService {
    void createUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();
}
