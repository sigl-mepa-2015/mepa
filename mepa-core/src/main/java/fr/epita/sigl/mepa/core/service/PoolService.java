package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Pool;

import java.util.List;

/**
 * Created by maite on 11/07/14.
 */
public interface PoolService {
    void createPool(Pool p);

    void updatePool(Pool p);

    void deletePool(Pool p);

    Pool getPoolById(Long id);

    List<Pool> getAllPools();
}
