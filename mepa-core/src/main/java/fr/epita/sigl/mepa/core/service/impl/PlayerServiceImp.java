package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.PlayerDao;
import fr.epita.sigl.mepa.core.domain.Player;
import fr.epita.sigl.mepa.core.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SushiBalboha on 12/07/2014.
 */

@Service
@Transactional
public class PlayerServiceImp implements PlayerService {

    //@Autowired
    private PlayerDao playerDao;

    @Override
    public void createPlayer(Player player){this.playerDao.create(player);};

    @Override
    public void updatePlayer(Player player){this.playerDao.update(player);};

    @Override
    public void deletePlayer(Player player){this.playerDao.delete(player);};

    @Override
    public Player getPlayerById(Long id){return this.playerDao.getById(id);};

    @Override
    public List<Player> getAllPlayers(){return this.playerDao.getAll();};
}
