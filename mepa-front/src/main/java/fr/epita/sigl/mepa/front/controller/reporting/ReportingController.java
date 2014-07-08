package fr.epita.sigl.mepa.front.controller.reporting;


import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.epita.sigl.mepa.core.domain.Match;
import fr.epita.sigl.mepa.core.domain.Pool;
import fr.epita.sigl.mepa.core.domain.Team;
import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.TournamentService;
import fr.epita.sigl.mepa.core.service.impl.TournamentServiceImpl;
import fr.epita.sigl.mepa.front.controller.home.HomeController;

@Controller
@RequestMapping("/reporting")
public class ReportingController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
    
    /**
     * 
     * @return ModelAndView
     */
    @RequestMapping(value="/endedGame", method=RequestMethod.GET)
    public ModelAndView showEndedGame(@RequestParam("tournamentID") Long tournamentID)
    {
    	/*Tournament t;
    	System.out.println(tournamentID);
    	
    	TournamentService tournamentService = new TournamentServiceImpl();
    	
    	t = tournamentService.getTournamentById(tournamentID);*/
    	
    	Tournament t = new Tournament();
    	t.setName("monTournoi");
    	Pool p1 = new Pool();
    	Pool p2 = new Pool();
    	p1.setName("A");
    	p2.setName("B");
    	
    	Team t1 = new Team();
    	t1.setName("Team1");
    	Team t2 = new Team();
    	t2.setName("team2");
    	Team t3 = new Team();
    	t3.setName("team3");
    	Team t4 = new Team();
    	t4.setName("team4");
    	
    	Match m1 = new Match();
    	Match m2 = new Match();
    	
    	m1.setTeam1(t1);
    	m1.setTeam2(t2);
    	m1.setScoreTeamOne(2);
    	m1.setScoreTeamTwo(4);
    	m2.setTeam1(t3);
    	m2.setScoreTeamOne(5);
    	m2.setTeam2(t4);
    	m2.setScoreTeamTwo(7);
    	
    	Set<Match> sm = new HashSet<Match>();
    	sm.add(m1);
    	sm.add(m2);
    	
    	p1.setMatchs(sm);
    	p2.setMatchs(sm);
    	
    	Set<Pool> sp = new HashSet<Pool>();
    	sp.add(p1);
    	sp.add(p2);
    	
    	t.setPools(sp);
    	
    	LOG.info("inside showEndedGame fonction");
    	ModelAndView mv = new ModelAndView("/reporting/showEndedGame");
    	mv.addObject("t",t);

    	return mv;
    }
    
    /**
     * 
     * @return ModelAndView
     */
    @RequestMapping(value="/comingGame", method=RequestMethod.GET)
    public ModelAndView showComingGame()
    {
    	LOG.info("inside showComingGame fonction");
    	ModelAndView mv = new ModelAndView("/reporting/showComingGame");
    	mv.addObject("tournamentName","Tournoi n1");
    	return mv;
    }
    
    /**
     * 
     * @return ModelAndView
     */
    @RequestMapping(value="/showRange", method=RequestMethod.GET)
    public ModelAndView showRange()
    {
    	LOG.info("inside showRange fonction");
    	ModelAndView mv = new ModelAndView("/reporting/showRange");
    	mv.addObject("tournamentName","Tournoi n1");

    	return mv;
    }

}
