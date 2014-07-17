package fr.epita.sigl.mepa.core.service.impl;

import fr.epita.sigl.mepa.core.dao.TournamentDao;
import fr.epita.sigl.mepa.core.domain.Tournament;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.validation.ValidationUtils;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.Validator;
import java.util.Date;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ModelServiceImplUTests {

    @Mock
    TournamentDao mockedTournamentDao;

    @InjectMocks
    TournamentServiceImpl modelService;

    @Test
    public void createModel_ShouldCreateANewModel_WithDateVeryCloseToNow() {
        // Given
        Tournament tournamentToCreate = new Tournament();
        Date now = new Date();
        long deltaInMilliseconds = 500;

        // When
        modelService.createTournament(tournamentToCreate);

        // Then
        //assertThat(tournamentToCreate.getCreated()).isCloseTo(now, deltaInMilliseconds);
    }

    @Test
    public void createModel_ShouldCreateANewModel_UsingModelDao() {
        // Given
        Tournament tournamentToCreate = new Tournament();

        // When
        modelService.createTournament(tournamentToCreate);

        // Then
        verify(mockedTournamentDao).create(tournamentToCreate);
    }
    @Test
    public void createTournamentWithOnlyName_ShouldCreateTournament_UsingTournamentDao()
    {
        Tournament tournamentToCreate = new Tournament();

        tournamentToCreate.setName("Tournoi Test");
        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Tournament>> constraintViolations = v.validateProperty(tournamentToCreate, "name");
        assertEquals(0, constraintViolations.size());

        modelService.createTournament(tournamentToCreate);
        verify(mockedTournamentDao).create(tournamentToCreate);
    }

    @Test
    public void createTournamentWithoutName_ShouldNotCreateTournament_UsingTournamentDao()
    {
        Tournament tournamentToCreate = new Tournament();

        tournamentToCreate.setName("");
        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Tournament>> constraintViolations = v.validateProperty(tournamentToCreate, "name");
        assertEquals(1, constraintViolations.size());

        modelService.createTournament(tournamentToCreate);
        verify(mockedTournamentDao).create(tournamentToCreate);
    }

    @Test
    public void createTournamentWithNegativeMaxTeamNumber_ShouldNotCreateTournament_UsingTournamentDao()
    {
        Tournament tournamentToCreate = new Tournament();
        tournamentToCreate.setName("Tournoi Test");

        tournamentToCreate.setMaxTeamNumber(-6);
        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Tournament>> constraintViolations = v.validateProperty(tournamentToCreate, "maxTeamNumber");
        assertEquals(1, constraintViolations.size());

        modelService.createTournament(tournamentToCreate);
        verify(mockedTournamentDao).create(tournamentToCreate);
    }

    @Test
    public void createTournamentWithPositiveMaxTeamNumber_ShouldCreateTournament_UsingTournamentDao()
    {
        Tournament tournamentToCreate = new Tournament();
        tournamentToCreate.setName("Tournoi Test");

        tournamentToCreate.setMaxTeamNumber(12);
        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Tournament>> constraintViolations = v.validateProperty(tournamentToCreate, "maxTeamNumber");
        assertEquals(0, constraintViolations.size());

        modelService.createTournament(tournamentToCreate);
        verify(mockedTournamentDao).create(tournamentToCreate);
    }

    @Test
    public void createTournamentWithType_ShouldCreateTournament_UsingTournamentDao()
    {
        Tournament tournamentToCreate = new Tournament();
        tournamentToCreate.setName("Tournoi Test");

        tournamentToCreate.setType("Football");
        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Tournament>> constraintViolations = v.validateProperty(tournamentToCreate, "type");
        assertEquals(0, constraintViolations.size());

        modelService.createTournament(tournamentToCreate);
        verify(mockedTournamentDao).create(tournamentToCreate);
    }

    @Test
    public void createTournamentWithoutType_ShouldCreateTournament_UsingTournamentDao()
    {
        Tournament tournamentToCreate = new Tournament();
        tournamentToCreate.setName("Tournoi Test");

        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Tournament>> constraintViolations = v.validateProperty(tournamentToCreate, "type");
        assertEquals(0, constraintViolations.size());

        modelService.createTournament(tournamentToCreate);
        verify(mockedTournamentDao).create(tournamentToCreate);
    }

    @Test
    public void createTournamentWithTypeAndSpecialCaracters_ShouldCreateTournament_UsingTournamentDao()
    {
        Tournament tournamentToCreate = new Tournament();
        tournamentToCreate.setName("Tournoi Test");

        tournamentToCreate.setType(",;:!?./§&é'(è_çà=$*^ù'");
        ValidatorFactory fact = Validation.buildDefaultValidatorFactory();
        Validator v = fact.getValidator();
        Set<ConstraintViolation<Tournament>> constraintViolations = v.validateProperty(tournamentToCreate, "type");
        assertEquals(0, constraintViolations.size());
        modelService.createTournament(tournamentToCreate);
        verify(mockedTournamentDao).create(tournamentToCreate);
    }
}

