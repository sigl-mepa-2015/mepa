package fr.epita.sigl.mepa.front.controller.tournament;

import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.TournamentService;
import fr.epita.sigl.mepa.front.model.tournament.AddTournamentFormBean;
import org.apache.commons.lang3.RandomStringUtils;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quentin on 08/07/2014.
 */
@Controller
@RequestMapping("/tournament/create")
@SessionAttributes({ CreateTournamentController.TOURNAMENT_MODEL_ATTRIBUTE})
public class CreateTournamentController {
    private static final Logger LOG = LoggerFactory.getLogger(CreateTournamentController.class);

    protected static final String TOURNAMENT_MODEL_ATTRIBUTE = "tournaments";
    private static final String ADD_TOURNAMENT_FORM_BEAN_MODEL_ATTRIBUTE = "addTournamentFormBean";

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
        modelMap.addAttribute(TOURNAMENT_MODEL_ATTRIBUTE, tournaments);

        return "/tournament/create/form";
    }

    /**
     * @param request
     * @param modelMap
     * @param addTournamentFormBean
     * @param result
     * @return
     */
    @RequestMapping(value = { "/add" }, method = { RequestMethod.POST })
    public String processForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid AddTournamentFormBean addTournamentFormBean, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/tournament/create/form";
        }
        Tournament newTournament = new Tournament();
        newTournament.setName(addTournamentFormBean.getName());
        this.tournamentService.createTournament(newTournament);
        modelMap.addAttribute(TOURNAMENT_MODEL_ATTRIBUTE, newTournament);

        return "/tournament/create/result";
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
     * Initialize "addCustomModelFormBean" model attribute
     *
     * @return a new AddTournamentFormBean.
     */
    @ModelAttribute(ADD_TOURNAMENT_FORM_BEAN_MODEL_ATTRIBUTE)
    public AddTournamentFormBean initAddCustomModelFormBean() {
        return new AddTournamentFormBean();
    }
}
