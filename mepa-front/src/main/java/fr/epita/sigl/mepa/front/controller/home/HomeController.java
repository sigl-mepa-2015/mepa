package fr.epita.sigl.mepa.front.controller.home;

import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.Pool;
import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.GameService;
import fr.epita.sigl.mepa.core.service.PoolService;
import fr.epita.sigl.mepa.core.service.TournamentService;
import fr.epita.sigl.mepa.core.service.impl.TournamentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;

@Controller
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private TournamentService t;
    @Autowired
    private PoolService p;
    @Autowired
    private GameService gs;

    @RequestMapping(value = {"/", "/home"})
    public String home()
    {
        //CreateObject init = new CreateObject();
        //Tournament g = init.createTournamentOnLaunch();

        Tournament tournament = new Tournament();
        tournament.setName("Tournois 1");

        t.createTournament(tournament);

        Pool p1 = new Pool();
        p1.setName("Poule A");
        p1.setTournament(tournament);

        Pool p2 = new Pool();
        p2.setName("Poule Z");
        p2.setTournament(tournament);

        p.createPool(p1);
        p.createPool(p2);

        Game g1 = new Game();
        g1.setPool(p1);
        g1.setStatus(Game.GameStatus.TODO);
        gs.createGame(g1);

        Game g2 = new Game();
        g2.setPool(p2);
        g2.setStatus(Game.GameStatus.DONE);
        g2.setResultTeam1(2);
        g2.setResultTeam2(3);
        gs.createGame(g2);

        return "/home/home";
    }

}
