package fr.epita.sigl.mepa.front.controller.reporting;


import fr.epita.sigl.mepa.core.domain.Team;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.social.FacebookAutoConfiguration;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.epita.sigl.mepa.core.domain.*;
import fr.epita.sigl.mepa.core.service.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/reporting")
public class ReportingController {

	@Autowired
    private TournamentService tournamentService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private GameService gameService;
	@Autowired
	private PoolService poolService;
    @Autowired
    private PlayerService playerService;
	    
    private static final Logger LOG = LoggerFactory.getLogger(ReportingController.class);
    
    
    /**
     * 
     * @return ModelAndView
     */
    @RequestMapping(value="/tournament", method=RequestMethod.GET)
    public ModelAndView globalReporting(@RequestParam("tournamentID") Long tournamentID)
    {    	
    	ModelAndView mv = new ModelAndView("/reporting/tournamentReporting");
    	
    	Tournament t = tournamentService.getTournamentById(tournamentID);
        String[] timeData = tournamentService.getTournamentEndDate(t);
        List<Team> teamList = teamService.getAllOrderTeamsByTournament(tournamentID);
        List<Player> playerList = playerService.getAllOrderPlayerByTournament(tournamentID);
    	mv.addObject("tournament",t);
        mv.addObject("listOrderTeam", teamList);
        mv.addObject("listOrderPlayer", playerList);
    	mv.addObject("comingGame", gameService.getComingGameByTournamentId(tournamentID));
    	mv.addObject("progressGame", gameService.getProgressGameByTournamentId(tournamentID));
    	mv.addObject("endedGame", gameService.getEndedGameByTournamentId(tournamentID));
    	mv.addObject("timeMoy", timeData[1]);
        mv.addObject("endedDate", timeData[0]);

    	try {
			mv.addObject("mapPools", poolService.aggregatePoolGameByTournament(tournamentID));
		} catch (JSONException e) {
			LOG.error(e.toString());
		}
    	
    	return mv;
    }
    
    @RequestMapping(value="/team", method=RequestMethod.GET)
    public ModelAndView teamReporting(@RequestParam("teamID") Long teamID)
    {
    	ModelAndView mv = new ModelAndView("/reporting/teamReporting");
    	
    	Team t = teamService.getTeamById(teamID);
    	mv.addObject("team", t);
    	HashMap<String, Long> rangeLongMap = teamService.getRangeByTounrnamentId(t.getTournament().getId(), t);
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
    	
    	for(Game g : listGame)
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
