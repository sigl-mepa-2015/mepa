package fr.epita.sigl.mepa.front.controller.team;

import fr.epita.sigl.mepa.core.domain.Phase;
import fr.epita.sigl.mepa.core.domain.Team;
import fr.epita.sigl.mepa.core.service.PhaseService;
import fr.epita.sigl.mepa.core.service.TeamService;
import fr.epita.sigl.mepa.front.model.team.AddTeamFormBean;
import fr.epita.sigl.mepa.front.model.team.RemoveTeamFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 10/07/14.
 */
@Controller
@RequestMapping("/team")
@SessionAttributes({TeamController.TEAM_MODEL_ATTRIBUTE})
public class TeamController {

    protected static final String TEAM_MODEL_ATTRIBUTE = "phases";
    private static final Logger LOG = LoggerFactory.getLogger(TeamController.class);
    private static final String ADD_TEAM_FORM_BEAN_MODEL_ATTRIBUTE = "addTeamFormBean";
    private static final String REMOVE_TEAM_FORM_BEAN_MODEL_ATTRIBUTE = "removeTeamFormBean";

    @Autowired
    private TeamService teamService;
    @Autowired
    private PhaseService phaseService;

    @RequestMapping(value = {"/form"})
    public ModelAndView showCreationForm(@RequestParam("phaseID") Long phaseID) {
        Phase phase = phaseService.getPhaseById(phaseID);
        ModelAndView mv = new ModelAndView("/team/create/form");
        mv.addObject("phase", phase);
        return mv;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView editTeam(@RequestParam("teamID") Long teamID) {

        Team team = teamService.getTeamById(teamID);
        ModelAndView mv = new ModelAndView("/team/edit/form");
        mv.addObject("team", team);
        return mv;

    }

    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public ModelAndView detailTeam(@PathVariable("id") Long teamID) {

        Team team = teamService.getTeamById(teamID);
        ModelAndView mv = new ModelAndView("/team/detail");
        mv.addObject("team", team);
        return mv;

    }


    @RequestMapping(value = {"/create"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid AddTeamFormBean addTeamFormBean, BindingResult result,
                              @RequestParam("phaseID") Long phaseID) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            Phase phase = phaseService.getPhaseById(phaseID);
            return "redirect:/phase/view/" + phase.getId();
        }
        Team newTeam = new Team(addTeamFormBean.getName());
        Phase phase = phaseService.getPhaseById(phaseID);

        newTeam.setPhase(phase);
        phaseService.updatePhase(phase);
        this.teamService.createTeam(newTeam);
        modelMap.addAttribute("team", newTeam);

        List<Team> allTeam = teamService.getAllTeams();
        modelMap.addAttribute("teams", allTeam);

        return "redirect:/phase/view/" + phase.getId();
    }

    @RequestMapping(value = {"/edit"}, method = {RequestMethod.POST})
    public String processEditForm(HttpServletRequest request, ModelMap modelMap,
                                  @Valid AddTeamFormBean addTeamFormBean, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            Team newTeam = this.teamService.getTeamById(addTeamFormBean.getId());
            return "redirect:/phase/view/" + newTeam.getPhase().getId();
        }
        Team newTeam = this.teamService.getTeamById(addTeamFormBean.getId());
        newTeam.setName(addTeamFormBean.getName());
        this.teamService.updateTeam(newTeam);
        modelMap.addAttribute("team", newTeam);

        List<Team> allTeam = teamService.getAllTeams();
        modelMap.addAttribute("teams", allTeam);

        return "redirect:/phase/view/" + newTeam.getPhase().getId();
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ModelAndView getAllTeam() {
        List<Team> allTeam = teamService.getAllTeams();
        ModelAndView mv = new ModelAndView("/team/read/list");
        mv.addObject("teams", allTeam);
        mv.addObject("team", null);

        return mv;
    }

    @RequestMapping(value = {"/remove"}, method = {RequestMethod.POST})
    public String removeTeam(HttpServletRequest request, ModelMap modelMap,
                             @Valid RemoveTeamFormBean removeTeamFormBean, BindingResult result) {

        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/tournament/read/list";
        }

        Team deleteTeam = this.teamService.getTeamById(removeTeamFormBean.getId());
        long idtournament = deleteTeam.getPhase().getId();

        if (deleteTeam.getPool() == null) {
            this.teamService.deleteTeam(deleteTeam);
            List<Team> allTeam = teamService.getAllTeams();
            modelMap.addAttribute("teams", allTeam);
        }

        return "redirect:/phase/view/" + idtournament;
    }

    @ModelAttribute(TEAM_MODEL_ATTRIBUTE)
    public List<Team> initTeams() {
        return new ArrayList<Team>();
    }

    @ModelAttribute(ADD_TEAM_FORM_BEAN_MODEL_ATTRIBUTE)
    public AddTeamFormBean initAddTeamFormBean() {
        return new AddTeamFormBean();
    }

    @ModelAttribute(REMOVE_TEAM_FORM_BEAN_MODEL_ATTRIBUTE)
    public RemoveTeamFormBean initRemoveTeamFormBean() {
        return new RemoveTeamFormBean();
    }
}
