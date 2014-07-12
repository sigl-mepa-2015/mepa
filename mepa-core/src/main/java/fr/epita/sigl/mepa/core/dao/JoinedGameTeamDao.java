package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.JoinedGameTeam;

import java.util.List;

public interface JoinedGameTeamDao {

    void create(JoinedGameTeam joinedGameTeam);

    void update(JoinedGameTeam joinedGameTeam);

    void delete(JoinedGameTeam joinedGameTeam);

    List<JoinedGameTeam> getByGameId(Long id);
}
