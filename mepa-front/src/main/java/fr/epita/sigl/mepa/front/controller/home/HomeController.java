package fr.epita.sigl.mepa.front.controller.home;


import java.util.HashSet;
import java.util.Set;

import fr.epita.sigl.mepa.core.domain.*;
import fr.epita.sigl.mepa.core.domain.Game.GameStatus;
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

    @RequestMapping(value = {"/", "/home"})
    public String home()
    {
        return "/home/home";
    }

//    @PostConstruct
//    public void init()
//    {
//        Tournament g = new Tournament("coucou");
//        t.createTournament(g);
//    }

}
