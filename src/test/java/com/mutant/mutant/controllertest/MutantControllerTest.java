package com.mutant.mutant.controllertest;

import com.mutant.mutant.controllers.MutantController;
import com.mutant.mutant.controllers.StatsController;
import com.mutant.mutant.dtos.MutantRequest;
import com.mutant.mutant.dtos.StatsResponse;
import com.mutant.mutant.entities.Mutant;
import com.mutant.mutant.repositories.MutantRepository;
import com.mutant.mutant.services.MutantService;
import com.mutant.mutant.services.StatsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
public class MutantControllerTest {

    @InjectMocks
    private MutantController mutantController;

    @InjectMocks
    private StatsController statsController;

    @Mock
    private MutantService mutantService;

    @Mock
    private StatsService statsService;

    @Mock
    private MutantRepository mutantRepository;

    @Test
    public void testNotNullController() {
        Assert.assertNotNull(mutantController);
        Assert.assertNotNull(statsController);
    }

    @Test
    public void testCheckMutant_IsMutant() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        MutantRequest mutantRequest = new MutantRequest();
        mutantRequest.setDna(dna);
        mutantRequest.setNombre("Nombre");
        mutantRequest.setApellido("Apellido");
        mutantRequest.setPoder("Poder");

        Mockito.when(mutantService.isMutant(any())).thenReturn(true);
        Mockito.when(mutantRepository.saveIfNotExists(any())).thenReturn(new Mutant());

        ResponseEntity<?> response = mutantController.checkMutant(mutantRequest);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCheckMutant_IsNotMutant() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCTCTA", "TCACTG"};
        MutantRequest mutantRequest = new MutantRequest();
        mutantRequest.setDna(dna);
        mutantRequest.setNombre("Nombre");
        mutantRequest.setApellido("Apellido");
        mutantRequest.setPoder("Poder");

        Mockito.when(mutantService.isMutant(any())).thenReturn(false);
        Mockito.when(mutantRepository.saveIfNotExists(any())).thenReturn(new Mutant());

        ResponseEntity<?> response = mutantController.checkMutant(mutantRequest);
        Assert.assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testGetStats() {
        StatsResponse statsResponse = new StatsResponse(4, 2, 2.0);
        Mockito.when(statsService.getStats()).thenReturn(statsResponse);

        ResponseEntity<StatsResponse> response = statsController.getStats();
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assert.assertEquals(4L, response.getBody().getCountMutantDna());
        Assert.assertEquals(2L, response.getBody().getCountHumanDna());
        Assert.assertEquals(2.0, response.getBody().getRatio(), 0.0);
    }
}