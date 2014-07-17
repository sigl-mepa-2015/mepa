package fr.epita.sigl.mepa.front.controller.player;

import fr.epita.sigl.mepa.core.domain.MepaUser;
import fr.epita.sigl.mepa.core.domain.Team;
import fr.epita.sigl.mepa.core.domain.Player;
import fr.epita.sigl.mepa.core.service.MepaUserService;
import fr.epita.sigl.mepa.core.service.TeamService;
import fr.epita.sigl.mepa.core.service.PlayerService;
import fr.epita.sigl.mepa.front.model.player.PlayerFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by david on 10/07/14.
 */
@Controller
@RequestMapping("/player")
@SessionAttributes({ PlayerController.PLAYER_MODEL_ATTRIBUTE})
public class PlayerController {

    private static final Logger LOG = LoggerFactory.getLogger(PlayerController.class);

    protected static final String PLAYER_MODEL_ATTRIBUTE = "team";
    private static final String ADD_PLAYER_FORM_BEAN_MODEL_ATTRIBUTE = "PlayerFormBean";
    private static final String EDIT_PLAYER_FORM_BEAN_MODEL_ATTRIBUTE = "PlayerFormBean";


    @Autowired
    private TeamService teamService;
    @Autowired
    private PlayerService playerService;
    @Autowired
    private MepaUserService userService;

    @RequestMapping(value = {"/form" })
    public ModelAndView showCreationForm(@RequestParam("teamID") Long teamID){
        Team team = teamService.getTeamById(teamID);
        ModelAndView mv = new ModelAndView("/player/create/form");
        mv.addObject("team", team);
        return mv;
    }

    @RequestMapping(value="/edit", method=RequestMethod.GET)
    public ModelAndView editPlayer(@RequestParam("playerID") Long playerID){

        Player player = playerService.getPlayerById(playerID);
        ModelAndView mv = new ModelAndView("player/edit/form");
        mv.addObject("player", player);
        return mv;

    }


    @RequestMapping(value = { "/create" }, method = { RequestMethod.POST })
    public String processForm(HttpServletRequest request, ModelMap modelMap,
                              @Valid PlayerFormBean playerFormBean, BindingResult result,
                              @RequestParam("teamID") Long teamID) {
        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/team/read/list";
        }
        
        Team team = teamService.getTeamById(teamID);
        MepaUser user = new MepaUser();
        user.setName(playerFormBean.getName());
        user.setLogin(playerFormBean.getName());
        user.setPwd("pwd");
        userService.createMepaUser(user);
        Player newPlayer = new Player(playerFormBean.getName(), playerFormBean.getFirstname(), team);
        newPlayer.setMepaUser(user);
        newPlayer.setTeam(team);
        playerService.createPlayer(newPlayer);
        modelMap.addAttribute("player", newPlayer);

        List<Player> allPlayer = playerService.getAllPlayers();
        modelMap.addAttribute("players", allPlayer);

        return "redirect:/team/detail/"+team.getId();
    }

    @ModelAttribute(PLAYER_MODEL_ATTRIBUTE)
    public List<Player> initPlayers() {
        return new ArrayList<Player>();
    }

    @ModelAttribute(ADD_PLAYER_FORM_BEAN_MODEL_ATTRIBUTE)
    public PlayerFormBean initPlayerFormBean() {
        return new PlayerFormBean();
    }

}
