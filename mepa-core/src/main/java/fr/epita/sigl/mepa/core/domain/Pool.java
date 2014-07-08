package fr.epita.sigl.mepa.core.domain;

import java.lang.Enum;
import java.lang.Float;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

//@Entity
@NamedQueries({
        @NamedQuery(name = "Pool.findById", query = "FROM Pool o WHERE o.id=:id"),
        @NamedQuery(name = "Pool.findAll", query = "FROM Pool o") })
public class Pool {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Set<Match> matchs;
    
    private Tournament tournament;
 
    private String name;
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //@OneToMany(mappedBy="pool")
    //public Set<Match> getMatchs() { return matchs; }

   // @ManyToOne()
    @JoinColumn(name="TOURNAMENT_ID", nullable=false)
    public Tournament getTournament() { return tournament; }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public Set<Match> getMatchs() {
		return matchs;
	}

	public void setMatchs(Set<Match> matchs) {
		this.matchs = matchs;
	}

}
