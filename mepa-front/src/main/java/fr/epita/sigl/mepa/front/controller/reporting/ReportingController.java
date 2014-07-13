package fr.epita.sigl.mepa.front.controller.reporting;


import fr.epita.sigl.mepa.core.domain.Team;

import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.*;

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
        System.out.print(teamList.size());
    	mv.addObject("tournament",t);
        mv.addObject("listOrderTeam", teamList);
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
  
}
