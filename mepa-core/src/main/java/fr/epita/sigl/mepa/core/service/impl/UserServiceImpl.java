package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.domain.User;
import fr.epita.sigl.mepa.core.dao.UserDao;
import fr.epita.sigl.mepa.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SushiBalboha on 12/07/2014.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(readOnly = true)
    public void createUser(User user){this.userDao.create(user);};

    @Override
    @Transactional(readOnly = true)
    public void updateUser(User user){this.userDao.update(user);};

    @Override
    @Transactional(readOnly = true)
    public void deleteUser(User user){this.userDao.delete(user);};

    @Override
    public User getUserById(Long id){return this.userDao.getById(id);};

    @Override
    public List<User> getAllUsers(){return this.userDao.getAll();};
}
