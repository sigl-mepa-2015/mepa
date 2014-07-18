package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Tournament;

import java.util.List;

/**
 * Created by Quentin on 17/07/2014.
 */
public interface TournamentService {

    void createTournament(Tournament tournament);

    void updateTournament(Tournament tournament);

    void deleteTournament(Tournament tournament);

    Tournament getTournamentById(Long id);

    List<Tournament> getAllTournaments();

    String[] getTournamentEndDate(Tournament tournament);
}
