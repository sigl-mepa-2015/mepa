package fr.epita.sigl.mepa.front.controller.tournament;

import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.TournamentService;
import fr.epita.sigl.mepa.front.model.phase.RemovePhaseFormBean;
import fr.epita.sigl.mepa.front.model.team.RemoveTeamFormBean;
import fr.epita.sigl.mepa.front.model.tournament.RemoveTournamentFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Tournament controller
 * Created by Quentin on 08/07/2014.
 */
@Controller
@RequestMapping("/tournament")
@SessionAttributes({TournamentController.TOURNAMENT_MODEL_ATTRIBUTE})
public class TournamentController {
    protected static final String TOURNAMENT_MODEL_ATTRIBUTE = "tournaments";
    private static final Logger LOG = LoggerFactory.getLogger(TournamentController.class);
    private static final String ADD_TOURNAMENT_FORM_BEAN_MODEL_ATTRIBUTE = "tournament";
    private static final String REMOVE_TOURNAMENT_FORM_BEAN_MODEL_ATTRIBUTE = "removeTournamentFormBean";
    private static final String REMOVE_TEAM_FORM_BEAN_MODEL_ATTRIBUTE = "removeTeamFormBean";

    private static final String REMOVE_PHASE_FORM_BEAN_MODEL_ATTRIBUTE = "removePhaseFormBean";

    @Autowired
    private TournamentService tournamentService;

    /**
     * Default action : show all tournaments
     *
     * @param request The request
     * @return The View Name
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView showAllTournaments(HttpServletRequest request) {
        List<Tournament> allTournament = tournamentService.getAllTournaments();
        ModelAndView mv = new ModelAndView("/tournament/read/list");
        mv.addObject("tournaments", allTournament);

        String removed = request.getParameter("removed");
        String updated = request.getParameter("updated");
        String created = request.getParameter("created");
        String unknown = request.getParameter("unknown");
        String removedPhase = request.getParameter("removedPhase");
        String updatedPhase = request.getParameter("updatedPhase");
        String createdPhase = request.getParameter("createdPhase");
        String unknownPhase = request.getParameter("unknownPhase");

        if (created != null) {
            mv.addObject("created", created);
        } else if (updated != null) {
            mv.addObject("updated", updated);
        } else if (removed != null) {
            mv.addObject("removed", removed);
        } else if (unknown != null) {
            mv.addObject("unknown", unknown);
        } else if (createdPhase != null) {
            mv.addObject("createdPhase", createdPhase);
        } else if (updatedPhase != null) {
            mv.addObject("updatedPhase", updatedPhase);
        } else if (removedPhase != null) {
            mv.addObject("removedPhase", removedPhase);
        } else if (unknownPhase != null) {
            mv.addObject("unknownPhase", unknownPhase);
        }

        return mv;
    }

    /**
     * Show the creation form
     *
     * @return The view name
     */
    @RequestMapping(value = {"/form"}, method = RequestMethod.GET)
    public String showForm() {
        return "/tournament/create/form";
    }

    /**
     * Show the update form
     *
     * @param id The tournament id
     * @return The view name
     * @throws ServletRequestBindingException
     */
    @RequestMapping(value = "/form/{id}", method = RequestMethod.GET)
    public ModelAndView setupForm(@PathVariable("id") long id) throws ServletRequestBindingException {
        Tournament tournament = tournamentService.getTournamentById(id);
        if (tournament == null)
            tournament = new Tournament();
        return new ModelAndView("/tournament/create/form", ADD_TOURNAMENT_FORM_BEAN_MODEL_ATTRIBUTE, tournament);
    }

    /**
     * Process the creation or update form
     *
     * @param request       The request
     * @param modelMap      The variables used in the view
     * @param newTournament The tournament made of the sent form
     * @param result        The binding result
     * @return The view name
     */
    @RequestMapping(value = {"/create"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid Tournament newTournament, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/tournament/create/form";
        }
        if (newTournament.getId() == null) {
            this.tournamentService.createTournament(newTournament);
            return "redirect:?created=" + newTournament.getName();
        } else {
            this.tournamentService.updateTournament(newTournament);
            return "redirect:?updated=" + newTournament.getName();
        }
    }


    /**
     * Remove a tournament action.
     * It used a form to prevent double removing when user hit refresh on its web browser
     *
     * @param request                  The request
     * @param modelMap                 The variables used in the view
     * @param removeTournamentFormBean The form binding object
     * @param result                   The binding result
     * @return viewName
     */
    @RequestMapping(value = {"/remove"}, method = {RequestMethod.POST})
    public String removeTournament(HttpServletRequest request, ModelMap modelMap,
                                   @Valid RemoveTournamentFormBean removeTournamentFormBean, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/tournament/read/list";
        }
        Tournament removedTournament = this.tournamentService.getTournamentById(removeTournamentFormBean.getId());

        this.tournamentService.deleteTournament(removedTournament);
        return "redirect:?removed=" + removedTournament.getName();
    }

    /**
     * /**
     * Initialize "tournaments" model attribute
     *
     * @return an empty List of Tournaments.
     */
    @ModelAttribute(TOURNAMENT_MODEL_ATTRIBUTE)
    public List<Tournament> initTournaments() {
        return new ArrayList<Tournament>();
    }

    /**
     * Initialize "Tournament" model attribute
     *
     * @return a new Tournament.
     */
    @ModelAttribute(ADD_TOURNAMENT_FORM_BEAN_MODEL_ATTRIBUTE)
    public Tournament initTournament() {
        return new Tournament();
    }


    /**
     * Initialize "RemoveTournamentFormBean" model attribute
     *
     * @return a new RemoveTournamentFormBean.
     */
    @ModelAttribute(REMOVE_TOURNAMENT_FORM_BEAN_MODEL_ATTRIBUTE)
    public RemoveTournamentFormBean initRemoveTournamentFormBean() {
        return new RemoveTournamentFormBean();
    }
    
    @ModelAttribute(REMOVE_TEAM_FORM_BEAN_MODEL_ATTRIBUTE)
    public RemoveTeamFormBean initRemoveTeamFormBean() {
        return new RemoveTeamFormBean();
    }
    
    @ModelAttribute(REMOVE_PHASE_FORM_BEAN_MODEL_ATTRIBUTE)
    public RemovePhaseFormBean initRemovePhaseFormBean() {
        return new RemovePhaseFormBean();
    }
}