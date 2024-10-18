package com.mutant.mutant.repositoriestest;


import com.mutant.mutant.entities.Mutant;
import com.mutant.mutant.repositories.MutantRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MutantRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MutantRepository mutantRepository;

    @Test
    public void testSaveIfNotExists_NewMutant() {
        Mutant mutant = Mutant.builder()
                .nombre("Nombre")
                .apellido("Apellido")
                .poder("Poder")
                .dna("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG")
                .isMutant(true)
                .build();

        Mutant savedMutant = mutantRepository.saveIfNotExists(mutant);
        Assert.assertNotNull(savedMutant.getId());
    }

    @Test
    public void testSaveIfNotExists_ExistingMutant() {
        Mutant mutant = Mutant.builder()
                .nombre("Nombre")
                .apellido("Apellido")
                .poder("Poder")
                .dna("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG")
                .isMutant(true)
                .build();

        entityManager.persist(mutant);
        entityManager.flush();

        Mutant savedMutant = mutantRepository.saveIfNotExists(mutant);
        Assert.assertEquals(mutant.getId(), savedMutant.getId());
    }

    @Test
    public void testFindByDna() {
        Mutant mutant = Mutant.builder()
                .nombre("Nombre")
                .apellido("Apellido")
                .poder("Poder")
                .dna("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG")
                .isMutant(true)
                .build();

        entityManager.persist(mutant);
        entityManager.flush();

        Optional<Mutant> foundMutant = mutantRepository.findByDna("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG");
        Assert.assertTrue(foundMutant.isPresent());
        Assert.assertEquals(mutant.getId(), foundMutant.get().getId());
    }

    @Test
    public void testCountByIsMutant() {
        Mutant mutant1 = Mutant.builder()
                .nombre("Nombre1")
                .apellido("Apellido1")
                .poder("Poder1")
                .dna("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG")
                .isMutant(true)
                .build();

        Mutant mutant2 = Mutant.builder()
                .nombre("Nombre2")
                .apellido("Apellido2")
                .poder("Poder2")
                .dna("ATGCGA,CAGTGC,TTATGT,AGAAGG,CCCCTA,TCACTG")
                .isMutant(false)
                .build();

        entityManager.persist(mutant1);
        entityManager.persist(mutant2);
        entityManager.flush();

        long countMutants = mutantRepository.countByIsMutant(true);
        long countNonMutants = mutantRepository.countByIsMutant(false);

        Assert.assertEquals(1, countMutants);
        Assert.assertEquals(1, countNonMutants);
    }
}