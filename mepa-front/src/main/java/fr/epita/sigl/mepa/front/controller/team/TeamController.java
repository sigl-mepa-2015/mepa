package fr.epita.sigl.mepa.front.controller.team;

import fr.epita.sigl.mepa.core.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by david on 10/07/14.
 */
@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @RequestMapping(value="/form", method=RequestMethod.GET)
    public String showCreationForm(){

        return "/team/create";
    }

    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public String editTeam(){

        return "/team/edit";
    }
}
