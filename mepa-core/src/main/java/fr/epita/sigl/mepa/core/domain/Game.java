package fr.epita.sigl.mepa.core.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="GAME")
@NamedQueries({
        @NamedQuery(name = "Game.findById", query = "FROM Game o WHERE o.id=:id"),
        @NamedQuery(name = "Game.findAll", query = "FROM Game o"),
        @NamedQuery(name = "Game.findAllComingByPhaseId",
                query = "SELECT Count(g) FROM Game g WHERE g.pool.phase.id = :phaseId AND g.status = 'TODO'"),
        @NamedQuery(name = "Game.findAllProgressByPhaseId",
                query = "SELECT Count(g) FROM Game g WHERE g.pool.phase.id = :phaseId AND g.status = 'PROGRESS'"),
        @NamedQuery(name = "Game.findAllEndedByPhaseId",
                query = "SELECT Count(g) FROM Game g WHERE g.pool.phase.id = :phaseId AND g.status = 'DONE'"),
        @NamedQuery(name = "Game.CountTodoGameByPoolId", 
        query = "SELECT Count(g) FROM Game g WHERE g.pool.id = :poolId AND g.status = 'TODO'"),
        @NamedQuery(name = "Game.CountProgressGameByPoolId", 
        query = "SELECT Count(g) FROM Game g WHERE g.pool.id = :poolId AND g.status = 'PROGRESS'"),
        @NamedQuery(name = "Game.CountEndedGameByPoolId", 
        query = "SELECT Count(g) FROM Game g WHERE g.pool.id = :poolId AND g.status = 'DONE'"),
        @NamedQuery(name = "Game.findAllByTeamId", 
        query = "SELECT g FROM Game g INNER JOIN g.joinedGameTeams j ON j.team.id = :teamId WHERE g.id = j.game.id"),
        @NamedQuery(name = "Game.CountEndedGameByTeamId", 
        query = "SELECT Count(g) FROM Game g INNER JOIN g.joinedGameTeams j ON j.team.id = :teamId WHERE g.id = j.game.id AND g.status = 'DONE'"),
        @NamedQuery(name = "Game.CountProgressGameByTeamId", 
        query = "SELECT Count(g) FROM Game g INNER JOIN g.joinedGameTeams j ON j.team.id = :teamId WHERE g.id = j.game.id AND g.status = 'PROGRESS'"),
        @NamedQuery(name = "Game.CountTodoGameByTeamId", 
        query = "SELECT Count(g) FROM Game g INNER JOIN g.joinedGameTeams j ON j.team.id = :teamId WHERE g.id = j.game.id AND g.status = 'TODO'")
})
public class Game implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long id;
	private int duration;
	private GameStatus status;
    private Pool pool;
    private Set<JoinedGameTeam> joinedGameTeams;

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

    @OneToMany(targetEntity = JoinedGameTeam.class, mappedBy = "game", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public Set<JoinedGameTeam> getJoinedGameTeams() {
        return joinedGameTeams;
    }

    public void setJoinedGameTeams(Set<JoinedGameTeam> joinedGameTeams) {
        this.joinedGameTeams = joinedGameTeams;
    }

    public enum GameStatus {
        TODO("TODO"), PROGRESS("PROGRESS"), DONE("DONE");

        private final String val;

        GameStatus(String val) {
            this.val = val;
        }

        public String getGameStatus() {
            return val;
        }
    }
}
