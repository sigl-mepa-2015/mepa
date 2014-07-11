package fr.epita.sigl.mepa.core.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TEAMRESULT")
@NamedQueries({
        @NamedQuery(name = "TeamResult.findById", query = "FROM TeamResult r WHERE r.id=:id"),
        @NamedQuery(name = "TeamResult.findAll", query = "FROM TeamResult r")
      /*  @NamedQuery(name = "TeamResult.findAllByTournamentId",
        query = "FROM TeamResult t "
        		+ "LEFT JOIN t.pool po "
        		+ "ON po.tournament.id = :tournamentId "
        		+ "WHERE t.pool.id = po.id ORDER BY winGame")*/})
public class TeamResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Integer winGame;
    private Integer loseGame;
    private Integer drawGame;
    private Pool pool;
    private Team team;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="TEAMRESULT_ID", nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @Column(name="TEAMRESULT_WG", nullable=false)
	public Integer getWinGame() {
		return winGame;
	}

	public void setWinGame(Integer winGame) {
		this.winGame = winGame;
	}

    @Column(name="TEAMRESULT_LG", nullable=false)
	public Integer getLoseGame() {
		return loseGame;
	}

	public void setLoseGame(Integer loseGame) {
		this.loseGame = loseGame;
	}

    @Column(name="TEAMRESULT_DG", nullable=false)
	public Integer getDrawGame() {
		return drawGame;
	}

	public void setDrawGame(Integer drawGame) {
		this.drawGame = drawGame;
	}

    @Column(name="TEAMRESULT_POOL", nullable=false)
	public Pool getPool() {
		return pool;
	}

	public void setPool(Pool pool) {
		this.pool = pool;
	}

    @Column(name="TEAMRESULT_TEAM", nullable=false)
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
