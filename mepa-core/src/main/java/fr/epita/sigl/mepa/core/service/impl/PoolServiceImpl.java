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
    private PoolDao pooldao;

    @Override
    public void createPool(Pool pool) {
        this.pooldao.create(pool);
    }

    @Override
    public void updatePool(Pool pool) {
        this.pooldao.update(pool);
    }

    @Override
    public void deletePool(Pool pool) {
        this.pooldao.delete(pool);
    }

    @Override
    @Transactional(readOnly = true)
    public Pool getPoolById(Long id) {
        return this.pooldao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pool> getAllPools() {
        return this.pooldao.getAll();
    }
}
