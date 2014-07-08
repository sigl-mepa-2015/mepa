package fr.epita.sigl.mepa.core.service.impl;


import fr.epita.sigl.mepa.core.dao.TournamentDao;
import fr.epita.sigl.mepa.core.domain.Tournament;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
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
        assertThat(tournamentToCreate.getCreated()).isCloseTo(now, deltaInMilliseconds);
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


}

