package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.Phase;

import java.util.List;

public interface PhaseDao {

    void create(Phase phase);

    void update(Phase phase);

    void delete(Phase phase);

    Phase getById(Long id);

    Phase getByType(String type);

    List<Phase> getAll();
}
