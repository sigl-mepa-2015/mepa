package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Tournament;

import java.util.List;

/**
 * Created by Quentin on 17/07/2014.
 */
public interface TournamentDao {

    void create(Tournament tournament);

    void update(Tournament tournament);

    void delete(Tournament tournament);

    Tournament getById(Long id);

    List<Tournament> getAll();
}
