package fr.epita.sigl.mepa.core.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Alexandre on 7/12/2014.
 */
@Entity
@Table(name = "MepaUser")
@NamedQueries({
        @NamedQuery(name = "MepaUser.findById", query = "FROM MepaUser o WHERE o.id=:id"),
        @NamedQuery(name = "MepaUser.findAll", query = "FROM MepaUser o")})
public class MepaUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "login", nullable = false)
    private String login;

    @Column(name = "pwd", nullable = false)
    private String pwd;

    @OneToOne (optional = true, mappedBy = "mepaUser")
    private Player player;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="USER_ROLES")
    private Set<Role> roles;

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        if (this.roles == null) {
            this.roles = new HashSet<>();
        }
        roles.add(role);
    }

    public MepaUser() {

    }

    public MepaUser(String name, String login) {
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}