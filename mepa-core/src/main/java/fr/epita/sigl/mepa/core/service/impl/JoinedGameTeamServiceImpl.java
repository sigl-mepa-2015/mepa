package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.JoinedGameTeamDao;
import fr.epita.sigl.mepa.core.domain.JoinedGameTeam;
import fr.epita.sigl.mepa.core.service.JoinedGameTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JoinedGameTeamServiceImpl implements JoinedGameTeamService {

    @Autowired
    private JoinedGameTeamDao joinedGameTeamDao;

    @Override
    public void createJoinedGameTeam(JoinedGameTeam game) {
        this.joinedGameTeamDao.create(game);
    }

    @Override
    public void updateJoinedGameTeam(JoinedGameTeam game) {
        this.joinedGameTeamDao.update(game);
    }

    @Override
    public void deleteJoinedGameTeam(JoinedGameTeam game) {
        this.joinedGameTeamDao.delete(game);
    }

    @Override
    public JoinedGameTeam getJoinedGameById(Long id) {
       return this.joinedGameTeamDao.getJoinedGameById(id);
    }
//    @Override
//    @Transactional(readOnly = true)
//    public JoinedGameTeam getGameById(Long id) {
//        return this.gameDao.getById(id);
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<Game> getAllGames() {
//        return this.gameDao.getAll();
//    }
//
//    @Override
//    public List<Team> getTeams(Long id) {
//        List<JoinedGameTeam> joinedGameTeams = joinedGameTeamDao.getByGameId(id);
//        List<Team> teams = new ArrayList<>();
//        for (JoinedGameTeam j : joinedGameTeams) {
//            teams.add(j.getTeam());
//        }
//return teams;
//    }
}
