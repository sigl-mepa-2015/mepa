package fr.epita.sigl.mepa.core.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import javax.persistence.*;

import com.mysql.fabric.xmlrpc.base.Array;

@Entity
@Table(name="GAME")
@NamedQueries({
        @NamedQuery(name = "Game.findById", query = "FROM Game o WHERE o.id=:id"),
        @NamedQuery(name = "Game.findAll", query = "FROM Game o"),
        @NamedQuery(name = "Game.findAllComingByTournamentId", 
        query = "SELECT Count(g) FROM Game g WHERE g.pool.tournament.id = :tournamentId AND g.status = 'TODO'"),
        @NamedQuery(name = "Game.findAllEndedByTournamentId", 
        query = "SELECT Count(g) FROM Game g WHERE g.pool.tournament.id = :tournamentId AND g.status = 'DONE'")})
public class Game implements Serializable{

	private static final long serialVersionUID = 1L;

    public enum GameStatus {
        TODO("TODO"), PROGRESS("PROGRESS"), DONE("DONE");

        private final String val;
        GameStatus(String val) { this.val = val;}

        public String getGameStatus() {
            return val;
        }
    }
	
	private Long id;
	private int duration;
	private GameStatus status;
    private Pool pool;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="GAME_ID", nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @Column(name="GAME_DURATION")
    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Enumerated(EnumType.STRING)
    @Column(name="GAME_STATUS", nullable=false)
    public GameStatus getStatus() {
        return status;
    }

    public void setStatus(GameStatus status) {
        this.status = status;
    }

    @ManyToOne()
    @JoinColumn(name="POOL_ID")
    public Pool getPool() {
        return pool;
    }

    public void setPool(Pool pool) {
        this.pool = pool;
    }

    private Set<JoinedGameTeam> joinedGameTeams;

    @OneToMany(targetEntity = JoinedGameTeam.class, mappedBy = "game", fetch = FetchType.EAGER)
    public Set<JoinedGameTeam> getJoinedGameTeams() {
        return joinedGameTeams;
    }

    public void setJoinedGameTeams(Set<JoinedGameTeam> joinedGameTeams) {
        this.joinedGameTeams = joinedGameTeams;
    }
}
