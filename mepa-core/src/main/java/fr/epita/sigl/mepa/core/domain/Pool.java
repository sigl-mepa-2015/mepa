package fr.epita.sigl.mepa.core.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Pool")
@NamedQueries({
        @NamedQuery(name = "Pool.findById", query = "FROM Pool o WHERE o.id=:id"),
        @NamedQuery(name = "Pool.findAll", query = "FROM Pool o"),
        @NamedQuery(name = "Pool.findAllByTournamentId", query ="SELECT p FROM Pool p LEFT JOIN p.tournament t ON t.id = :tournamentId")})
public class Pool implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable=false)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@OneToMany
    private Set<Game> games;
	
	@OneToMany
	private Set<Team> teams;

	@ManyToOne(targetEntity=Tournament.class)
	@JoinColumn(name="TOURNAMENT_ID")
	private Tournament tournament;

    public Pool(String name, Tournament tournament) {
        this.name = name;
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

	/**
	 * @return the games
	 */
	public Set<Game> getGames() {
		return games;
	}

	/**
	 * @param games the games to set
	 */
	public void setGames(Set<Game> games) {
		this.games = games;
	}

	/**
	 * @return the teams
	 */
	public Set<Team> getTeams() {
		return teams;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	/**
	 * @return the tournament
	 */
	public Tournament getTournament() {
		return tournament;
	}

	/**
	 * @param tournament the tournament to set
	 */
	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}



}
