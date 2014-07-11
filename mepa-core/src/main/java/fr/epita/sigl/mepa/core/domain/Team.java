package fr.epita.sigl.mepa.core.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="Team")
@NamedQueries({
    @NamedQuery(name = "Team.findById", query = "FROM Team o WHERE o.id=:id"),
    @NamedQuery(name = "Team.findAll", query = "FROM Team o") })
public class Team  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable=false)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;

    @OneToMany
    private Set<TeamResult> teamResults;

    public Team(String name) {
        this.name = name;
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
}
