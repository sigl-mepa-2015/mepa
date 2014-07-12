package fr.epita.sigl.mepa.front.model.tournament;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by maite on 12/07/14.
 */
public class ViewTournamentFormBean {
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
