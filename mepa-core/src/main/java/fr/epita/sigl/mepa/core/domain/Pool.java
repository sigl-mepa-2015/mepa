package fr.epita.sigl.mepa.core.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.*;

@Entity
@Table(name="POOL")
@NamedQueries({
        @NamedQuery(name = "Pool.findById", query = "FROM Pool o WHERE o.id=:id"),
        @NamedQuery(name = "Pool.findAll", query = "FROM Pool o")})
public class Pool implements Serializable{

	private static final long serialVersionUID = 1L;

	
	private Long id;	
	private String name;
    private Set<Game> games;
	private Set<Team> teams;

    @ManyToOne()
    @JoinColumn(name="TOURNAMENT_ID")
    public Tournament getTournament() {
        return tournament;
    }

    public Pool(String name, Tournament tournament) {
        this.name = name;
        this.tournament = tournament;
    }
    public void setTournament(Tournament tournament) {

        this.tournament = tournament;
    }

    public Pool() {
        this.name= "";
        this.tournament = null;

    }



    private Tournament tournament;
    /**
     * @return the id
     */
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="POOL_ID", nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	//@OneToMany(cascade=CascadeType.ALL)
	//@JoinTable(name="POOL_GAME", joinColumns = {@JoinColumn(name="POOL_ID")}, inverseJoinColumns = {@JoinColumn(name="GAME_ID")}
    @OneToMany(cascade=CascadeType.ALL, targetEntity = Game.class, mappedBy = "pool", fetch = FetchType.EAGER)
    public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}

    @OneToMany(cascade=CascadeType.ALL, targetEntity = Team.class, mappedBy = "pool", fetch = FetchType.EAGER)
    public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

}
