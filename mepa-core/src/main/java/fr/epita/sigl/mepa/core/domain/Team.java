package fr.epita.sigl.mepa.core.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "Team")
@NamedQueries({
    @NamedQuery(name = "Team.findById", query = "FROM Team o WHERE o.id=:id"),
    @NamedQuery(name = "Team.findAll", query = "FROM Team o"),
    @NamedQuery(name = "Team.findAllOrderByTournamentId", 
    query = "FROM Team t where t.tournament.id = :tournamentId "
    		+ "ORDER BY t.winGame DESC, t.drawGame DESC, t.loseGame ASC")})

public class Team implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name="TOURNAMENT_ID")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name="POOL_ID")
    private Pool pool;

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    @ManyToMany(mappedBy = "teams")
    private Set<Game> games;

    @OneToMany(cascade=CascadeType.ALL, targetEntity = Player.class, mappedBy = "team", fetch = FetchType.EAGER)
    private Set<Player> players;

    @Column(name = "winGame", nullable = true)
    Integer winGame;

    @Column(name = "drawGame", nullable = true)
    Integer drawGame;

    @Column(name = "loseGame", nullable = true)
    Integer loseGame;

    public Integer getWinGame() {
        return winGame;
    }

    public void setWinGame(Integer winGame) {
        this.winGame = winGame;
    }

    public Integer getDrawGame() {
        return drawGame;
    }

    public void setDrawGame(Integer drawGame) {
        this.drawGame = drawGame;
    }

    public Integer getLoseGame() {
        return loseGame;
    }

    public void setLoseGame(Integer loseGame) {
        this.loseGame = loseGame;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    public Team() {
        
    }
    
    public Team(String name) {
        this.name = name;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
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
