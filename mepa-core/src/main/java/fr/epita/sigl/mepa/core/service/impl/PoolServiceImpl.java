package fr.epita.sigl.mepa.core.service.impl;


import fr.epita.sigl.mepa.core.dao.PoolDao;
import fr.epita.sigl.mepa.core.domain.Pool;

import java.util.List;

import fr.epita.sigl.mepa.core.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * Created by maite on 11/07/14.
 */
import java.util.List;

@Service
@Transactional
public class PoolServiceImpl implements PoolService {

    @Autowired
    private PoolDao poolDao;

    public PoolDao getPoolDao() {
        return poolDao;
    }

    public void setPoolDao(PoolDao poolDao) {
        this.poolDao = poolDao;
    }

    @Override
    @Transactional(readOnly = true)
    public Pool getPoolById(Long id) {
        return this.poolDao.getById(id);
    }

    @Override
    public void createPool(Pool pool) {
        this.poolDao.create(pool);
    }

    @Override
    public void updatePool(Pool pool) {
        this.poolDao.update(pool);
    }

    @Override
    public void deletePool(Pool pool) {
        this.poolDao.delete(pool);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Pool> getAllPools() {

        return this.poolDao.getAll();

    }
}
