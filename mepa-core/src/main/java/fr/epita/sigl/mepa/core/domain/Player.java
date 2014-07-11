package fr.epita.sigl.mepa.core.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alexandre on 7/12/2014.
 */
@Entity
@Table(name = "Player")
@NamedQueries({
        @NamedQuery(name = "Player.findById", query = "FROM Player o WHERE o.id=:id"),
        @NamedQuery(name = "Player.findAll", query = "FROM Player o")})
public class Player implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    @OneToOne(optional = false)
    @JoinColumn(name = "USER_ID", unique = true, nullable = false)
    private User user;

    public Player() {

    }

    public Player(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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
}
