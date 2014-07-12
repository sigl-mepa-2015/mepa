package fr.epita.sigl.mepa.front.controller.reporting;


import fr.epita.sigl.mepa.core.domain.Team;
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
//    	System.out.println("pools :" + t.getPools().size());
//    	System.out.println("teamps : " + t.getTeams().size());

        List<Team> teamList = teamService.getAllOrderTeamsByTournament(tournamentID);
        System.out.print(teamList.size());
    	mv.addObject("tournament",t);
        mv.addObject("listOrderTeam", teamList);
    	mv.addObject("comingGame", gameService.getComingGameByTournamentId(tournamentID));
    	mv.addObject("endedGame", gameService.getEndedGameByTournamentId(tournamentID));
    	
    	return mv;
    }
  
}
