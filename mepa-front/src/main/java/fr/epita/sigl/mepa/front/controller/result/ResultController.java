package fr.epita.sigl.mepa.front.controller.result;


import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.GameService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    private GameService gameservice;

    /**
     * Default action : show all tournaments
     *
     * @param request The request
     * @return The View Name
     */
     @RequestMapping(value = {"/viewResult"}, method = RequestMethod.GET)
    public ModelAndView ViewResult(HttpServletRequest request) {
   
        return new ModelAndView("result/view");
    }
    
}