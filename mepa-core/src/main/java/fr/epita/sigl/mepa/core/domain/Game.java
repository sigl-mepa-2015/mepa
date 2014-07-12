package fr.epita.sigl.mepa.core.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="GAME")
@NamedQueries({
        @NamedQuery(name = "Game.findById", query = "FROM Game o WHERE o.id=:id"),
        @NamedQuery(name = "Game.findAll", query = "FROM Game o"),
        @NamedQuery(name = "Game.findAllComingByTournamentId", 
        query = "SELECT Count(g) FROM Game g WHERE g.pool.tournament.id = :tournamentId AND g.status = 'TODO'"),
        @NamedQuery(name = "Game.findAllEndedByTournamentId", 
        query = "SELECT Count(g) FROM Game g WHERE g.pool.tournament.id = :tournamentId AND g.status = 'ENDED'")})
public class Game implements Serializable{

	private static final long serialVersionUID = 1L;

    public enum GameStatus {
        TODO("TODO"), PROGESS("PROGRESS"), DONE("DONE");

        private final String val;
        GameStatus(String val) { this.val = val;}

        public String getGameStatus() {
            return val;
        }
    }
	
	private Long id;
    private Set<Team> teams;
	private int resultTeam1;	
	private int resultTeam2;
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

    @ManyToMany(targetEntity = Team.class)
    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

	@Column(name="GAME_RTEAM1")
	public int getResultTeam1() {
		return resultTeam1;
	}

	public void setResultTeam1(int resultTeam1) {
		this.resultTeam1 = resultTeam1;
	}

	@Column(name="GAME_RTEAM2")
	public int getResultTeam2() {
		return resultTeam2;
	}

	public void setResultTeam2(int resultTeam2) {
		this.resultTeam2 = resultTeam2;
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
}
