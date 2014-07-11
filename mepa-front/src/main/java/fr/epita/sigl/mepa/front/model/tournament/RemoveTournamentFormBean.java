package fr.epita.sigl.mepa.front.model.tournament;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class RemoveTournamentFormBean {

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
