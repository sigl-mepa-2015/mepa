package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.RoleDao;
import fr.epita.sigl.mepa.core.domain.Role;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Role role) {
        this.getSession().save(role);
    }

    @Override
    public void update(Role role) {
        this.getSession().saveOrUpdate(role);
    }

    @Override
    public void delete(Role role) {
        this.getSession().delete(role);
    }

    @Override
    public Role getById(Long id) {
        Query query = this.getSession().getNamedQuery("Role.findById");
        query.setParameter("id", id);
        return (Role) query.uniqueResult();
    }

    @Override
    public List<Role> getAll() {
        Query query = this.getSession().getNamedQuery("Role.findAll");
        return query.list();
    }
}
