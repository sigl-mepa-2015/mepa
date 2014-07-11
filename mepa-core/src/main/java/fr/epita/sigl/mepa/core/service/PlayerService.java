package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Player;

import java.util.List;

/**
 * Created by SushiBalboha on 12/07/2014.
 */
public interface PlayerService {

    void createPlayer(Player player);

    void updatePlayer(Player player);

    void deletePlayer(Player player);

    Player getPlayerById(Long id);

    List<Player> getAllPlayers();

    List<Player> getAllPlayersByIdTeam(Long id);
}
