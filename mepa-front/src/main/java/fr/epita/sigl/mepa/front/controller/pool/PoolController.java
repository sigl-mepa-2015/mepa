package fr.epita.sigl.mepa.front.controller.pool;

import fr.epita.sigl.mepa.core.domain.*;
import fr.epita.sigl.mepa.core.service.*;
import fr.epita.sigl.mepa.front.model.pool.CreatePoolFormBean;
import org.apache.commons.lang3.tuple.Pair;
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
import java.util.*;

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

    @Autowired

    private GameService gs;

    @Autowired
    private JoinedGameTeamService jgs;


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
        pModel.addAttribute("tournamentID", tournamentID);
        List<Team> teams = this.ts.getAllOrderTeamsByTournament(tournamentID);
        pModel.addAttribute("teams", teams);
        return "/creerPoule";
    }

    @RequestMapping(value="/poolManager", method = RequestMethod.POST)
    public String creer2(@RequestParam("tournamentID") Long tournamentID, ModelMap modelMap,
                         CreatePoolFormBean createPoolFormBean, BindingResult result) {

        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/poolManager";
        }

        List<Pool> l = this.s.getAllPools();
        modelMap.addAttribute("pools", l);
        modelMap.addAttribute("tournamentID", tournamentID);

        Pool newPool = new Pool();
        newPool.setName(createPoolFormBean.getName());
        newPool.setTournament(t.getTournamentById(tournamentID));

        Set<Team> listteams = new HashSet<Team>();

        this.s.createPool(newPool);

        Team newTeam = new Team();
        for (String id_teams: createPoolFormBean.getTeams()) {
            listteams.add(ts.getTeamById(Long.parseLong(id_teams)));
            newTeam = this.ts.getTeamById(Long.parseLong(id_teams));
            System.out.println("Team = " + newTeam.getId());
            newTeam.setPool(newPool);
            this.ts.updateTeam(newTeam);
        }   

        newPool.setTeams(listteams);
        newPool.setGames(generateGames(newPool.getTournament().getId(), newPool.getId()));


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


    public Set<Game> generateGames(Long id_team, Long id_pool) {
        List<Game> listGames = new ArrayList<Game>();
        for(int i = 0; i < this.ts.getAllOrderTeamsByTournament(id_team).size(); ++i) {
            for(int j = i + 1; j < this.ts.getAllOrderTeamsByTournament(id_team).size(); ++j) {
                Game g = new Game();
                JoinedGameTeam jg1 = new JoinedGameTeam();
                jg1.setTeam(this.ts.getAllOrderTeamsByTournament(id_team).get(i));
                JoinedGameTeam jg2 = new JoinedGameTeam();
                jg2.setTeam(this.ts.getAllOrderTeamsByTournament(id_team).get(j));

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
        listGames = this.gs.getAllGames();


        return new HashSet<Game>(listGames);

    }

    @RequestMapping(value = {"/afficherGame"}, method = RequestMethod.GET)
    public String afficherGame(@RequestParam("poolID") Long poolID, ModelMap pModel) {
        Pool pool = this.s.getPoolById(poolID);
        if (pool != null) {
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