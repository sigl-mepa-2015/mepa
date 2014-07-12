package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.Role;

import java.util.List;

public interface RoleDao {

    void create(Role role);

    void update(Role role);

    void delete(Role role);

    Role getById(Long id);

    List<Role> getAll();

}
