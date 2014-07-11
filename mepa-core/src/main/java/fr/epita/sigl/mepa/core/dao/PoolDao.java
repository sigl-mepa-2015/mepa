package fr.epita.sigl.mepa.core.dao;

import java.util.List;

import fr.epita.sigl.mepa.core.domain.Pool;

/**
 * Created by maite on 11/07/14.
 */
public interface PoolDao {
    void create(Pool p);

    void update(Pool p);

    void delete(Pool p);

    Pool getById(Long id);

    List<Pool> getAll();
}
