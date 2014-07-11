package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Pool;

import java.util.List;

public interface PoolService {

    void createTournament(Pool pool);

    void updateTournament(Pool pool);

    void deleteTournament(Pool pool);

    Pool getPoolById(Long id);

    List<Pool> getAllPools();

}