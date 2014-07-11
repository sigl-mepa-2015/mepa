package fr.epita.sigl.mepa.front.utils;

import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.Pool;
import fr.epita.sigl.mepa.core.domain.Team;
import fr.epita.sigl.mepa.core.domain.Tournament;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexandre on 7/10/2014.
 */
public class CreateObject {

    // tournois complet : pool, game, equipes

    public Tournament createTournamentOnLaunch()
    {
        Tournament g = new Tournament("Tournois 1");
        Pool p1 = new Pool("Pool 1", g);
        Pool p2 = new Pool("Pool 2", g);
        Team t1 = new Team("team 1");
        Team t2 = new Team("team 2");
        Game g1 = new Game(t1, t2, p1);
        Game g2 = new Game(t1, t2, p1);

        Set<Team> teams = new HashSet<Team>();
        teams.add(t1);
        teams.add(t2);
        Set<Game> games = new HashSet<Game>();
        games.add(g1);
        games.add(g2);
        p1.setGames(games);
        p1.setTeams(teams);
        Set<Pool> pools = new HashSet<Pool>();
        pools.add(p1);
        pools.add(p2);
        g.setPools(pools);

        return g;
    }
}
