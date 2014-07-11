package fr.epita.sigl.mepa.core.service.impl;

import java.util.List;

import fr.epita.sigl.mepa.core.dao.TournamentDao;
import fr.epita.sigl.mepa.core.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.sigl.mepa.core.domain.Tournament;

@Service
@Transactional
public class TournamentServiceImpl implements TournamentService {

    @Autowired
    private TournamentDao tournamentDao;

    @Override
    public void createTournament(Tournament tournament) {
        this.tournamentDao.create(tournament);
    }

    @Override
    public void updateTournament(Tournament tournament) {
        this.tournamentDao.update(tournament);
    }

    @Override
    public void deleteTournament(Tournament tournament) {
        this.tournamentDao.delete(tournament);
    }

    @Override
    @Transactional(readOnly = true)
    public Tournament getTournamentByType(String type) {
        return this.tournamentDao.getByType(type);
    }

    @Override
    @Transactional(readOnly = true)
    public Tournament getTournamentById(Long id) {
        return this.tournamentDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tournament> getAllTournaments() {
        return this.tournamentDao.getAll();
    }
    
    @Override
    public int getComingGameByTournamentId(Long tournamentId)
    {
    	return this.tournamentDao.getComingGameById(tournamentId);
    }
    
    @Override
    public int getEndedGameByTournamentId(Long tournamentId)
    {
    	return this.tournamentDao.getEndedGameById(tournamentId);
    }
}
