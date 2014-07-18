package fr.epita.sigl.mepa.front.controller.pool;

import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.JoinedGameTeam;
import fr.epita.sigl.mepa.core.domain.Pool;
import fr.epita.sigl.mepa.core.domain.Team;
import fr.epita.sigl.mepa.core.service.*;
import fr.epita.sigl.mepa.front.model.pool.CreatePoolFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by maite on 10/07/14.
 */
@Controller
public class PoolController {

    protected static final String POOL_MODEL_ATTRIBUTE = "pools";
    protected static final String TEAM_MODEL_ATTRIBUTE = "teams";
    private static final Logger LOG = LoggerFactory.getLogger(PoolController.class);
    private static final String CREATE_POOL_FORM_BEAN_MODEL_ATTRIBUTE = "createPoolFormBean";
    @Autowired
    private PoolService s;
    @Autowired
    private PhaseService t;
    @Autowired
    private TeamService ts;
    @Autowired

    private GameService gs;
    @Autowired
    private JoinedGameTeamService jgs;

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
    public String afficher(@RequestParam("phaseID") Long phaseID, ModelMap pModel) {
        List<Pool> l = this.s.getAllPools();
        pModel.addAttribute("pools", l);
        pModel.addAttribute("phaseID", phaseID);
        List<Team> teams = this.ts.getAllOrderTeamsByPhase(phaseID);
        pModel.addAttribute("teams", teams);
        return "/creerPoule";
    }

    @RequestMapping(value="/poolManager", method = RequestMethod.POST)
    public String creer2(@RequestParam("phaseID") Long phaseID,
                         @RequestParam(value = "created", required = false) Long poolID,
                         ModelMap modelMap,
                         CreatePoolFormBean createPoolFormBean, BindingResult result) {

        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/poolManager";
        }
        // My code
        if (poolID != null){
            modelMap.addAttribute("created", 1);//this.s.getPoolById(poolID));
        }
        // End MyCode

        List<Pool> l = this.s.getAllPools();
        modelMap.addAttribute("pools", l);
        modelMap.addAttribute("phaseID", phaseID);

        Pool newPool = new Pool();
        newPool.setName(createPoolFormBean.getName());
        newPool.setPhase(t.getPhaseById(phaseID));

        Set<Team> listteams = new HashSet<Team>();

        this.s.createPool(newPool);

        Team newTeam = new Team();
        for (String id_teams: createPoolFormBean.getTeams()) {
            listteams.add(ts.getTeamById(Long.parseLong(id_teams)));
            newTeam = this.ts.getTeamById(Long.parseLong(id_teams));
            newTeam.setPool(newPool);
            this.ts.updateTeam(newTeam);
        }

        newPool.setTeams(listteams);
        

        modelMap.addAttribute("pool", newPool);

        modelMap.addAttribute("message", true);
        return "/poolManager";
    }


    @RequestMapping(value="/poolManager", method = RequestMethod.GET)
    public String creer(@RequestParam("poolID") Long poolID, ModelMap modelMap,
                         CreatePoolFormBean createPoolFormBean, BindingResult result) {

        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/poolManager";
        }

        Pool p = new Pool();
        p=this.s.getPoolById(poolID);


        modelMap.addAttribute("pool", p);

        return "/poolManager";
    }

/*
    @RequestMapping(value="/matchGame", method = RequestMethod.GET)
    public String creerMatch(@RequestParam("poolID") Long poolID,
                             ModelMap modelMap,CreatePoolFormBean createPoolFormBean, BindingResult result) {

        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/poolManager";
        }

        Pool p = new Pool();
        p=this.s.getPoolById(poolID);
        modelMap.addAttribute("pool", p);

        if (match == true) {
            generateGames(p.getTournament().getId(), p.getId());
            this.match = false;
        }
        return "/matchGame";
    }

*/


    public void generateGames(Long id_team, Long id_pool) {
        for(int i = 0; i < this.ts.getAllOrderTeamsByPhase(id_team).size(); ++i) {
            for(int j = i + 1; j < this.ts.getAllOrderTeamsByPhase(id_team).size(); ++j) {
                Game g = new Game();
                JoinedGameTeam jg1 = new JoinedGameTeam();
                jg1.setTeam(this.ts.getAllOrderTeamsByPhase(id_team).get(i));
                JoinedGameTeam jg2 = new JoinedGameTeam();
                jg2.setTeam(this.ts.getAllOrderTeamsByPhase(id_team).get(j));

                jg1.setGame(g);
                jg2.setGame(g);

                Set<JoinedGameTeam> st = new HashSet<JoinedGameTeam>();
                st.add(jg1);
                st.add(jg2);
                g.setJoinedGameTeams(st);
                g.setPool(this.s.getPoolById(id_pool));
                g.setStatus(Game.GameStatus.TODO);

                this.gs.createGame(g);
                this.jgs.createJoinedGameTeam(jg1);
                this.jgs.createJoinedGameTeam(jg2);
            }
        }
    }

    @RequestMapping(value = {"/afficherGame"}, method = RequestMethod.GET)
    public String afficherGame(@RequestParam("poolID") Long poolID, ModelMap pModel) {
        Pool pool = this.s.getPoolById(poolID);
        if (pool != null) {
            if(pool.getGames().isEmpty())
            {
                generateGames(pool.getPhase().getId(), pool.getId());
                pool=this.s.getPoolById(poolID);
            }
            Set<Game> gameList = pool.getGames();
            pModel.addAttribute("gameList", gameList);
        }

        pModel.addAttribute("pools", poolID);

        return "/afficherGame";
    }




    @RequestMapping(value = {"/updateGame"}, method = RequestMethod.POST)
    public String updateGame(HttpServletRequest request, ModelMap modelMap) {

        Long gameID = Long.parseLong(request.getParameter("gameID"));
        Game g = this.gs.getGameById(gameID);
        g.setDuration(Integer.parseInt(request.getParameter("duration")));
        this.gs.updateGame(g);

        if (request.getParameter("resultEquipe1").compareTo("") != 0) {
            Long joinedGameTeam1 = Long.parseLong(request.getParameter("joinedID1"));
            JoinedGameTeam j1 = this.jgs.getJoinedGameById(joinedGameTeam1);
            j1.setScore(Integer.parseInt(request.getParameter("resultEquipe1")));
            this.jgs.updateJoinedGameTeam(j1);
        }
        if (request.getParameter("resultEquipe2").compareTo("") != 0) {
            Long joinedGameTeam2 = Long.parseLong(request.getParameter("joinedID2"));
            JoinedGameTeam j2 = this.jgs.getJoinedGameById(joinedGameTeam2);
            j2.setScore(Integer.parseInt(request.getParameter("resultEquipe2")));
            this.jgs.updateJoinedGameTeam(j2);
        }

        return "redirect:afficherGame?poolID="+g.getPool().getId();
    }
}