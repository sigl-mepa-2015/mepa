package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.TeamDao;
import fr.epita.sigl.mepa.core.domain.Team;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class TeamDaoImpl implements TeamDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Team team) {
        this.getSession().save(team);
    }

    @Override
    public void update(Team team) {
        this.getSession().saveOrUpdate(team);
    }

    @Override
    public void delete(Team team) {
        this.getSession().delete(team);
    }

    @Override
    public Team getById(Long id) {
        Query query = this.getSession().getNamedQuery("Team.findById");
        query.setParameter("id", id);
        return (Team) query.uniqueResult();
    }

    @Override
    public List<Team> getAll() {
        Query query = this.getSession().getNamedQuery("Team.findAll");
        return query.list();
    }

    @Override
    public List<Team> getOrderByPhaseId(Long id) {
        Query query = this.getSession().getNamedQuery("Team.findAllOrderByPhaseId");
        query.setParameter("phaseId", id);
        return query.list();
    }
}
