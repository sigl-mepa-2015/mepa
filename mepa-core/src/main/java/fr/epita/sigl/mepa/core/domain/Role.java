package fr.epita.sigl.mepa.core.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by Alexandre on 7/12/2014.
 */

@Entity
@Table(name = "Role")
@NamedQueries({
        @NamedQuery(name = "Player.findById", query = "FROM Player o WHERE o.id=:id"),
        @NamedQuery(name = "Player.findAll", query = "FROM Player o")})
public class Role {
	
	@Id
	private Long id;
}
