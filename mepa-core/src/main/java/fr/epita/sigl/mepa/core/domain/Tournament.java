package fr.epita.sigl.mepa.core.domain;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

@Entity
@Table(name="TOURNAMENT")
@NamedQueries({
        @NamedQuery(name = "Tournament.findById", query = "FROM Tournament t WHERE t.id=:id"),
        @NamedQuery(name = "Tournament.findAll", query = "FROM Tournament t") })
public class Tournament implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable=false)
	private Long id;

	@Column(name="name", nullable=false)
    @NotBlank
	private String name;

    @Column(name="maxTeamNumber", nullable=true)
    @Digits(integer = 10, fraction = 0)
    @Min(0)
    private Integer maxTeamNumber;

    @Column(name="type", nullable=true)
    private String type;

	@OneToMany
	private Set<Pool> pools;

	private Date startedDate;

    public Tournament() {
        name = null;
        maxTeamNumber = null;
        type = null;
    }

    public Tournament(String name, Integer maxTeamNumber, String type) {
        this.name = name;
        this.maxTeamNumber = maxTeamNumber;
        this.type = type;
    }

	@OneToMany(cascade=CascadeType.ALL, targetEntity = Pool.class, mappedBy = "tournament", fetch = FetchType.EAGER)
    public Set<Pool> getPools() {
		return pools;
	}

	public void setPools(Set<Pool> pools) {
		this.pools = pools;
	}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="TOURNAMENT_ID", nullable=false)
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    @Column(name="TOURNAMENT_NAME", nullable=false)
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Integer getMaxTeamNumber() { return maxTeamNumber; }

    public void setMaxTeamNumber(Integer maxTeamNumber) { this.maxTeamNumber = maxTeamNumber; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }
	
	@Column(name="TOURNAMENT_STARTEDDATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}
}