package fr.epita.sigl.mepa.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="GAME")
@NamedQueries({
        @NamedQuery(name = "Game.findById", query = "FROM Game o WHERE o.id=:id"),
        @NamedQuery(name = "Game.findAll", query = "FROM Game o")})
public class Game implements Serializable{

	private static final long serialVersionUID = 1L;

    public enum GameStatus {TODO, PROGESS, DONE};
	
	private Long id;
	//private Team team1;
	//private Team team2;
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

//	@Column(name="GAME_TEAM1", nullable=false)
//	public Team getTeam1() {
//		return team1;
//	}
//
//	public void setTeam1(Team team1) {
//		this.team1 = team1;
//	}
//
//	@Column(name="GAME_TEAM2", nullable=false)
//	public Team getTeam2() {
//		return team2;
//	}
//
//	public void setTeam2(Team team2) {
//		this.team2 = team2;
//	}

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
