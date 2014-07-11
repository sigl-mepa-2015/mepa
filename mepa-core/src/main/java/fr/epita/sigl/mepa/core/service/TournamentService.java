package fr.epita.sigl.mepa.core.service;

import java.util.List;

import fr.epita.sigl.mepa.core.domain.Tournament;

public interface TournamentService {

    void createTournament(Tournament tournament);

    void updateTournament(Tournament tournament);

    void deleteTournament(Tournament tournament);

    Tournament getTournamentById(Long id);

    Tournament getTournamentByType(String type);

    List<Tournament> getAllTournaments();

	int getComingGameByTournamentId(Long tournamentId);

	int getEndedGameByTournamentId(Long tournamentId);

}