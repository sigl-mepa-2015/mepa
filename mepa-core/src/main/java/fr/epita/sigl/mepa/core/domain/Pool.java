package fr.epita.sigl.mepa.core.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "POOL")
@NamedQueries({
        @NamedQuery(name = "Pool.findById", query = "FROM Pool o WHERE o.id=:id"),
        @NamedQuery(name = "Pool.findAll", query = "FROM Pool o")})
public class Pool implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long id;
    private String name;

    private Set<Game> games;
    private Set<Team> teams;
    private Phase phase;

    public Pool(String name, Phase phase) {
        this.name = name;
        this.phase = phase;
    }

    public Pool() {
        this.name = "";
        this.phase = null;

    }

    @ManyToOne
    @JoinColumn(name = "PHASE_ID")
    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {

        this.phase = phase;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POOL_ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //@OneToMany(cascade=CascadeType.ALL)
    //@JoinTable(name="POOL_GAME", joinColumns = {@JoinColumn(name="POOL_ID")}, inverseJoinColumns = {@JoinColumn(name="GAME_ID")}
    @OneToMany(cascade = CascadeType.ALL, targetEntity = Game.class, mappedBy = "pool", fetch = FetchType.EAGER)
    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Team.class, mappedBy = "pool", fetch = FetchType.EAGER)
    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

}
