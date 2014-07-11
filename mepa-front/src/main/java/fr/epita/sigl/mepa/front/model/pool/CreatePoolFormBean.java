package fr.epita.sigl.mepa.front.model.pool;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by maite on 10/07/14.
 */
public class CreatePoolFormBean {
   // @NotBlank
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
