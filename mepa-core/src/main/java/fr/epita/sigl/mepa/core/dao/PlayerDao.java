package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Player;
import java.util.List;

/**
 * Created by SushiBalboha on 12/07/2014.
 */
public interface PlayerDao{
    void create(Player player);

    void update(Player player);

    void delete(Player player);

    Player getById(Long id);

    List<Player> getAll();

    List<Player> getAllOrderPlayerByTournament(long tournamentid);
}
