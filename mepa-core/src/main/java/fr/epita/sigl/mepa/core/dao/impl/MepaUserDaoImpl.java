package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.MepaUserDao;
import fr.epita.sigl.mepa.core.domain.MepaUser;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SushiBalboha on 12/07/2014.
 */

@Repository
public class MepaUserDaoImpl implements MepaUserDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional
    public void create(MepaUser mepaUser) {
        this.getSession().save(mepaUser);
    }

    @Override
    @Transactional(readOnly = true)
    public void update(MepaUser mepaUser) {
        this.getSession().saveOrUpdate(mepaUser);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(MepaUser mepaUser) {
        this.getSession().delete(mepaUser);
    }

    @Override
    public MepaUser getById(Long id) {
        Query query = this.getSession().getNamedQuery("MepaUser.findById");
        query.setParameter("id", id);
        return (MepaUser) query.uniqueResult();
    }

    @Override
    public List<MepaUser> getAll() {
        Query query = this.getSession().getNamedQuery("MepaUser.findAll");
        return query.list();
    }


}
