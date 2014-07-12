package fr.epita.sigl.mepa.core.service.impl;


import fr.epita.sigl.mepa.core.dao.GameDao;
import fr.epita.sigl.mepa.core.dao.PoolDao;
import fr.epita.sigl.mepa.core.dao.TournamentDao;
import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.Pool;
import fr.epita.sigl.mepa.core.domain.Tournament;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fr.epita.sigl.mepa.core.service.PoolService;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
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
    @Autowired
    private TournamentDao tournamentDao;
    @Autowired
    private GameDao gameDao;

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
    
    @Override
    public HashMap<Pool, JSONArray> aggregatePoolGameByTournament(Long id) throws JSONException
    {
    	HashMap<Pool, JSONArray> returnMap = new HashMap<Pool, JSONArray>();
    	    	
    	Tournament t = tournamentDao.getById(id);
    	for (Pool p : t.getPools())
    	{
    		JSONArray jArray = new JSONArray();
    		
    		JSONObject comingObject = new JSONObject();
    		comingObject.put("value", gameDao.countTodoGameByPoolId(id));
    		comingObject.put("color", "#F7464A");
    		comingObject.put("highlight", "#FF5A5E");
    		comingObject.put("label", "A venir");
    		
    		JSONObject progressObject = new JSONObject();
    		progressObject.put("value", gameDao.countProgressGameByPoolId(id));
    		progressObject.put("color", "#46BFBD");
    		progressObject.put("highlight", "#5AD3D1");
    		progressObject.put("label", "En cours");
    		
    		JSONObject endedObject = new JSONObject();
    		endedObject.put("value", gameDao.countEndedGameByPoolId(id));
    		endedObject.put("color", "#FDB45C");
    		endedObject.put("highlight", "#FFC870");
    		endedObject.put("label", "Fini");
    		
    		jArray.put(0, comingObject);
    		jArray.put(1, progressObject);
    		jArray.put(2, endedObject);
    		
    		returnMap.put(p, jArray);	
    	}
    	return returnMap;
    }
    
}
