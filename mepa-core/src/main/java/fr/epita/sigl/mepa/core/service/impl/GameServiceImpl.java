package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.GameDao;
import fr.epita.sigl.mepa.core.dao.PoolDao;
import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.Pool;
import fr.epita.sigl.mepa.core.service.GameService;
import fr.epita.sigl.mepa.core.service.PoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDao gameDao;

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
}
