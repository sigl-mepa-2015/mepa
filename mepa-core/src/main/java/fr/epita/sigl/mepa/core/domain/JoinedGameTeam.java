package fr.epita.sigl.mepa.core.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alexandre on 7/12/2014.
 */

@Entity
@Table(name = "JoinedGameTeam")
@NamedQueries({@NamedQuery(name = "JoinedGameTeam.findAllByGameId", query = "FROM JoinedGameTeam j WHERE j.game.id=:gameId"),
	@NamedQuery(name = "JoinedGameTeam.getScoreGoalByTeamId", 
		query = "SELECT sum(j.score) FROM JoinedGameTeam j WHERE j.team.id=:teamId")})

public class JoinedGameTeam implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Game game;
    private Team team;
    private Integer score;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="JOINEDGAMETEAM_ID", nullable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "GAME_ID")
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Column(name = "score", nullable=true)
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
