package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.Pool;

import java.util.List;

public interface GameService {

    void createGame(Game game);

    void updateGame(Game game);

    void deleteGame(Game game);

    Game getGameById(Long id);

    List<Game> getAllGames();

	int getComingGameByTournamentId(Long tournamentId);

	int getEndedGameByTournamentId(Long tournamentId);

}