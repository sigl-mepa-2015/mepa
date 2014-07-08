package fr.epita.sigl.mepa.front.controller.reporting;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView showEndedGame()
    {
    	LOG.info("inside showEndedGame fonction");
    	ModelAndView mv = new ModelAndView("/reporting/showEndedGame");
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
    	ModelAndView mv = new ModelAndView("/reporting/showComingGale");
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
    	return mv;
    }

}
