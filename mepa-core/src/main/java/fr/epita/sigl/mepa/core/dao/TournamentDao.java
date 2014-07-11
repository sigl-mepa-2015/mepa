package fr.epita.sigl.mepa.core.dao;

import java.util.List;

import fr.epita.sigl.mepa.core.domain.Tournament;

public interface TournamentDao {

    void create(Tournament tournament);

    void update(Tournament tournament);

    void delete(Tournament tournament);

    Tournament getById(Long id);

    Tournament getByType(String type);

    List<Tournament> getAll();

	int getComingGameById(Long id);

	int getEndedGameById(Long id);

}
