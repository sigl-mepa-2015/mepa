package fr.epita.sigl.mepa.front.controller.reporting;




import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.epita.sigl.mepa.core.domain.Match;
import fr.epita.sigl.mepa.core.domain.Pool;
import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.TournamentService;
import fr.epita.sigl.mepa.core.service.impl.TournamentServiceImpl;

@Controller
@RequestMapping("/reporting")
public class ReportingController {

	@Autowired
    private TournamentService tournamentService;
	
    private static final Logger LOG = LoggerFactory.getLogger(ReportingController.class);
    
    /**
     * 
     * @return ModelAndView
     */
    @RequestMapping(value="/endedGame", method=RequestMethod.GET)
    public ModelAndView showEndedGame(@RequestParam("tournamentID") Long tournamentID)
    {
    	Tournament t = null;
    	System.out.println(tournamentID);    	
    	t = tournamentService.getTournamentById(tournamentID);
    	
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
    public ModelAndView showComingGame(@RequestParam("tournamentID") Long tournamentID)
    {
    	
    	Tournament t = null;
    	System.out.println(tournamentID);    	
    	t = tournamentService.getTournamentById(tournamentID);
    	
    	LOG.info("inside showComingGame fonction");
    	ModelAndView mv = new ModelAndView("/reporting/showComingGame");
    	mv.addObject("t",t);

    	return mv;
    }
    
    /**
     * 
     * @return ModelAndView
     */
    @RequestMapping(value="/showRange", method=RequestMethod.GET)
    public ModelAndView showRange(@RequestParam("tournamentID") Long tournamentID)
    {
    	Tournament t = null;
    	System.out.println(tournamentID);    	
    	t = tournamentService.getTournamentById(tournamentID);
    	
    	LOG.info("inside showRange fonction");
    	ModelAndView mv = new ModelAndView("/reporting/showRange");
    	mv.addObject("t",t);

    	return mv;
    }

}
