package fr.epita.sigl.mepa.core.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * Created by Alexandre on 7/10/2014.
 */
@Entity
@Table(name="TeamResult")
@NamedQueries({
        @NamedQuery(name = "TeamResult.findById", query = "FROM TeamResult r WHERE r.id=:id"),
        @NamedQuery(name = "TeamResult.findAll", query = "FROM TeamResult r") })
public class TeamResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="winGame", nullable=false)
    private Integer winGame;

    @Column(name="loseGame", nullable=false)
    private Integer loseGame;

    @Column(name="tieGame", nullable=false)
    private Integer tieGame;

    @ManyToOne(targetEntity=Pool.class)
    @JoinColumn(name="POOL_ID")
    private Pool pool;

    @ManyToOne(targetEntity=Team.class)
    @JoinColumn(name="TEAM_ID")
    private Team team;

}
