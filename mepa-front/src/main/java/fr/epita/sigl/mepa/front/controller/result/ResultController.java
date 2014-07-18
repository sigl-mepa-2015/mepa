package fr.epita.sigl.mepa.front.controller.result;


import fr.epita.sigl.mepa.core.domain.*;
import fr.epita.sigl.mepa.core.service.*;

import java.util.Random;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;



/**
 * result controller
 * Created by Jason on 16/07/2014.
 */
@Controller
@RequestMapping("/result")

public class ResultController {
   
    @Autowired
    private GameService gs;
    
     @Autowired
    private PoolService s;
     
    @Autowired
    private JoinedGameTeamService jgs;

    @Autowired
    private TeamService ts;

    @Autowired
    private PlayerService ps;

    @Autowired
    private MepaUserService mus;

    @Autowired
    private PhaseService phs;

    /**
     * Default action : show all tournaments
     *
     * @param request The request
     * @return The View Name
     */
     @RequestMapping(value = {"/view"}, method = RequestMethod.GET)
    public ModelAndView ViewResult(HttpServletRequest request) {
   
        ModelAndView mv = new ModelAndView("/result/view");
        mv.addObject("poolID", "1");
        return mv;
    }
    /**
     * Default action : show a team
     */
    @RequestMapping(value = {"/teamScore"}, method = RequestMethod.GET)
    public String teamScore(@RequestParam("teamID") Long teamID, ModelMap pModel, HttpServletRequest request) {

        if (request.getParameter("valid") != null) {
            pModel.addAttribute("valid", request.getParameter("valid"));
        }
        Team team = this.ts.getTeamById(teamID);
        if (team != null) {
            Set<Player> playerList = team.getPlayers();

            pModel.addAttribute("playerList", playerList);
        }

        pModel.addAttribute("team", team);
        return "/result/teamScore";
    }

    @RequestMapping(value = {"/modifyPlayerScore"}, method = RequestMethod.POST)
    public String modifyPlayerScore(HttpServletRequest request, ModelMap pModel) {

        Long gameID = Long.parseLong(request.getParameter("playerID"));
        Player p = this.ps.getPlayerById(gameID);
        try {
            p.setNbPoint(Integer.parseInt(request.getParameter("playerScore")));
            ps.updatePlayer(p);
            pModel.addAttribute("valid", 1);
            return "redirect:/result/teamScore?teamID="+p.getTeam().getId();
        } catch (Exception e) {
            pModel.addAttribute("valid", 0);
            return "redirect:/result/teamScore?teamID="+p.getTeam().getId();
        }

    }

    @RequestMapping(value = {"/afficherGame"}, method = RequestMethod.GET)
    public String afficherGame(@RequestParam("poolID") Long poolID, ModelMap pModel, HttpServletRequest request) {
        Pool pool = this.s.getPoolById(poolID);
        String message = request.getParameter("message");
        pModel.addAttribute("message", message);
        
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
        
        String message = null;

        if ((request.getParameter("duration").compareTo("") != 0) || (request.getParameter("duration").compareTo("0") != 0))
           g.setDuration(Integer.parseInt(request.getParameter("duration")));
        else
        {
            message = "durée";
            modelMap.addAttribute("message", message);
            return "redirect:afficherGame?poolID="+g.getPool().getId();
        }
        
         if (request.getParameter("Status").compareTo("En cours") == 0)
            g.setStatus(Game.GameStatus.PROGRESS);
        else
            g.setStatus(Game.GameStatus.DONE);
         
        this.gs.updateGame(g);
        
        if (request.getParameter("resultEquipe1").compareTo("") != 0) {
            Long joinedGameTeam1 = Long.parseLong(request.getParameter("joinedID1"));
            JoinedGameTeam j1 = this.jgs.getJoinedGameById(joinedGameTeam1);
            j1.setScore(Integer.parseInt(request.getParameter("resultEquipe1")));
            this.jgs.updateJoinedGameTeam(j1);
        }
         else
        {
            message = "score";
            modelMap.addAttribute("message", message);
            return "redirect:afficherGame?poolID="+g.getPool().getId();
        }
      
        if (request.getParameter("resultEquipe2").compareTo("") != 0) {
            Long joinedGameTeam2 = Long.parseLong(request.getParameter("joinedID2"));
            JoinedGameTeam j2 = this.jgs.getJoinedGameById(joinedGameTeam2);
            j2.setScore(Integer.parseInt(request.getParameter("resultEquipe2")));
            this.jgs.updateJoinedGameTeam(j2);
        }
         else
        {
            message = "score";
            modelMap.addAttribute("message", message);
            return "redirect:afficherGame?poolID="+g.getPool().getId();
        }
        
       
        
         if (request.getParameter("Status").compareTo("En cours") == 0)
        message = "validerLive";
         else
             message = "ValiderFin";
         modelMap.addAttribute("message", message);
        return "redirect:afficherGame?poolID="+g.getPool().getId();
        
    }

    /**
     * Default action : show all tournaments
     */
    @RequestMapping(value = {"/addRandomTeam"}, method = RequestMethod.GET)
    public String addRandomTeam(HttpServletRequest request) {

        Phase ph = phs.getPhaseById((long) 1);
        Team t = new Team();
        t.setName(new Random().nextInt() + "");
        t.setPhase(ph);
        ts.createTeam(t);


        MepaUser user = new MepaUser();
        user.setName(new Random().nextInt() + "");
        user.setLogin(new Random().nextInt() + "");
        user.setPwd(new Random().nextInt() + "");
        mus.createMepaUser(user);

        Player p = new Player();
        p.setTeam(t);
        p.setMepaUser(user);
        p.setName(new Random().nextInt() + "");
        ps.createPlayer(p);


        return "redirect:/tournament";
    }
}