package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Pool;

import java.util.List;

/**
 * Created by maite on 11/07/14.
 */

public interface PoolService {

    void createPool(Pool pool);

    void updatePool(Pool pool);

    void deletePool(Pool pool);

    Pool getPoolById(Long id);

    List<Pool> getAllPools();

}
