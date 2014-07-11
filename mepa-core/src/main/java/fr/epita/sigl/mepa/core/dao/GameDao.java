package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Game;

import java.util.List;

public interface GameDao {

    void create(Game game);

    void update(Game game);

    void delete(Game game);

    Game getById(Long id);

    List<Game> getAll();

	int getComingGameById(Long id);

	int getEndedGameById(Long id);

}
