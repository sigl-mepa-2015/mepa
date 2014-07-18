package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.TournamentDao;
import fr.epita.sigl.mepa.core.domain.Tournament;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Quentin on 17/07/2014.
 */
@Repository
public class TournamentDaoImpl implements TournamentDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Tournament tournament) {
        this.getSession().save(tournament);
    }

    @Override
    public void update(Tournament tournament) {
        this.getSession().saveOrUpdate(tournament);
    }

    @Override
    public void delete(Tournament tournament) {
        this.getSession().delete(tournament);
    }

    @Override
    public Tournament getById(Long id) {
        Query query = this.getSession().getNamedQuery("Tournament.findById");
        query.setParameter("id", id);
        return (Tournament) query.uniqueResult();
    }

    @Override
    public List<Tournament> getAll() {
        Query query = this.getSession().getNamedQuery("Tournament.findAll");
        return query.list();
    }
}

