package fr.epita.sigl.mepa.front.controller.pool;

import fr.epita.sigl.mepa.core.domain.Pool;
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
import java.util.ArrayList;
import java.util.List;

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
        System.out.println("YYYYYYYYYYYYYY");
        return "/creerPoule";
    }

    @RequestMapping(value="/creerPoule", method = RequestMethod.POST)
    public String creer(HttpServletRequest request, ModelMap modelMap,
                         CreatePoolFormBean createPoolFormBean, BindingResult result) {


        if (result.hasErrors()) {
            // Error(s) in form bean validation
            return "/creerPoule";
        }

        System.out.println("Resultat ********** " + createPoolFormBean.getName());
        Pool newPool = new Pool(createPoolFormBean.getName(), null);

        this.s.createPool(newPool);
        System.out.println(s.getAllPools().get(0).getName());
        modelMap.addAttribute("pool", newPool);
        List<Pool> l = this.s.getAllPools();
        modelMap.addAttribute("pools", l);
        modelMap.addAttribute("message", true);
        return "/creerPoule";
    }

}