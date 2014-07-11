package fr.epita.sigl.mepa.front.model.pool;

import fr.epita.sigl.mepa.core.domain.Team;
import org.hibernate.validator.constraints.NotBlank;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<String> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<String> teams) {
        this.teams = teams;
    }

    private ArrayList<String> teams;

}
