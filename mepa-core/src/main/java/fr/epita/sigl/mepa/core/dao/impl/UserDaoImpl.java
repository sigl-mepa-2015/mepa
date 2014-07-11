package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.UserDao;
import fr.epita.sigl.mepa.core.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by SushiBalboha on 12/07/2014.
 */
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(readOnly = true)
    public void create(User user) {
        this.getSession().save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public void update(User user) {
        this.getSession().saveOrUpdate(user);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(User user) {
        this.getSession().delete(user);
    }

    @Override
    public User getById(Long id) {
        Query query = this.getSession().getNamedQuery("User.findById");
        query.setParameter("id", id);
        return (User) query.uniqueResult();
    }

    @Override
    public List<User> getAll() {
        Query query = this.getSession().getNamedQuery("User.findAll");
        return query.list();
    }
}
