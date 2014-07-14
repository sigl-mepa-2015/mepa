package fr.epita.sigl.mepa.front.controller.pool;

import fr.epita.sigl.mepa.core.domain.Pool;
import fr.epita.sigl.mepa.core.domain.Team;
import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.PoolService;
import fr.epita.sigl.mepa.core.service.TeamService;
import fr.epita.sigl.mepa.core.service.TournamentService;
import fr.epita.sigl.mepa.front.model.pool.CreatePoolFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by maite on 10/07/14.
 */
@Controller
public class PoolController {

    private static final Logger LOG = LoggerFactory.getLogger(PoolController.class);

    @Autowired
    private PoolService s;

    @Autowired
    private TournamentService t;

    @Autowired
    private TeamService ts;

    private static final String CREATE_POOL_FORM_BEAN_MODEL_ATTRIBUTE = "createPoolFormBean";
    protected static final String POOL_MODEL_ATTRIBUTE = "pools";
    protected static final String TEAM_MODEL_ATTRIBUTE = "teams";


    @ModelAttribute(CREATE_POOL_FORM_BEAN_MODEL_ATTRIBUTE)
    public CreatePoolFormBean initAddPoolFormBean() {

        return new CreatePoolFormBean();
    }

    @ModelAttribute(POOL_MODEL_ATTRIBUTE)
    public List<Pool> initPools() {
        return new ArrayList<Pool>();
    }

    @ModelAttribute(TEAM_MODEL_ATTRIBUTE)
    public List<Team> initTeams() {
        return new ArrayList<Team>();
    }

    @RequestMapping(value = {"/creerPoule"}, method = RequestMethod.GET)
    public String afficher(@RequestParam("tournamentID") Long tournamentID, ModelMap pModel) {
        List<Pool> l = this.s.getAllPools();
        pModel.addAttribute("pools", l);

        List<Team> teams = this.ts.getAllOrderTeamsByTournament(tournamentID);
        pModel.addAttribute("teams", teams);

        return "/creerPoule";
    }

    @RequestMapping(value="/creerPoule", method = RequestMethod.POST)
    public String creer(@RequestParam("tournamentID") Long tournamentID, ModelMap modelMap,
                         CreatePoolFormBean createPoolFormBean, BindingResult result) {

        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/creerPoule";
        }

        Pool newPool = new Pool();
        newPool.setName(createPoolFormBean.getName());

        newPool.setTournament(t.getTournamentById(tournamentID));

        this.s.createPool(newPool);
        modelMap.addAttribute("pool", newPool);


        Set<Team> teamsSet = new HashSet<Team>();
        for(int i = 0; i < createPoolFormBean.getTeams().size(); ++i){
            teamsSet.add(ts.getTeamById(Long.getLong(createPoolFormBean.getTeams().get(i))));
        }

        newPool.setTeams(teamsSet);

        List<Pool> l = this.s.getAllPools();
        modelMap.addAttribute("pools", l);

        modelMap.addAttribute("message", true);
        return "/creerPoule";
    }

}