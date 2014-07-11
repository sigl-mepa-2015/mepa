package fr.epita.sigl.mepa.front.controller.home;

import fr.epita.sigl.mepa.core.domain.Pool;
import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.PoolService;
import fr.epita.sigl.mepa.core.service.TournamentService;
import fr.epita.sigl.mepa.core.service.impl.TournamentServiceImpl;
import fr.epita.sigl.mepa.front.utils.CreateObject;
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

    @RequestMapping(value = {"/", "/home"})
    public String home()
    {
        //CreateObject init = new CreateObject();
        //Tournament g = init.createTournamentOnLaunch();

        Tournament g = new Tournament();
        g.setName("Tournois 1");

        t.createTournament(g);

        Pool p1 = new Pool();
        p1.setName("Poule A");
        p1.setTournament(g);

        Pool p2 = new Pool();
        p2.setName("Poule Z");
        p2.setTournament(g);

        p.createPool(p1);
        p.createPool(p2);

        return "/home/home";
    }

}
