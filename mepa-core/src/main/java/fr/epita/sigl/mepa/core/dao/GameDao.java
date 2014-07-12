package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.JoinedGameTeam;
import fr.epita.sigl.mepa.core.domain.Team;

import java.util.List;

public interface GameDao {

    void create(Game game);

    void update(Game game);

    void delete(Game game);

    Game getById(Long id);

    List<Game> getAll();

	Long getComingGameById(Long id);

	Long getEndedGameById(Long id);

	Long countTodoGameByPoolId(Long id);

	Long countProgressGameByPoolId(Long id);

	Long countEndedGameByPoolId(Long id);

}
