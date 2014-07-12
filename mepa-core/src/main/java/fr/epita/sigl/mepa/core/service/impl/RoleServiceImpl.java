package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.RoleDao;
import fr.epita.sigl.mepa.core.domain.Role;
import fr.epita.sigl.mepa.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public void createRole(Role role) {
        this.roleDao.create(role);
    }

    @Override
    public void updateRole(Role role) {
        this.roleDao.update(role);
    }

    @Override
    public void deleteRole(Role role) {
        this.roleDao.delete(role);
    }

    @Override
    @Transactional(readOnly = true)
    public Role getRoleById(Long id) {
        return this.roleDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return this.roleDao.getAll();
    }
}
