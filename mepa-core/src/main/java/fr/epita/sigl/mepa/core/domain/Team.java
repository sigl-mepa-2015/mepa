package fr.epita.sigl.mepa.core.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="TEAM")
@NamedQueries({
    @NamedQuery(name = "Team.findById", query = "FROM Team o WHERE o.id=:id"),
    @NamedQuery(name = "Team.findAll", query = "FROM Team o") })
public class Team  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String name;
    
    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="TEAM_ID", nullable=false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="name", nullable=false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
