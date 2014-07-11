package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Pool;

import java.util.List;

public interface PoolDao {

    void create(Pool pool);

    void update(Pool pool);

    void delete(Pool pool);

    Pool getById(Long id);

    List<Pool> getAll();

}
