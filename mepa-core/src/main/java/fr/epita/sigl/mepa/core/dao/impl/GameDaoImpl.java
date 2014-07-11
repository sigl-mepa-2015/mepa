package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.GameDao;
import fr.epita.sigl.mepa.core.dao.PoolDao;
import fr.epita.sigl.mepa.core.domain.Game;
import fr.epita.sigl.mepa.core.domain.Pool;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameDaoImpl implements GameDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(Game game) {
        this.getSession().save(game);
    }

    @Override
    public void update(Game game) {
        this.getSession().saveOrUpdate(game);
    }

    @Override
    public void delete(Game game) {
        this.getSession().delete(game);
    }

    @Override
    public Game getById(Long id) {
        Query query = this.getSession().getNamedQuery("Game.findById");
        query.setParameter("id", id);
        return (Game) query.uniqueResult();
    }

    @Override
    public List<Game> getAll() {
        Query query = this.getSession().getNamedQuery("Game.findAll");
        return query.list();
    }
    
    @Override
    public int getComingGameById(Long id)
    {
    	 Query query = this.getSession().getNamedQuery("Game.findAllComingByTournamentId");
         query.setParameter("id", id);
         return (int) query.uniqueResult();
    }
    
    @Override
    public int getEndedGameById(Long id)
    {
    	 Query query = this.getSession().getNamedQuery("Game.findAllEndedByTournamentId");
         query.setParameter("id", id);
         return (int) query.uniqueResult();
    }
}
