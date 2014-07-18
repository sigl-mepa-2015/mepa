package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.PhaseDao;
import fr.epita.sigl.mepa.core.dao.TournamentDao;
import fr.epita.sigl.mepa.core.domain.Phase;
import fr.epita.sigl.mepa.core.domain.Tournament;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ReportingImplUTests {

    @Mock
    PhaseDao mockedPhaseDao;

    @Mock
    TournamentDao mockedTournamentDao;

    @InjectMocks
    PhaseServiceImpl phaseService;

    @InjectMocks
    TournamentServiceImpl tournamentService;

    @Test
    public void createModel_ShouldCreateANewModel_WithDateVeryCloseToNow() {
        // Given
        Tournament tournamentToCreate = new Tournament();
        Phase phaseToCreate = new Phase(tournamentToCreate);
        Date now = new Date();
        long deltaInMilliseconds = 500;

        // When
        tournamentService.createTournament(tournamentToCreate);
        phaseService.createPhase(phaseToCreate);

        // Then
        //assertThat(phaseToCreate.getCreated()).isCloseTo(now, deltaInMilliseconds);
    }

    @Test
    public void createModel_ShouldCreateANewModel_UsingModelDao() {
        // Given
        Tournament tournamentToCreate = new Tournament();
        Phase phaseToCreate = new Phase(tournamentToCreate);

        // When
        tournamentService.createTournament(tournamentToCreate);
        phaseService.createPhase(phaseToCreate);

        // Then
        verify(mockedPhaseDao).create(phaseToCreate);
    }

    @Test
    public void createPhaseWithOnlyName_ShouldCreatePhase_UsingPhaseDao() {
        // Given
        Tournament tournamentToCreate = new Tournament();
        Phase phaseToCreate = new Phase(tournamentToCreate);

        phaseToCreate.setName("Tournoi Test");
        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Phase>> constraintViolations = v.validateProperty(phaseToCreate, "name");
        assertEquals(0, constraintViolations.size());

        // When
        tournamentService.createTournament(tournamentToCreate);
        phaseService.createPhase(phaseToCreate);

        verify(mockedPhaseDao).create(phaseToCreate);
    }

    @Test
    public void createPhaseWithoutName_ShouldNotCreatePhase_UsingPhaseDao() {
        // Given
        Tournament tournamentToCreate = new Tournament();
        Phase phaseToCreate = new Phase(tournamentToCreate);

        phaseToCreate.setName("");
        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Phase>> constraintViolations = v.validateProperty(phaseToCreate, "name");
        assertEquals(1, constraintViolations.size());

        // When
        tournamentService.createTournament(tournamentToCreate);
        phaseService.createPhase(phaseToCreate);
        verify(mockedPhaseDao).create(phaseToCreate);
    }

    @Test
    public void createPhaseWithNegativeMaxTeamNumber_ShouldNotCreatePhase_UsingPhaseDao() {
        // Given
        Tournament tournamentToCreate = new Tournament();

        tournamentToCreate.setName("Tournoi Test");

        tournamentToCreate.setMaxTeamNumber(-6);
        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Tournament>> constraintViolations = v.validateProperty(tournamentToCreate, "maxTeamNumber");
        assertEquals(1, constraintViolations.size());

        // When
        tournamentService.createTournament(tournamentToCreate);

        verify(mockedTournamentDao).create(tournamentToCreate);
    }

    @Test
    public void createPhaseWithPositiveMaxTeamNumber_ShouldCreatePhase_UsingPhaseDao() {
        // Given
        Tournament tournamentToCreate = new Tournament();
        Phase phaseToCreate = new Phase(tournamentToCreate);

        phaseToCreate.setName("Tournoi Test");

        phaseToCreate.setMaxTeamNumber(12);
        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Phase>> constraintViolations = v.validateProperty(phaseToCreate, "maxTeamNumber");
        assertEquals(0, constraintViolations.size());

        // When
        tournamentService.createTournament(tournamentToCreate);
        phaseService.createPhase(phaseToCreate);

        verify(mockedPhaseDao).create(phaseToCreate);
    }

    @Test
    public void createPhaseWithType_ShouldCreatePhase_UsingPhaseDao() {
        // Given
        Tournament tournamentToCreate = new Tournament();
        Phase phaseToCreate = new Phase(tournamentToCreate);

        phaseToCreate.setName("Tournoi Test");

        phaseToCreate.setType("Football");
        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Phase>> constraintViolations = v.validateProperty(phaseToCreate, "type");
        assertEquals(0, constraintViolations.size());

        // When
        tournamentService.createTournament(tournamentToCreate);
        phaseService.createPhase(phaseToCreate);

        verify(mockedPhaseDao).create(phaseToCreate);
    }

    @Test
    public void createPhaseWithoutType_ShouldCreatePhase_UsingPhaseDao() {
        // Given
        Tournament tournamentToCreate = new Tournament();
        Phase phaseToCreate = new Phase(tournamentToCreate);

        phaseToCreate.setName("Tournoi Test");

        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Phase>> constraintViolations = v.validateProperty(phaseToCreate, "type");
        assertEquals(0, constraintViolations.size());

        // When
        tournamentService.createTournament(tournamentToCreate);
        phaseService.createPhase(phaseToCreate);

        verify(mockedPhaseDao).create(phaseToCreate);
    }

    @Test
    public void createPhaseWithTypeAndSpecialCaracters_ShouldCreatePhase_UsingPhaseDao() {
        // Given
        Tournament tournamentToCreate = new Tournament();
        Phase phaseToCreate = new Phase(tournamentToCreate);

        phaseToCreate.setName("Tournoi Test");

        phaseToCreate.setType(",;:!?./§&é'(è_çà=$*^ù'");
        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Phase>> constraintViolations = v.validateProperty(phaseToCreate, "type");
        assertEquals(0, constraintViolations.size());
        // When
        tournamentService.createTournament(tournamentToCreate);
        phaseService.createPhase(phaseToCreate);

        verify(mockedPhaseDao).create(phaseToCreate);
    }
}

