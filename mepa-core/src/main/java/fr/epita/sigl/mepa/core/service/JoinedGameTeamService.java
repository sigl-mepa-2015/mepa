package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.JoinedGameTeam;
import fr.epita.sigl.mepa.core.domain.Team;

import java.util.List;

public interface JoinedGameTeamService {

    void createJoinedGameTeam(JoinedGameTeam joinedGameTeamService);

    void updateJoinedGameTeam(JoinedGameTeam joinedGameTeamService);

    void deleteJoinedGameTeam(JoinedGameTeam joinedGameTeamService);

    JoinedGameTeam getJoinedGameById(Long id);

//    JoinedGameTeam getJoinedGameTeamServiceById(Long id);
//
//    List<JoinedGameTeam> getAllJoinedGameTeamServices();
//
//    List<Team> getTeams(Long id);

}