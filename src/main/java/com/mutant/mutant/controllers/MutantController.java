package com.mutant.mutant.controllers;

import com.mutant.mutant.dtos.MutantRequest;
import com.mutant.mutant.entities.Mutant;
import com.mutant.mutant.repositories.MutantRepository;
import com.mutant.mutant.services.MutantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/mutant")
public class MutantController {

    private final MutantService mutantService;
    private final MutantRepository mutantRepository;
    public MutantController(MutantService mutantService, MutantRepository mutantRepository){
        this.mutantService = mutantService;
        this.mutantRepository = mutantRepository;
    }
    @Operation(summary = "Detectar si un humano es mutante")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Es mutante", content= @Content),
            @ApiResponse(responseCode = "403", description = "No es mutante", content = @Content),
            @ApiResponse(responseCode = "400", description = "Error en la solicitud", content = @Content )
    })
    @PostMapping("/")
    public ResponseEntity<?> checkMutant(@RequestBody MutantRequest mutant1) {
        String[] dnaArray = mutant1.getDna();


        try {
            boolean isMutant = mutantService.isMutant(dnaArray);
            if (isMutant){
                Mutant mutant = Mutant.builder()
                        .nombre(mutant1.getNombre())
                        .apellido(mutant1.getApellido())
                        .poder(mutant1.getPoder())
                        .dna(String.join(",", dnaArray))
                        .isMutant(true)
                        .build();
                mutantRepository.saveIfNotExists(mutant);
                return ResponseEntity.ok().build();
            } else {
                Mutant mutant = Mutant.builder()
                        .nombre(mutant1.getNombre())
                        .apellido(mutant1.getApellido())
                        .poder(mutant1.getPoder())
                        .dna(String.join(",", dnaArray))
                        .isMutant(false)
                        .build();
                mutantRepository.saveIfNotExists(mutant);
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error, por favor intente más tarde\"}");
        }

    }

}
