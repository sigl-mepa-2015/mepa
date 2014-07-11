package fr.epita.sigl.mepa.front.controller.reporting;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.epita.sigl.mepa.core.service.*;

@Controller
@RequestMapping("/reporting")
public class ReportingController {

	@Autowired
    private TournamentService tournamentService;
	@Autowired
	private TeamService teamService;
	@Autowired
	private GameService gameService;
	
    private static final Logger LOG = LoggerFactory.getLogger(ReportingController.class);
    
    /**
     * 
     * @return ModelAndView
     */
    @RequestMapping(value="/tournament", method=RequestMethod.GET)
    public ModelAndView globalReporting(@RequestParam("tournamentID") Long tournamentID)
    {    	
    	ModelAndView mv = new ModelAndView("/reporting/tournamentReporting");
    	
    	mv.addObject("tournament",tournamentService.getTournamentById(tournamentID));
        mv.addObject("listOrderTeam", teamService.getAllOrderTeamsByTournament(tournamentID));
    	mv.addObject("comingGame", gameService.getComingGameByTournamentId(tournamentID));
    	mv.addObject("endedGame", gameService.getEndedGameByTournamentId(tournamentID));
            
    	return mv;
    }
  
}
