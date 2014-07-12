package fr.epita.sigl.mepa.core.domain;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;



@Entity
@Table(name="TOURNAMENT")
@NamedQueries({
        @NamedQuery(name = "Tournament.findById", query = "FROM Tournament t WHERE t.id=:id"),
        @NamedQuery(name = "Tournament.findAll", query = "FROM Tournament t") })
public class Tournament implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Set<Pool> pools;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMaxTeamNumber() {
        return maxTeamNumber;
    }

    public void setMaxTeamNumber(Integer maxTeamNumber) {
        this.maxTeamNumber = maxTeamNumber;
    }

    private Integer maxTeamNumber;

    private String type;
	private Date startedDate;

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="TOURNAMENT_ID", nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

    @Column(name="TOURNAMENT_NAME", nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade=CascadeType.ALL, targetEntity = Pool.class, mappedBy = "tournament", fetch = FetchType.EAGER)
	//@JoinTable(name="TOURNAMENT_POOL", joinColumns = {@JoinColumn(name="TOURNAMENT_ID")}, inverseJoinColumns = {@JoinColumn(name="POOL_ID")})
	//@Column
    public Set<Pool> getPools() {
		return pools;
	}

	public void setPools(Set<Pool> pools) {
		this.pools = pools;
	}

	@Column(name="TOURNAMENT_STARTEDDATE")
	@Temporal(TemporalType.TIMESTAMP)
	public Date getStartedDate() {
		return startedDate;
	}

	public void setStartedDate(Date startedDate) {
		this.startedDate = startedDate;
	}
}
