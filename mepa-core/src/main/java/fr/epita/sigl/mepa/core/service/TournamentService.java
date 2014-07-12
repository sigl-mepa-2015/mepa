package fr.epita.sigl.mepa.core.service;

import java.util.List;
import java.util.Date;

import fr.epita.sigl.mepa.core.domain.Tournament;

public interface TournamentService {

    void createTournament(Tournament tournament);

    void updateTournament(Tournament tournament);

    void deleteTournament(Tournament tournament);

    Tournament getTournamentById(Long id);

    Tournament getTournamentByType(String type);

    List<Tournament> getAllTournaments();

    Date getTournamentEndDate (Tournament t);
}