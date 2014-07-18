package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.GameDao;
import fr.epita.sigl.mepa.core.dao.JoinedGameTeamDao;
import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.Game.GameStatus;
import fr.epita.sigl.mepa.core.domain.JoinedGameTeam;
import fr.epita.sigl.mepa.core.domain.Team;
import fr.epita.sigl.mepa.core.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

    @Autowired
    private JoinedGameTeamDao joinedGameTeamDao;

    @Override
    public void createGame(Game game) {
        this.gameDao.create(game);
    }

    @Override
    public void updateGame(Game game) {
        this.gameDao.update(game);
    }

    @Override
    public void deleteGame(Game game) {
        this.gameDao.delete(game);
    }

    @Override
    @Transactional(readOnly = true)
    public Game getGameById(Long id) {
        return this.gameDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Game> getAllGames() {
        return this.gameDao.getAll();
    }

    @Override
    public Long getComingGameByPhaseId(Long phaseId) {
        return this.gameDao.getComingGameById(phaseId);
    }

    @Override
    public Long getProgressGameByPhaseId(Long phaseId) {
        return this.gameDao.getProgressGameById(phaseId);
    }

    @Override
    public Long getEndedGameByPhaseId(Long phaseId) {
        return this.gameDao.getEndedGameById(phaseId);
    }

    @Override
    public List<Team> getTeams(Long id) {
        List<JoinedGameTeam> joinedGameTeams = joinedGameTeamDao.getByGameId(id);
        List<Team> teams = new ArrayList<>();
        for (JoinedGameTeam j : joinedGameTeams) {
            teams.add(j.getTeam());
        }
        return teams;
    }

    @Override
    public List<Game> getGameByTeam(Long teamId) {
        return this.gameDao.getGameByTeamId(teamId);
    }

    @Override
    public Long countComingGameByTeamId(Long teamId) {
        return this.gameDao.countTodoGameByTeamId(teamId);
    }

    @Override
    public Long countProgressGameByTeamId(Long teamId) {
        return this.gameDao.countProgressGameByTeamId(teamId);
    }

    @Override
    public Long countEndedGameByTeamId(Long teamId) {
        return this.gameDao.countEndedGameByTeamId(teamId);
    }

    @Override
    public int getAverragePlayingTimeByTeam(Long teamId) {
        List<Game> list = gameDao.getGameByTeamId(teamId);

        int sum = 0;
        int i = 0;

        for (Game g : list) {
            if (g.getStatus() == GameStatus.DONE) {
                sum += g.getDuration();
                i++;
            }
        }

        return sum / i;
    }
}
