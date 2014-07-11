package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.User;
import java.util.List;

public interface UserDao {
    void create(User user);

    void update(User user);

    void delete(User user);

    User getById(Long id);

    List<User> getAll();
}