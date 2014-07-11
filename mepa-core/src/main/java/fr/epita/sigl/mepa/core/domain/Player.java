package fr.epita.sigl.mepa.core.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import fr.epita.sigl.mepa.core.domain.Team;
import javax.persistence.*;

/**
 * Created by SushiBalboha on 12/07/2014.
 */
@Entity
@Table(name = "Player")
@NamedQueries({
        @NamedQuery(name = "Player.findById", query = "FROM Player o WHERE o.id=:id"),
        @NamedQuery(name = "Player.findAll", query = "FROM Player o"),
        @NamedQuery(name = "Player.findByIdTeam", query = "From Player o WHERE o.idTeam=:idTeam")})
public class Player implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "team", unique = true, nullable = false)
    private Team team;

    @Column(name="teamId", nullable = true)
    private Long idTeam;

    public Player() {

    }

    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
        this.idTeam = team.getId();
    }

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
     * @return the team
     */
    public Team getTeam() {
        return team;
    }

    /**
     * @param team the team to set
     */
    public void setTeam(Team team) {
        this.team = team;
        this.setTeamId(team.getId());
    }

    /**
     * @return the team
     */
    public Long getTeamId() {
        return team.getId();
    }

    /**
     * @param idTeam the team to set
     */
    public void setTeamId(Long idTeam) {
        this.idTeam = idTeam;
    }
}
