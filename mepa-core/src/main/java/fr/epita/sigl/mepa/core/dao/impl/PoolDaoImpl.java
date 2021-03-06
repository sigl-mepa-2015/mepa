package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.PoolDao;
import fr.epita.sigl.mepa.core.domain.Pool;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PoolDaoImpl implements PoolDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Pool pool) {
        this.getSession().save(pool);
    }

    @Override
    public void update(Pool pool) {
        this.getSession().saveOrUpdate(pool);
    }

    @Override
    public void delete(Pool pool) {
        this.getSession().delete(pool);
    }

    @Override
    public Pool getById(Long id) {
        Query query = this.getSession().getNamedQuery("Pool.findById");
        query.setParameter("id", id);
        return (Pool) query.uniqueResult();
    }

    @Override
    public List<Pool> getAll() {
        Query query = this.getSession().getNamedQuery("Pool.findAll");
        return query.list();
    }
}
