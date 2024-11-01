package com.mutant.mutant.repositories;


import com.mutant.mutant.entities.Mutant;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MutantRepository extends org.springframework.data.jpa.repository.JpaRepository<Mutant, Long>{
    Optional<Mutant> findByDna(String dnaSequence);

    long countByIsMutant(boolean isMutant);

    default Mutant saveIfNotExists(Mutant mutant){
        Optional<Mutant> existingMutant = findByDna(mutant.getDna());
        return existingMutant.orElseGet(() -> save(mutant));
    }
}
