package fr.epita.sigl.mepa.core.dao;

import fr.epita.sigl.mepa.core.domain.MepaUser;
import java.util.List;

public interface MepaUserDao {
    void create(MepaUser mepaUser);

    void update(MepaUser mepaUser);

    void delete(MepaUser mepaUser);

    MepaUser getById(Long id);

    List<MepaUser> getAll();
}