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
@Table(name="Game")
@NamedQueries({
        @NamedQuery(name = "Game.findById", query = "FROM Game o WHERE o.id=:id"),
        @NamedQuery(name = "Game.findAll", query = "FROM Game o") ,
        @NamedQuery(name = "Game.findAllByPoolId", query = "SELECT g FROM Game g LEFT JOIN g.pool p ON p.id = :poolId")})
public class Game implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public enum GameStatus {TODO, PROGESS, DONE};
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable=false)
	private Long id;
	
	@Column(name="team1", nullable=false)
	private Team team1;
	
	@Column(name="team2", nullable=false)
	private Team team2;
	
	@Column(name="resultTeam1")
	private int resultTeam1;
	
	@Column(name="resultTeam2")
	private int resultTeam2;
	
	@Column(name="duration")
	private int duration;
	
	@Enumerated(EnumType.STRING)
	@Column(name="status", nullable=false)
	private GameStatus status;
	
	@ManyToOne(targetEntity=Pool.class)
	@JoinColumn(name="POOL_ID")
	private Pool pool;

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
	 * @return the team1
	 */
	public Team getTeam1() {
		return team1;
	}

	/**
	 * @param team1 the team1 to set
	 */
	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	/**
	 * @return the team2
	 */
	public Team getTeam2() {
		return team2;
	}

	/**
	 * @param team2 the team2 to set
	 */
	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

	/**
	 * @return the resultTeam1
	 */
	public int getResultTeam1() {
		return resultTeam1;
	}

	/**
	 * @param resultTeam1 the resultTeam1 to set
	 */
	public void setResultTeam1(int resultTeam1) {
		this.resultTeam1 = resultTeam1;
	}

	/**
	 * @return the resultTeam2
	 */
	public int getResultTeam2() {
		return resultTeam2;
	}

	/**
	 * @param resultTeam2 the resultTeam2 to set
	 */
	public void setResultTeam2(int resultTeam2) {
		this.resultTeam2 = resultTeam2;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the status
	 */
	public GameStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(GameStatus status) {
		this.status = status;
	}

	/**
	 * @return the pool
	 */
	public Pool getPool() {
		return pool;
	}

	/**
	 * @param pool the pool to set
	 */
	public void setPool(Pool pool) {
		this.pool = pool;
	}
}
