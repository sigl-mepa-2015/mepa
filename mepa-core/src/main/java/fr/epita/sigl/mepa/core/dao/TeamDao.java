package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Team;
import java.util.List;

/**
 * Created by david on 10/07/14.
 */
public interface TeamDao {
    void create(Team team);

    void update(Team team);

    void delete(Team team);

    Team getById(Long id);

    List<Team> getAll();
}
