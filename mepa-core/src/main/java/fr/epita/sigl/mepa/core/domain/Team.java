package fr.epita.sigl.mepa.core.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Team")
@NamedQueries({
    @NamedQuery(name = "Team.findById", query = "FROM Team o WHERE o.id=:id"),
    @NamedQuery(name = "Team.findAll", query = "FROM Team o"),
        @NamedQuery(name = "Team.findAllOrderByPhaseId",
                query = "FROM Team t where t.phase.id = :phaseId "
                        + "ORDER BY t.winGame DESC, t.drawGame DESC, t.loseGame ASC")})

public class Team implements Serializable {

    private static final long serialVersionUID = 1L;
    Integer winGame = 0;
    Integer drawGame = 0;
    Integer loseGame = 0;
    private Long id;

    /*  public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @ManyToMany(mappedBy = "teams")
    private Set<Game> games;*/
    private String name;
    private Phase phase;
    private Pool pool;
    private Set<Player> players;
    private Set<JoinedGameTeam> joinedGameTeams;

    public Team() {

    }

    public Team(String name) {
        this.name = name;
    }

    @Column(name = "winGame", nullable = true)
    public Integer getWinGame() {
        return winGame;
    }

    public void setWinGame(Integer winGame) {
        this.winGame = winGame;
    }

    @Column(name = "drawGame", nullable = true)
    public Integer getDrawGame() {
        return drawGame;
    }

    @Column(name = "loseGame", nullable = true)
    public void setDrawGame(Integer drawGame) {
        this.drawGame = drawGame;
    }

    public Integer getLoseGame() {
        return loseGame;
    }

    public void setLoseGame(Integer loseGame) {
        this.loseGame = loseGame;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Player.class, mappedBy = "team", fetch = FetchType.EAGER)
    @OrderBy("name")
    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @ManyToOne
    @JoinColumn(name = "POOL_ID")
    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    @ManyToOne
    @JoinColumn(name = "PHASE_ID")
    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public void addWin()
    {
        this.winGame += 1;
    }

    public void addDraw()
    {
        this.drawGame += 1;
    }

    public void addLose() {
        this.loseGame += 1;
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
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
    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = JoinedGameTeam.class, mappedBy = "team")
    public Set<JoinedGameTeam> getJoinedGameTeams() {
        return joinedGameTeams;
    }

    public void setJoinedGameTeams(Set<JoinedGameTeam> joinedGameTeams) {
        this.joinedGameTeams = joinedGameTeams;
    }
}
