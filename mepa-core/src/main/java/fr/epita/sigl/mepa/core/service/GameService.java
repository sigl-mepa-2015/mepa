package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.Team;

import java.util.List;

public interface GameService {

    void createGame(Game game);

    void updateGame(Game game);

    void deleteGame(Game game);

    Game getGameById(Long id);

    List<Game> getAllGames();

    Long getComingGameByPhaseId(Long tournamentId);

    Long getEndedGameByPhaseId(Long tournamentId);

    List<Team> getTeams(Long id);

    Long getProgressGameByPhaseId(Long tournamentId);

    List<Game> getGameByTeam(Long teamId);

    Long countEndedGameByTeamId(Long teamId);

    Long countProgressGameByTeamId(Long teamId);

    Long countComingGameByTeamId(Long teamId);

    int getAverragePlayingTimeByTeam(Long teamId);

}