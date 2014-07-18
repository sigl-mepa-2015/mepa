package fr.epita.sigl.mepa.core.service;

import fr.epita.sigl.mepa.core.domain.Phase;

import java.util.List;

public interface PhaseService {

    void createPhase(Phase phase);

    void updatePhase(Phase phase);

    void deletePhase(Phase phase);

    Phase getPhaseById(Long id);

    Phase getPhases(String type);

    List<Phase> getAllPhases();

    String[] getPhaseEndDate(Phase phase);
}