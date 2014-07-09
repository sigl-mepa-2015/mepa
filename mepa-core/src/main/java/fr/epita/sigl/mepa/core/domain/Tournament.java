package fr.epita.sigl.mepa.core.domain;

import java.util.Set;

import javax.persistence.*;



@Entity
@Table(name="Tournament")
@NamedQueries({
        @NamedQuery(name = "Tournament.findById", query = "FROM Tournament t WHERE t.id=:id"),
        @NamedQuery(name = "Tournament.findAll", query = "FROM Tournament t") })
public class Tournament {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable=false)
	private Long id;
	
	@Column(name="name", nullable=false)
	private String name;
	
	@OneToMany
	private Set<Pool> pools;

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
