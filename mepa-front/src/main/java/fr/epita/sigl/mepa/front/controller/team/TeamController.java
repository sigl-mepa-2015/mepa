package fr.epita.sigl.mepa.front.controller.team;

import fr.epita.sigl.mepa.core.domain.Team;
import fr.epita.sigl.mepa.core.service.TeamService;
import fr.epita.sigl.mepa.front.model.team.AddTeamFormBean;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by david on 10/07/14.
 */
@Controller
@RequestMapping("/team")
@SessionAttributes({ TeamController.TEAM_MODEL_ATTRIBUTE})
public class TeamController {

    private static final Logger LOG = LoggerFactory.getLogger(TeamController.class);

    protected static final String TEAM_MODEL_ATTRIBUTE = "tournaments";
    private static final String ADD_TEAM_FORM_BEAN_MODEL_ATTRIBUTE = "addTeamFormBean";
    private static final String EDIT_TEAM_FORM_BEAN_MODEL_ATTRIBUTE = "editTeamFormBean";
    
    @Autowired
    private TeamService teamService;

    @RequestMapping(value = {"/form" })
    public String showCreationForm(){
        return "/team/create/form";
    }

    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView editTeam(@RequestParam("teamID") Long teamID){

        Team team = teamService.getTeamById(teamID);
        System.out.println(teamID);
        ModelAndView mv = new ModelAndView("/team/edit/form");
        mv.addObject("t", team);
        return mv;

    }
    
    
     @RequestMapping(value = { "/create" }, method = { RequestMethod.POST })
    public String processForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid AddTeamFormBean addTeamFormBean, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/team/read/list";
        }
        Team newTeam = new Team(addTeamFormBean.getName());
        this.teamService.createTeam(newTeam);
        modelMap.addAttribute("team", newTeam);
        
    	List<Team> allTeam = teamService.getAllTeams();
    	modelMap.addAttribute("teams", allTeam);

        return "/team/read/list";
    }

    @RequestMapping(value = { "/edit/form" }, method = { RequestMethod.POST })
    public String processEditForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid AddTeamFormBean addTeamFormBean, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/team/read/list";
        }
        Team newTeam = this.teamService.getTeamById(addTeamFormBean.getId());
        newTeam.setName(addTeamFormBean.getName());
        this.teamService.updateTeam(newTeam);
        modelMap.addAttribute("team", newTeam);

        List<Team> allTeam = teamService.getAllTeams();
        modelMap.addAttribute("teams", allTeam);

        return "/team/read/list";
    }
    
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ModelAndView getAllTeam()
    {
    	List<Team> allTeam = teamService.getAllTeams();
    	ModelAndView mv = new ModelAndView("/team/read/list");
    	mv.addObject("teams", allTeam);
    	mv.addObject("team", null);
    	
    	return mv;
    }
    
        @ModelAttribute(TEAM_MODEL_ATTRIBUTE)
    public List<Team> initTeams() {
        return new ArrayList<Team>();
    }

    @ModelAttribute(ADD_TEAM_FORM_BEAN_MODEL_ATTRIBUTE)
    public AddTeamFormBean initAddTeamFormBean() {
        return new AddTeamFormBean();
    }

    

}
