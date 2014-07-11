/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.epita.sigl.mepa.front.model.team;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author david
 */
public class AddTeamFormBean {
    @NotBlank
    
    private String name;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
