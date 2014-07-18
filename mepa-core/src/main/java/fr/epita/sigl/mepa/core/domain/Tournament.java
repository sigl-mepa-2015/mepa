package fr.epita.sigl.mepa.core.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by Quentin on 15/07/2014.
 */
@Entity
@Table(name = "TOURNAMENT")
@NamedQueries({
        @NamedQuery(name = "Tournament.findById", query = "FROM Tournament t WHERE t.id=:id"),
        @NamedQuery(name = "Tournament.findAll", query = "FROM Tournament t ORDER BY name")})
public class Tournament implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String type;
    @Digits(integer = 10, fraction = 0)
    @Min(0)
    private Integer maxTeamNumber;
    @NotBlank
    private String name;
    private Set<Phase> phases;
    @Digits(integer = 10, fraction = 0)
    @Min(0)
    private Integer maxPlayerNumber;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "maxTeamNumber", nullable = true)
    public Integer getMaxTeamNumber() {
        return maxTeamNumber;
    }

    public void setMaxTeamNumber(Integer maxTeamNumber) {
        this.maxTeamNumber = maxTeamNumber;
    }

    @Column(name = "type", nullable = true)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Phase.class, mappedBy = "tournament")
    @ElementCollection(targetClass = Phase.class)
    public Set<Phase> getPhases() {
        return phases;
    }

    public void setPhases(Set<Phase> phases) {
        this.phases = phases;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "maxPlayerNumber", nullable = true)
    public Integer getMaxPlayerNumber() {
        return maxPlayerNumber;
    }

    public void setMaxPlayerNumber(Integer maxPlayerNumber) {
        this.maxPlayerNumber = maxPlayerNumber;
    }
}
