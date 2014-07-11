package fr.epita.sigl.mepa.front.controller.pool;

import fr.epita.sigl.mepa.core.domain.Pool;
import fr.epita.sigl.mepa.core.domain.Team;
import fr.epita.sigl.mepa.core.domain.Tournament;
import fr.epita.sigl.mepa.core.service.PoolService;
import fr.epita.sigl.mepa.front.model.pool.CreatePoolFormBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by maite on 10/07/14.
 */
@Controller
public class PoolController {

    private static final Logger LOG = LoggerFactory.getLogger(PoolController.class);

     @Autowired
    private PoolService s;

    private static final String CREATE_POOL_FORM_BEAN_MODEL_ATTRIBUTE = "createPoolFormBean";
    protected static final String POOL_MODEL_ATTRIBUTE = "pools";


    @ModelAttribute(CREATE_POOL_FORM_BEAN_MODEL_ATTRIBUTE)
    public CreatePoolFormBean initAddPoolFormBean() {
        return new CreatePoolFormBean();
    }

    @ModelAttribute(POOL_MODEL_ATTRIBUTE)
    public List<Pool> initPools() {
        return new ArrayList<Pool>();
    }


    @RequestMapping(value = {"/creerPoule"}, method = RequestMethod.GET)
    public String afficher(HttpServletRequest request, ModelMap pModel) {
        List<Pool> l = this.s.getAllPools();
        pModel.addAttribute("pools", l);
        return "/creerPoule";
    }

    @RequestMapping(value="/creerPoule", method = RequestMethod.POST)
    public String creer(HttpServletRequest request, ModelMap modelMap,
                        @Valid CreatePoolFormBean createPoolFormBean, BindingResult result) {



        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/creerPoule";
        }
      List e = createPoolFormBean.getTeams();
      System.out.println(e.size());
      Set<Team> teams = new HashSet<Team>();
      for(int i = 0; i < e.size(); ++i) {
            Team c = new Team(e.get(i).toString());
            teams.add(c);
        }

        System.out.println(teams.toString());
        Pool newPool = new Pool();
        newPool.setName(createPoolFormBean.getName());
        //newPool.setTeams(teams);
        this.s.createPool(newPool);
        modelMap.addAttribute("pool", newPool);

        List<Pool> l = this.s.getAllPools();
        modelMap.addAttribute("pools", l);
        return "/creerPoule";
    }
}