package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.PoolDao;
import fr.epita.sigl.mepa.core.domain.Pool;
import fr.epita.sigl.mepa.core.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PoolServiceImpl implements PoolService {

    @Autowired
    private PoolDao poolDao;

    @Override
    public void createTournament(Pool pool) {
        this.poolDao.create(pool);
    }

    @Override
    public void updateTournament(Pool pool) {
        this.poolDao.update(pool);
    }

    @Override
    public void deleteTournament(Pool pool) {
        this.poolDao.delete(pool);
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
