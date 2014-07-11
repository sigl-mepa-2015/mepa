package fr.epita.sigl.mepa.core.service.impl;

import java.util.List;

import fr.epita.sigl.mepa.core.dao.PoolDao;
import fr.epita.sigl.mepa.core.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import fr.epita.sigl.mepa.core.domain.Pool;

/**
 * Created by maite on 11/07/14.
 */

@Service
@Transactional
public class PoolServiceImpl implements PoolService{
    @Autowired
    private PoolDao poolDao;

    @Override
    public void createPool(Pool p) {
        this.poolDao.create(p);
    }

    @Override
    public void updatePool(Pool p) {
        this.poolDao.update(p);
    }

    @Override
    public void deletePool(Pool p) {
        this.poolDao.delete(p);
    }

    @Override
    @Transactional(readOnly = true)
    public Pool getPoolById(Long id) {
        return this.poolDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pool> getAllPools() {
        return this.poolDao.getAll();
    }

}
