package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.TeamDao;
import fr.epita.sigl.mepa.core.domain.Team;
import fr.epita.sigl.mepa.core.service.TeamService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by david on 10/07/14.
 */

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Override
    public void createTeam(Team team) {
        this.teamDao.create(team);
    }

    @Override
    public void updateTeam(Team team) {
        this.teamDao.update(team);
    }

    @Override
    public void deleteTeam(Team team) {
        this.deleteTeam(team);
    }

    @Override
    @Transactional(readOnly = true)
    public Team getTeamById(Long id) {
        return this.teamDao.getById(id);
    }

    @Override
    public List<Team> getAllTeams() {
        return this.teamDao.getAll();
    }

    @Override
    public List<Team> getAllOrderTeamsByTournament(Long tournamentID)
    {
        return this.teamDao.getOrderByTournamentId(tournamentID);
    }

    @Override
    public HashMap<String, Long> getRangeByTounrnamentId(Long tournamentID, Team team)
    {
        HashMap<String, Long> result = new HashMap<String, Long>();

        List<Team> allOrder = this.getAllOrderTeamsByTournament(tournamentID);
        Long myRangeGlob = (long) 0;
        Long rangeGlob = (long) 0;
        Long rangePool = (long) 0;
        Long myRangePool = (long) 0;
        int i = 0;
        Boolean get_it = false;

        for (Team t : allOrder)
        {
            rangeGlob += 1;
            if (t.getPool().getId() == team.getPool().getId())
                rangePool += 1;
            if (t.getId() == team.getId())
            {
               if ((i != 0) && ((t.getWinGame() != allOrder.get(i-1).getWinGame())
                    || (t.getDrawGame() != allOrder.get(i-1).getDrawGame())
                    || (t.getLoseGame() != allOrder.get(i-1).getLoseGame()))) {
                   myRangeGlob = rangeGlob;
                   myRangePool = rangePool;
               }
                if (myRangePool == 0)
                    myRangePool += 1;
               get_it = true;
               result.put("global", myRangeGlob);
                result.put("pool", myRangePool);
            }
            else
            {
                if ((i == 0) && ((t.getWinGame() != team.getWinGame())
                        || (t.getDrawGame() != team.getDrawGame())
                        || (t.getLoseGame() != team.getLoseGame())))
                {
                    myRangeGlob = rangeGlob;
                    result.put("globalPrev", t.getId());
                    result.put("globalPrevRange", myRangeGlob);
                    if (t.getPool().getId() == team.getPool().getId())
                    {
                        myRangePool = rangePool;
                        result.put("poolPrev", t.getId());
                        result.put("poolPrevRange", myRangePool);
                    }
                }
                else
                {
                    if (((t.getWinGame() != team.getWinGame())
                            || (t.getDrawGame() != team.getDrawGame())
                            || (t.getLoseGame() != team.getLoseGame())) &&
                            ((t.getWinGame() != allOrder.get(i-1).getWinGame())
                            || (t.getDrawGame() != allOrder.get(i-1).getDrawGame())
                            || (t.getLoseGame() != allOrder.get(i-1).getLoseGame())))
                    {
                        myRangeGlob = rangeGlob;
                        if (get_it == false) {
                            result.put("globalPrev", t.getId());
                            result.put("globalPrevRange", rangeGlob);
                            if (t.getPool().getId() == team.getPool().getId())
                            {
                                myRangePool = rangePool;
                                result.put("poolPrev", t.getId());
                                result.put("poolPrevRange", rangePool);
                            }
                        }
                        else {
                            if (result.get("globalNext") == null) {
                                result.put("globalNext", t.getId());
                                result.put("globalNextRange", rangeGlob);
                            }
                            if (t.getPool().getId() == team.getPool().getId())
                            {
                                myRangePool = rangePool;
                                result.put("poolNext", t.getId());
                                result.put("poolNextRange", rangePool);
                                break;
                            }
                        }
                    }
                    if ((t.getWinGame() == team.getWinGame())
                            && (t.getDrawGame() == team.getDrawGame())
                            && (t.getLoseGame() == team.getLoseGame()))
                    {
                        myRangeGlob = rangeGlob;
                        myRangePool = rangePool;
                    }
                }

            }
            i++;
        }
    	/*for (Team t : allOrder)
    	{
    		if (i == 0 || 
    				t.getWinGame() != allOrder.get(i-1).getWinGame() ||
    				t.getDrawGame() != allOrder.get(i-1).getDrawGame() ||
    				t.getLoseGame() != allOrder.get(i-1).getLoseGame())
    		{
    			rangeGlob++;
    			if ((get_it == false) && (t.getId() != team.getId()))
    				result.put("globalPrev", t.getId());
    			else {
                    if ((result.get("globalNext") == null) && (t.getId() != team.getId()))
                        result.put("globalNext", t.getId());
                }
    			if (t.getPool().getId() == team.getPool().getId())
    			{
    				rangePool++;
    				if ((get_it == false) && (t.getId() != team.getId()))
    					result.put("poolPrev", t.getId());
    				else {
                        result.put("poolNext", t.getId());
                        break;
                    }
    			}
    			
    		}
    		if (t.getId() == team.getId())
    		{
    			result.put("global", rangeGlob);
    			result.put("pool", rangePool);
    			get_it = true;
    		}
    		i++;
    	}*/

        return result;
    }
}
