package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.TeamDao;
import fr.epita.sigl.mepa.core.domain.Team;
import fr.epita.sigl.mepa.core.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
