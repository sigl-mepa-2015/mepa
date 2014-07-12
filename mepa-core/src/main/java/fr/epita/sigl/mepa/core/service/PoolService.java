package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Pool;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;

import java.util.HashMap;
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

	HashMap<Pool, JSONArray> aggregatePoolGameByTournament(Long id) throws JSONException;

}
