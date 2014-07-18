package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.PhaseDao;
import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.Phase;
import fr.epita.sigl.mepa.core.domain.Pool;
import fr.epita.sigl.mepa.core.service.PhaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
public class PhaseServiceImpl implements PhaseService {

    @Autowired
    private PhaseDao phaseDao;

    @Override
    public void createPhase(Phase phase) {
        this.phaseDao.create(phase);
    }

    @Override
    public void updatePhase(Phase phase) {
        this.phaseDao.update(phase);
    }

    @Override
    public void deletePhase(Phase phase) {
        this.phaseDao.delete(phase);
    }

    @Override
    @Transactional(readOnly = true)
    public Phase getPhases(String type) {
        return this.phaseDao.getByType(type);
    }

    @Override
    @Transactional(readOnly = true)
    public Phase getPhaseById(Long id) {
        return this.phaseDao.getById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Phase> getAllPhases() {
        return this.phaseDao.getAll();
    }

    @Override
    public String[] getPhaseEndDate(Phase phase) {
        String[] results = new String[2];
        Date d = new Date();
        float min = 0;
        long moy = 0;
        int games_ended = 0;
        int games_todo = 0;
        if (phase != null) {
            for (Pool p : phase.getPools()) {
                for (Game g : p.getGames()) {
                    if (g.getStatus() == Game.GameStatus.DONE) {
                        min += g.getDuration();
                        games_ended += 1;
                    } else {
                        games_todo += 1;
                    }
                }
            }
        }
        moy = (long) (min / games_ended);
        d.setTime(d.getTime() + moy * 60 * 1000 * games_todo);
        SimpleDateFormat dateformat = new SimpleDateFormat("EEEEEEEE dd MMMMMMMMM YYYY, kk:mm", Locale.FRANCE);
        results[0] = dateformat.format(d);
        results[1] = "" + moy;
        return results;
    }

}
