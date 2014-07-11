package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.PlayerDao;
import fr.epita.sigl.mepa.core.domain.Player;
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
public class PlayerDaoImpl implements PlayerDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    @Transactional(readOnly = true)
    public void create(Player player) {
        this.getSession().save(player);
    }

    @Override
    @Transactional(readOnly = true)
    public void update(Player player) {
        this.getSession().saveOrUpdate(player);
    }

    @Override
    @Transactional(readOnly = true)
    public void delete(Player player) {
        this.getSession().delete(player);
    }

    @Override
    public Player getById(Long id) {
        Query query = this.getSession().getNamedQuery("Player.findById");
        query.setParameter("id", id);
        return (Player) query.uniqueResult();
    }

    @Override
    public List<Player> getAll() {
        Query query = this.getSession().getNamedQuery("Player.findAll");
        return query.list();
    }

    @Override
    public List<Player> getAllByIdTeam(Long id)
    {
        Query query = this.getSession().getNamedQuery("Player.findByIdTeam");
        return query.list();
    }
}