package fr.epita.sigl.mepa.core.domain;

import java.lang.Enum;
import java.lang.Float;
import java.lang.Integer;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@NamedQueries({
        @NamedQuery(name = "Match.findById", query = "FROM Match o WHERE o.id=:id"),
        @NamedQuery(name = "Match.findAll", query = "FROM Match o") })
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    
    private Pool pool;

    @ManyToOne
    @JoinColumn(name="POOL_ID", nullable=false)
    public Pool getPool() { return pool; }

    @NotNull
    private Integer scoreTeam1;

    @NotNull
    private Integer scoreTeam2;

    private Float duration;
    private Team team1;
    private Team team2;

    public enum enumStatus {
            EC ("en cours"),
            AF ("à faire"),
            T ("terminer");

        private final String name;

        private enumStatus(String s) {
            name = s;
        }
    };

    private enumStatus status;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScoreTeam1() {
        return scoreTeam1;
    }

    // Add team

    public void setScoreTeamOne(Integer scoreTeamOne) {
        this.scoreTeam1 = scoreTeamOne;
    }

    public Integer getScoreTeam2() {
        return scoreTeam2;
    }

    public void setScoreTeamTwo(Integer scoreTeamTwo) {
        this.scoreTeam2 = scoreTeamTwo;
    }

    public fr.epita.sigl.mepa.core.domain.Match.enumStatus getStatus() {
        return status;
    }

    public void setStatus(fr.epita.sigl.mepa.core.domain.Match.enumStatus status) {
        this.status = status;
    }

    public Float getDuration() {
        return duration;
    }

    public void setDuration(Float duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}

}
