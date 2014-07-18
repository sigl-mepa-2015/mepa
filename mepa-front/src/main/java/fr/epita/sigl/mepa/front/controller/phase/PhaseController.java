package fr.epita.sigl.mepa.front.controller.phase;

import fr.epita.sigl.mepa.core.domain.Phase;
import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.PhaseService;
import fr.epita.sigl.mepa.core.service.TournamentService;
import fr.epita.sigl.mepa.front.model.phase.RemovePhaseFormBean;
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
 * Phase controller
 * Created by Quentin on 08/07/2014.
 */
@Controller
@RequestMapping("/phase")
@SessionAttributes({PhaseController.PHASE_MODEL_ATTRIBUTE, PhaseController.VIEW_PHASE_FORM_BEAN_MODEL_ATTRIBUTE})
public class PhaseController {
    protected static final String PHASE_MODEL_ATTRIBUTE = "phases";
    protected static final String VIEW_PHASE_FORM_BEAN_MODEL_ATTRIBUTE = "phaseView";
    private static final Logger LOG = LoggerFactory.getLogger(PhaseController.class);
    private static final String ADD_PHASE_FORM_BEAN_MODEL_ATTRIBUTE = "phase";
    private static final String REMOVE_PHASE_FORM_BEAN_MODEL_ATTRIBUTE = "removePhaseFormBean";

    @Autowired
    private PhaseService phaseService;

    @Autowired
    private TournamentService tournamentService;

    /**
     * Default action : show all phases
     *
     * @param request The request
     * @return The View Name
     */
    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public ModelAndView showAllPhases(HttpServletRequest request) {
        List<Phase> allPhase = phaseService.getAllPhases();
        ModelAndView mv = new ModelAndView("/tournament/read/list");
        mv.addObject("phases", allPhase);

        String removed = request.getParameter("removed");
        String updated = request.getParameter("updated");
        String created = request.getParameter("created");
        String unknown = request.getParameter("unknown");

        if (created != null) {
            mv.addObject("created", created);
        } else if (updated != null) {
            mv.addObject("updated", updated);
        } else if (removed != null) {
            mv.addObject("removed", removed);
        } else if (unknown != null) {
            mv.addObject("unknown", unknown);
        }

        return mv;
    }

    /**
     * Show the creation form
     *
     * @return The view name
     */
    @RequestMapping(value = "/form/{tournament}/", method = RequestMethod.GET)
    public ModelAndView showForm(@PathVariable("tournament") long tournament) throws ServletRequestBindingException {
        Phase phase;
        Tournament t = tournamentService.getTournamentById(tournament);
        phase = new Phase(t);
        return new ModelAndView("/phase/create/form", ADD_PHASE_FORM_BEAN_MODEL_ATTRIBUTE, phase);
    }

    /**
     * Show the update form
     *
     * @param id The phase id
     * @return The view name
     * @throws org.springframework.web.bind.ServletRequestBindingException
     */
    @RequestMapping(value = "/form/{tournament}/{id}", method = RequestMethod.GET)
    public ModelAndView setupForm(@PathVariable("tournament") long tournament, @PathVariable("id") long id) throws ServletRequestBindingException {
        Phase phase = phaseService.getPhaseById(id);
        Tournament t = tournamentService.getTournamentById(tournament);
        if (phase == null)
            phase = new Phase(t);
        return new ModelAndView("/phase/create/form", ADD_PHASE_FORM_BEAN_MODEL_ATTRIBUTE, phase);
    }

    /**
     * Show the view of a phase
     *
     * @return The view name
     */
    @RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
    public ModelAndView setupView(@PathVariable("id") long id) throws ServletRequestBindingException {
        Phase phase = phaseService.getPhaseById(id);
        if (phase == null)
            return new ModelAndView("redirect:/?unknownPhase=" + id);
        return new ModelAndView("/phase/read/view", VIEW_PHASE_FORM_BEAN_MODEL_ATTRIBUTE, phase);
    }

    /**
     * Process the creation or update form
     *
     * @param request  The request
     * @param modelMap The variables used in the view
     * @param newPhase The phase made of the sent form
     * @param result   The binding result
     * @return The view name
     */
    @RequestMapping(value = {"/create"}, method = {RequestMethod.POST})
    public String processForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid Phase newPhase, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/phase/create/form";
        }
        if (newPhase.getId() == null) {
            this.phaseService.createPhase(newPhase);
            return "redirect:/?createdPhase=" + newPhase.getName();
        } else {
            this.phaseService.updatePhase(newPhase);
            return "redirect:/?updatedPhase=" + newPhase.getName();
        }
    }


    /**
     * Remove a phase action.
     * It used a form to prevent double removing when user hit refresh on its web browser
     *
     * @param request             The request
     * @param modelMap            The variables used in the view
     * @param removePhaseFormBean The form binding object
     * @param result              The binding result
     * @return viewName
     */
    @RequestMapping(value = {"/remove"}, method = {RequestMethod.POST})
    public String removePhase(HttpServletRequest request, ModelMap modelMap,
                              @Valid RemovePhaseFormBean removePhaseFormBean, BindingResult result) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/tournament/read/list";
        }
        Phase removedPhase = this.phaseService.getPhaseById(removePhaseFormBean.getId());

        //if (removedPhase.getPools().size() == 0 && removedPhase.getTeams().size() == 0) {
        this.phaseService.deletePhase(removedPhase);
        modelMap.addAttribute("phase", removedPhase);
        return "redirect:/?removedPhase=" + removedPhase.getName();
        //}
        //return "/tournament/read/list";
    }

    /**
     * Initialize "PhaseView" model attribute
     *
     * @return a new PhaseView.
     */
    @ModelAttribute(VIEW_PHASE_FORM_BEAN_MODEL_ATTRIBUTE)
    public Phase initPhaseView() {
        return new Phase(null);
    }

    /**
     * /**
     * Initialize "phases" model attribute
     *
     * @return an empty List of Phases.
     */
    @ModelAttribute(PHASE_MODEL_ATTRIBUTE)
    public List<Phase> initPhases() {
        return new ArrayList<Phase>();
    }

    /**
     * Initialize "RemovePhaseFormBean" model attribute
     *
     * @return a new RemovePhaseFormBean.
     */
    @ModelAttribute(REMOVE_PHASE_FORM_BEAN_MODEL_ATTRIBUTE)
    public RemovePhaseFormBean initRemovePhaseFormBean() {
        return new RemovePhaseFormBean();
    }
}