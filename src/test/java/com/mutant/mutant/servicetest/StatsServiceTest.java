package com.mutant.mutant.servicetest;


import com.mutant.mutant.dtos.StatsResponse;
import com.mutant.mutant.repositories.MutantRepository;
import com.mutant.mutant.services.StatsService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StatsServiceTest {

    @InjectMocks
    private StatsService statsService;

    @Mock
    private MutantRepository mutantRepository;

    @Before
    public void setUp() {
        // Puedes inicializar algunos datos comunes aquí si es necesario
    }

    @Test
    public void testGetStats_WithData() {
        Mockito.when(mutantRepository.countByIsMutant(true)).thenReturn(4L);
        Mockito.when(mutantRepository.countByIsMutant(false)).thenReturn(2L);

        StatsResponse statsResponse = statsService.getStats();
        Assert.assertEquals(4L, statsResponse.getCountMutantDna());
        Assert.assertEquals(2L, statsResponse.getCountHumanDna());
        Assert.assertEquals(2.0, statsResponse.getRatio(), 0.0);
    }

    @Test
    public void testGetStats_WithNoData() {
        Mockito.when(mutantRepository.countByIsMutant(true)).thenReturn(0L);
        Mockito.when(mutantRepository.countByIsMutant(false)).thenReturn(0L);

        StatsResponse statsResponse = statsService.getStats();
        Assert.assertEquals(0L, statsResponse.getCountMutantDna());
        Assert.assertEquals(0L, statsResponse.getCountHumanDna());
        Assert.assertEquals(0.0, statsResponse.getRatio(), 0.0);
    }
}