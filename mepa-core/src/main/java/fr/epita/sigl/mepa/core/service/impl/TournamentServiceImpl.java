package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.TournamentDao;
import fr.epita.sigl.mepa.core.domain.Phase;
import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Quentin on 17/07/2014.
 */
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
    public Tournament getTournamentById(Long id) {
        return this.tournamentDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tournament> getAllTournaments() {
        return this.tournamentDao.getAll();
    }

    @Override
    public String[] getTournamentEndDate(Tournament tournament) {
        Date max = new Date();
        String[] results = new String[2];
        max.setTime(0);
        Phase maxP = null;
        for (Phase p : tournament.getPhases()) {
            Date d = p.getEndDate();
            if (d.after(max)) {
                max = d;
                maxP = p;
            }
        }
        SimpleDateFormat dateformat = new SimpleDateFormat("EEEEEEEE dd MMMMMMMMM YYYY, kk:mm", Locale.FRANCE);
        results[0] = dateformat.format(max);
        results[1] = (maxP != null) ? "" + maxP.getAverage() : "";

        return results;
    }

}
