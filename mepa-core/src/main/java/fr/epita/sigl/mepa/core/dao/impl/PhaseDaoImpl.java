package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.PhaseDao;
import fr.epita.sigl.mepa.core.domain.Phase;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhaseDaoImpl implements PhaseDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Phase phase) {
        this.getSession().save(phase);
    }

    @Override
    public void update(Phase phase) {
        this.getSession().saveOrUpdate(phase);
    }

    @Override
    public void delete(Phase phase) {
        this.getSession().delete(phase);
    }

    @Override
    public Phase getById(Long id) {
        Query query = this.getSession().getNamedQuery("Phase.findById");
        query.setParameter("id", id);
        return (Phase) query.uniqueResult();
    }

    @Override
    public Phase getByType(String type) {
        Query query = this.getSession().getNamedQuery("Phase.findByType");
        query.setParameter("type", type);
        return (Phase) query.uniqueResult();
    }

    @Override
    public List<Phase> getAll() {
        Query query = this.getSession().getNamedQuery("Phase.findAll");
        return query.list();
    }
}
