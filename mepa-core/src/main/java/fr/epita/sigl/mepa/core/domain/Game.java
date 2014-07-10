package fr.epita.sigl.mepa.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="Game")
@NamedQueries({
        @NamedQuery(name = "Game.findById", query = "FROM Game o WHERE o.id=:id"),
        @NamedQuery(name = "Game.findAll", query = "FROM Game o") })
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
}
