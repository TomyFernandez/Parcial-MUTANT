package com.mutant.mutant.servicetest;


import com.mutant.mutant.repositories.MutantRepository;
import com.mutant.mutant.services.MutantService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MutantServiceTest {

    @InjectMocks
    private MutantService mutantService;

    @Mock
    private MutantRepository mutantRepository;

    @Before
    public void setUp() {
        // Puedes inicializar algunos datos comunes aquí si es necesario
    }

    @Test
    public void testIsMutant_WithMutantDna() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"};
        boolean isMutant = mutantService.isMutant(dna);
        Assert.assertTrue(isMutant);
    }

    @Test
    public void testIsMutant_WithNonMutantDna() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTG"};
        boolean isMutant = mutantService.isMutant(dna);
        Assert.assertFalse(isMutant);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsMutant_WithInvalidDna() {
        String[] dna = {"ATGCGA", "CAGTGC", "TTATTT", "AGACGG", "GCGTCA", "TCACTZ"}; // 'Z' is invalid
        mutantService.isMutant(dna);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testIsMutant_WithEmptyDna() {
        String[] dna = {};
        mutantService.isMutant(dna);
    }
}