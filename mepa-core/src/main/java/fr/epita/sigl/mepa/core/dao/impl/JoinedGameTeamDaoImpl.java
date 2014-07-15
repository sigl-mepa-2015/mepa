package fr.epita.sigl.mepa.core.dao.impl;

import fr.epita.sigl.mepa.core.dao.JoinedGameTeamDao;
import fr.epita.sigl.mepa.core.domain.JoinedGameTeam;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JoinedGameTeamDaoImpl implements JoinedGameTeamDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public void create(JoinedGameTeam joinedGameTeam) {
        this.getSession().save(joinedGameTeam);
    }

    @Override
    public void update(JoinedGameTeam joinedGameTeam) {
        this.getSession().saveOrUpdate(joinedGameTeam);
    }

    @Override
    public void delete(JoinedGameTeam joinedGameTeam) {
        this.getSession().delete(joinedGameTeam);
    }

    @Override
    public List<JoinedGameTeam> getByGameId(Long id) {
        Query query = this.getSession().getNamedQuery("JoinedGameTeam.findAllByGameId");
        query.setParameter("gameId", id);
        return query.list();
    }

    @Override
    public JoinedGameTeam getJoinedGameById(Long id) {
        Query query = this.getSession().getNamedQuery("JoinedGameTeam.findById");
        query.setParameter("id", id);
        return (JoinedGameTeam) query.uniqueResult();
    }
}
