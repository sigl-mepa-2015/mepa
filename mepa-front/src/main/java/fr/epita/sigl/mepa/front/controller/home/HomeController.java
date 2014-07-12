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
        return "/home/home";
    }

}
