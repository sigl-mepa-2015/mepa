package fr.epita.sigl.mepa.front.model.tournament;

import org.hibernate.validator.constraints.NotBlank;

public class AddTournamentFormBean {

    @NotBlank
    private String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
