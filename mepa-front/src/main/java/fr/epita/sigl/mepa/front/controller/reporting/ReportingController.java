package fr.epita.sigl.mepa.front.controller.reporting;


import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.TournamentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
    @RequestMapping(value="/tournament", method=RequestMethod.GET)
    public ModelAndView showEndedGame(@RequestParam("tournamentID") Long tournamentID)
    {
    	Tournament t = null;
//    	System.out.println(tournamentID);
    	t = tournamentService.getTournamentById(tournamentID);
    	
//    	LOG.info("inside showEndedGame fonction");
    	ModelAndView mv = new ModelAndView("/reporting/tournamentReporting");
    	mv.addObject("t",t);

        List<Tournament> li = tournamentService.getAllTournaments();
        mv.addObject("li", li);
    	return mv;
    }
  
}
