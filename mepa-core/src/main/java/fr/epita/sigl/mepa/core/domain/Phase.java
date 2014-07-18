package fr.epita.sigl.mepa.core.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "PHASE")
@NamedQueries({
        @NamedQuery(name = "Phase.findById", query = "FROM Phase t WHERE t.id=:id"),
        @NamedQuery(name = "Phase.findAll", query = "FROM Phase t ORDER BY id")})
public class Phase implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private Tournament tournament;

    @NotBlank
    private String name;

    private Set<Pool> pools;

    private Set<Team> teams;

    private Date startedDate;
    private Date endDate = null;
    private long average = -1;

    @Digits(integer = 10, fraction = 0)
    @Min(0)
    private Integer maxPlayerNumber;

    protected Phase() {
        this.tournament = null;
    }

    public Phase(Tournament tournament) {
        this.tournament = tournament;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Team.class, mappedBy = "phase", fetch = FetchType.EAGER)
    @ElementCollection(targetClass = Team.class)
    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Pool.class, mappedBy = "phase", fetch = FetchType.EAGER)
    @ElementCollection(targetClass = Pool.class)
    public Set<Pool> getPools() {
        return pools;
    }

    public void setPools(Set<Pool> pools) {
        this.pools = pools;
    }

    @Column(name = "PHASE_STARTEDDATE")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getStartedDate() {
        return startedDate;
    }

    public void setStartedDate(Date startedDate) {
        this.startedDate = startedDate;
    }

    public Integer getMaxTeamNumber() {
        return tournament.getMaxTeamNumber();
    }

    public void setMaxTeamNumber(Integer maxTeamNumber) {
        if (tournament != null) tournament.setMaxTeamNumber(maxTeamNumber);
    }

    public String getType() {
        return tournament.getType();
    }

    public void setType(String type) {
        if (tournament != null)
            tournament.setType(type);
    }

    public Date getEndDate() {
        if (endDate == null && this.pools != null) {
            endDate = new Date();
            float min = 0;
            this.average = 0;
            int games_ended = 0;
            int games_todo = 0;
            for (Pool p : this.getPools()) {
                for (Game g : p.getGames()) {
                    if (g.getStatus() == Game.GameStatus.DONE) {
                        min += g.getDuration();
                        games_ended += 1;
                    } else {
                        games_todo += 1;
                    }
                }
            }
            average = (long) (min / games_ended);
            endDate.setTime(endDate.getTime() + average * 60 * 1000 * games_todo);
        }
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @ManyToOne
    @JoinColumn(name = "TOURNAMENT_ID")
    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public long getAverage() {
        if (average == -1)
            this.getEndDate();
        return average;
    }

    public Integer getMaxPlayerNumber() {
        return tournament.getMaxPlayerNumber();
    }

    public void setMaxPlayerNumber(Integer maxPlayerNumber) {
        this.maxPlayerNumber = maxPlayerNumber;
    }

    public void setAverage(long moy) {
        this.average = moy;
    }
}
