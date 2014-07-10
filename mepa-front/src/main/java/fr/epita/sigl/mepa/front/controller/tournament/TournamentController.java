package fr.epita.sigl.mepa.front.controller.tournament;

import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.TournamentService;
import fr.epita.sigl.mepa.front.model.tournament.RemoveTournamentFormBean;
import fr.epita.sigl.mepa.front.model.tournament.AddTournamentFormBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quentin on 08/07/2014.
 */
@Controller
@RequestMapping("/tournament/")
@SessionAttributes({ TournamentController.TOURNAMENT_MODEL_ATTRIBUTE})
public class TournamentController {
    private static final Logger LOG = LoggerFactory.getLogger(TournamentController.class);

    protected static final String TOURNAMENT_MODEL_ATTRIBUTE = "tournaments";
    private static final String ADD_TOURNAMENT_FORM_BEAN_MODEL_ATTRIBUTE = "addTournamentFormBean";
    private static final String REMOVE_TOURNAMENT_FORM_BEAN_MODEL_ATTRIBUTE = "removeTournamentFormBean";

    @Autowired
    private TournamentService tournamentService;

    @RequestMapping(value = { "/", "/form" })
    public String showForm(HttpServletRequest request, ModelMap modelMap) {

        // Get tournaments data from database
        List<Tournament> tournaments = this.tournamentService.getAllTournaments();
        if (LOG.isDebugEnabled()) {
            LOG.debug("There are {} tournaments in database", tournaments.size());
        }

        // Update model attribute "tournaments", to use it in JSP
        modelMap.addAttribute("tournaments", tournaments);

        return "/tournament/read/list";
    }

    /**
     * @param request
     * @param modelMap
     * @param addTournamentFormBean
     * @param result
     * @return
     */
    @RequestMapping(value = { "/create" }, method = { RequestMethod.POST })
    public String processForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid AddTournamentFormBean addTournamentFormBean, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/tournament/read/list";
        }
        Tournament newTournament = new Tournament();
        newTournament.setName(addTournamentFormBean.getName());
        this.tournamentService.createTournament(newTournament);
        modelMap.addAttribute("tournament", newTournament);

        return "/tournament/create/result";
    }
    
    @RequestMapping(value="/all", method=RequestMethod.GET)
    public ModelAndView getAllTournament()
    {
    	List<Tournament> allTournament = tournamentService.getAllTournaments();
    	ModelAndView mv = new ModelAndView("/tournament/read/list");
    	mv.addObject("tournaments", allTournament);
    	
    	return mv;
    }

    /**
     * @param request
     * @param modelMap
     * @param result
     * @return
     */
    @RequestMapping(value = { "/remove" }, method = { RequestMethod.POST })
    public String removeTournament(HttpServletRequest request, ModelMap modelMap,
                                   @Valid RemoveTournamentFormBean removeTournamentFormBean, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/tournament/read/list";
        }
        Tournament removedTournament = this.tournamentService.getTournamentById(removeTournamentFormBean.getId());
        this.tournamentService.deleteTournament(removedTournament);
        modelMap.addAttribute("tournament", removedTournament);
        return "/tournament/remove/result";
    }

    /**
     * Initialize "tournaments" model attribute
     *
     * @return an empty List of Tournaments.
     */
    @ModelAttribute(TOURNAMENT_MODEL_ATTRIBUTE)
    public List<Tournament> initTournaments() {
        return new ArrayList<Tournament>();
    }

    /**
     * Initialize "AddTournamentFormBean" model attribute
     *
     * @return a new AddTournamentFormBean.
     */
    @ModelAttribute(ADD_TOURNAMENT_FORM_BEAN_MODEL_ATTRIBUTE)
    public AddTournamentFormBean initAddTournamentFormBean() {
        return new AddTournamentFormBean();
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
}
