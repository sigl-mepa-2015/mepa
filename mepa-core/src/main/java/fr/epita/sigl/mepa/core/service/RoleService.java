package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Role;

import java.util.List;

public interface RoleService {

    void createRole(Role role);

    void updateRole(Role role);

    void deleteRole(Role role);

    Role getRoleById(Long id);

    Role getRoleByAuthority(String authority);

    List<Role> getAllRoles();

}