package fr.epita.sigl.mepa.front.model.team;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RemoveTeamFormBean {

    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
