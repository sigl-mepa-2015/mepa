package fr.epita.sigl.mepa.core.domain;

import java.lang.Enum;
import java.lang.Float;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(name="Pool")
@NamedQueries({
        @NamedQuery(name = "Pool.findById", query = "FROM Pool o WHERE o.id=:id"),
        @NamedQuery(name = "Pool.findAll", query = "FROM Pool o") })
public class Pool {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable=false)
	private Long id;

    private String name;

    private Set<Team> teams;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the teams
     */
    public Set<Team> getTeams() {
        return teams;
    }

    /**
     * @param teams the teams to set
     */
    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

}