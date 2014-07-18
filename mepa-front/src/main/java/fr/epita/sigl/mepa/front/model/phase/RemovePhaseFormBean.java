package fr.epita.sigl.mepa.front.model.phase;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RemovePhaseFormBean {

    @NotNull
    @Min(1)
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
