package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.GameDao;
import fr.epita.sigl.mepa.core.domain.Game;
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
    public Long getComingGameById(Long id) {
        Query query = this.getSession().getNamedQuery("Game.findAllComingByPhaseId");
        query.setParameter("phaseId", id);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long getProgressGameById(Long id) {
        Query query = this.getSession().getNamedQuery("Game.findAllProgressByPhaseId");
        query.setParameter("phaseId", id);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long getEndedGameById(Long id) {
        Query query = this.getSession().getNamedQuery("Game.findAllEndedByPhaseId");
        query.setParameter("phaseId", id);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long countTodoGameByPoolId(Long id) {
        Query query = this.getSession().getNamedQuery("Game.CountTodoGameByPoolId");
        query.setParameter("poolId", id);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long countProgressGameByPoolId(Long id) {
        Query query = this.getSession().getNamedQuery("Game.CountProgressGameByPoolId");
        query.setParameter("poolId", id);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long countEndedGameByPoolId(Long id) {
        Query query = this.getSession().getNamedQuery("Game.CountEndedGameByPoolId");
        query.setParameter("poolId", id);
        return (Long) query.uniqueResult();
    }

    @Override
    public List<Game> getGameByTeamId(Long id) {
        Query query = this.getSession().getNamedQuery("Game.findAllByTeamId");
        query.setParameter("teamId", id);
        return query.list();
    }

    @Override
    public Long countEndedGameByTeamId(Long id) {
        Query query = this.getSession().getNamedQuery("Game.CountEndedGameByTeamId");
        query.setParameter("teamId", id);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long countTodoGameByTeamId(Long id) {
        Query query = this.getSession().getNamedQuery("Game.CountTodoGameByTeamId");
        query.setParameter("teamId", id);
        return (Long) query.uniqueResult();
    }

    @Override
    public Long countProgressGameByTeamId(Long id) {
        Query query = this.getSession().getNamedQuery("Game.CountProgressGameByTeamId");
        query.setParameter("teamId", id);
        return (Long) query.uniqueResult();
    }

}
