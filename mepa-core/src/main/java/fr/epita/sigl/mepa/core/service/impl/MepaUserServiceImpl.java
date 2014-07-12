package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.domain.MepaUser;
import fr.epita.sigl.mepa.core.dao.MepaUserDao;
import fr.epita.sigl.mepa.core.service.MepaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SushiBalboha on 12/07/2014.
 */
@Service
@Transactional
public class MepaUserServiceImpl implements MepaUserService {

    @Autowired
    private MepaUserDao mepaUserDao;

    @Override
    @Transactional(readOnly = true)
    public void createMepaUser(MepaUser mepaUser){this.mepaUserDao.create(mepaUser);};

    @Override
    @Transactional(readOnly = true)
    public void updateMepaUser(MepaUser mepaUser){this.mepaUserDao.update(mepaUser);};

    @Override
    @Transactional(readOnly = true)
    public void deleteMepaUser(MepaUser mepaUser){this.mepaUserDao.delete(mepaUser);};

    @Override
    public MepaUser getMepaUserById(Long id){return this.mepaUserDao.getById(id);};

    @Override
    public List<MepaUser> getAllMepaUsers(){return this.mepaUserDao.getAll();};
}
