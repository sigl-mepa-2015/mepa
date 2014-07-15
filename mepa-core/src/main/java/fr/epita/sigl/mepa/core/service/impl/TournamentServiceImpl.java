package fr.epita.sigl.mepa.core.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;
import java.util.Locale;

import fr.epita.sigl.mepa.core.dao.TournamentDao;
import fr.epita.sigl.mepa.core.domain.*;
import fr.epita.sigl.mepa.core.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.epita.sigl.mepa.core.domain.Tournament;

@Service
@Transactional
public class TournamentServiceImpl implements TournamentService {

    static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs

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
    public String[] getTournamentEndDate (Tournament t)
    {
        String[] results = new String[2];
        Date d = new Date();
        float min = 0;
        long moy = 0;
        int games_ended = 0;
        int games_todo = 0;
        for (Pool p : t.getPools())
        {
            for (Game g : p.getGames())
            {
                if (g.getStatus() == Game.GameStatus.DONE)
                {
                    min += g.getDuration();
                    games_ended += 1;
                }
                else {
                    games_todo += 1;
                }
            }
        }
        moy = (long) (min / games_ended);
        d.setTime(d.getTime() + moy * ONE_MINUTE_IN_MILLIS * games_todo);
        SimpleDateFormat dateformat = new SimpleDateFormat("EEEEEEEE dd MMMMMMMMM YYYY, kk:mm", Locale.FRANCE);
        results[0] = dateformat.format(d);
        results[1] = "" + moy;
        return results;
    }

}
