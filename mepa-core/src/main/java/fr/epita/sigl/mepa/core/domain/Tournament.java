package fr.epita.sigl.mepa.core.domain;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;


@Entity
@Table(name="Tournament")
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

    @Column(name="maxTeamNumber")
    @Digits(integer = 10, fraction = 0)
    @Min(0)
    private Integer maxTeamNumber;

    @Column(name="type", nullable=true)
    private String type;
	
	@OneToMany
	private Set<Pool> pools;

	@Column(name="startedDate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startedDate;

    public Tournament() {
        name = null;
        maxTeamNumber = 0;
        type = null;
    }

    public Tournament(String name, Integer maxTeamNumber, String type) {
        this.name = name;
        this.maxTeamNumber = maxTeamNumber;
        this.type = type;
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

    public Integer getMaxTeamNumber() { return maxTeamNumber; }

    public void setMaxTeamNumber(Integer maxTeamNumber) { this.maxTeamNumber = maxTeamNumber; }

    public void setMaxPoolNumber(String maxPoolNumber) {
        this.maxTeamNumber = Integer.parseInt(maxPoolNumber);
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

	/**
	 * @return the pools
	 */
	public Set<Pool> getPools() {
		return pools;
	}

	/**
	 * @param pools the pools to set
	 */
	public void setPools(Set<Pool> pools) {
		this.pools = pools;
	}
}
