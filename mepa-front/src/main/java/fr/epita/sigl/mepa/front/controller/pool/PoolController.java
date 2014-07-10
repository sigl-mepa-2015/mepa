package fr.epita.sigl.mepa.front.controller.pool;

import fr.epita.sigl.mepa.core.domain.Pool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by maite on 10/07/14.
 */
public class PoolController {

    private static final Logger LOG = LoggerFactory.getLogger(PoolController.class);

    @RequestMapping(value = {"/", "/pool"})
    public String create() {
        return "/home/create";
    }
}
