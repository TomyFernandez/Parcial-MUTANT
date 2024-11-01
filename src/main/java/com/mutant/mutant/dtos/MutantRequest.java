package com.mutant.mutant.dtos;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class MutantRequest {
    private String[] dna;
    private String nombre;
    private String apellido;
    private String poder;
}
