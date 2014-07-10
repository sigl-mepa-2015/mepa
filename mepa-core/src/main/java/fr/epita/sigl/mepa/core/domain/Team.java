package fr.epita.sigl.mepa.core.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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
}
