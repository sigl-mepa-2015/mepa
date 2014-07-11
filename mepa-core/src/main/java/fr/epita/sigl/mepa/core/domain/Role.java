package fr.epita.sigl.mepa.core.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Alexandre on 7/12/2014.
 */

@Entity
@Table(name = "Role")
@NamedQueries({
        @NamedQuery(name = "Role.findById", query = "FROM Role o WHERE o.id=:id"),
        @NamedQuery(name = "Role.findAll", query = "FROM Role o")})
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private String authority;
    private Long id;

    @JoinTable(
            name="USER_ROLE",
            joinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")},
            inverseJoinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")})
    private Set<User> users;

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="POOL_ID", nullable=false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
