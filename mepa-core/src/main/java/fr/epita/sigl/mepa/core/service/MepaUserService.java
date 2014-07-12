package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.MepaUser;

import java.util.List;

/**
 * Created by SushiBalboha on 12/07/2014.
 */
public interface MepaUserService {

    void createMepaUser(MepaUser mepaUser);

    void updateMepaUser(MepaUser mepaUser);

    void deleteMepaUser(MepaUser mepaUser);

    MepaUser getMepaUserById(Long id);

    List<MepaUser> getAllMepaUsers();
}
