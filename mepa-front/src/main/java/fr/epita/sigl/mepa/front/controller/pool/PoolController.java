package fr.epita.sigl.mepa.front.controller.pool;

import fr.epita.sigl.mepa.core.domain.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * Created by maite on 10/07/14.
 */
@Controller
public class PoolController {

    private static final Logger LOG = LoggerFactory.getLogger(PoolController.class);

    @RequestMapping(value = {"/creerPoule", "/creerPoule"})
    public String create() {
        return "/creerPoule";
    }
}
