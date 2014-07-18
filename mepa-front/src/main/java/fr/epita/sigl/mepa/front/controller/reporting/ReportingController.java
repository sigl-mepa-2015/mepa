package fr.epita.sigl.mepa.front.controller.reporting;


import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.Phase;
import fr.epita.sigl.mepa.core.domain.Player;
import fr.epita.sigl.mepa.core.domain.Team;
import fr.epita.sigl.mepa.core.service.*;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/reporting")
public class ReportingController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingController.class);
    @Autowired
    private PhaseService phaseService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private GameService gameService;
    @Autowired
    private PoolService poolService;
    @Autowired
    private PlayerService playerService;

    /**
     * @return ModelAndView
     */
    @RequestMapping(value = "/tournament", method = RequestMethod.GET)
    public ModelAndView globalReporting(@RequestParam("phaseID") Long phaseID) {
        ModelAndView mv = new ModelAndView("/reporting/tournamentReporting");

        Phase t = phaseService.getPhaseById(phaseID);
        String[] timeData = phaseService.getPhaseEndDate(t);
        List<Team> teamList = teamService.getAllOrderTeamsByPhase(phaseID);
        List<Player> playerList = playerService.getAllOrderPlayerByPhase(phaseID);
        mv.addObject("tournament", t);
        mv.addObject("listOrderTeam", teamList);
        mv.addObject("listOrderPlayer", playerList);
        mv.addObject("comingGame", gameService.getComingGameByPhaseId(phaseID));
        mv.addObject("progressGame", gameService.getProgressGameByPhaseId(phaseID));
        mv.addObject("endedGame", gameService.getEndedGameByPhaseId(phaseID));
        mv.addObject("timeMoy", timeData[1]);
        mv.addObject("endedDate", timeData[0]);

        try {
            mv.addObject("mapPools", poolService.aggregatePoolGameByPhase(phaseID));
        } catch (JSONException e) {
            LOG.error(e.toString());
        }

        return mv;
    }

    @RequestMapping(value = "/team", method = RequestMethod.GET)
    public ModelAndView teamReporting(@RequestParam("teamID") Long teamID) {
        ModelAndView mv = new ModelAndView("/reporting/teamReporting");

        Team t = teamService.getTeamById(teamID);
        mv.addObject("team", t);
        HashMap<String, Long> rangeLongMap = teamService.getRangeByPhaseId(t.getPhase().getId(), t);
        mv.addObject("rangeGlobal", rangeLongMap.get("global"));
        mv.addObject("rangePool", rangeLongMap.get("pool"));
        if (rangeLongMap.get("globalPrev") != null) {
            mv.addObject("globalPrev", teamService.getTeamById(rangeLongMap.get("globalPrev")));
            mv.addObject("globalPrevRange", rangeLongMap.get("globalPrevRange"));
        }
        if (rangeLongMap.get("globalNext") != null) {
            mv.addObject("globalNext", teamService.getTeamById(rangeLongMap.get("globalNext")));
            mv.addObject("globalNextRange", rangeLongMap.get("globalNextRange"));
        }
        if (rangeLongMap.get("poolPrev") != null) {
            mv.addObject("poolPrev", teamService.getTeamById(rangeLongMap.get("poolPrev")));
            mv.addObject("poolPrevRange", rangeLongMap.get("poolPrevRange"));
        }
        if (rangeLongMap.get("poolNext") != null) {
            mv.addObject("poolNext", teamService.getTeamById(rangeLongMap.get("poolNext")));
            mv.addObject("poolNextRange", rangeLongMap.get("poolNextRange"));
        }

        List<Game> listGame = gameService.getGameByTeam(teamID);

        for (Game g : listGame)
            System.out.println(g.getStatus());

        mv.addObject("teamGame", listGame);
        mv.addObject("players", t.getPlayers());
        mv.addObject("todoGame", gameService.countComingGameByTeamId(teamID));
        mv.addObject("endedGame", gameService.countEndedGameByTeamId(teamID));
        mv.addObject("averrageTime", gameService.getAverragePlayingTimeByTeam(teamID));
        try {
            mv.addObject("jsonResult", teamService.constructJSONforResultChart(t));
            mv.addObject("jsonResultScore", teamService.constructJSONForScoreChart(teamID));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return mv;
    }

}
