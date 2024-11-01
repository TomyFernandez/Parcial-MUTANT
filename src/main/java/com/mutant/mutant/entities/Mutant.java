package com.mutant.mutant.entities;


import lombok.*;



import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@ToString
@Table(name="mutant")

public class Mutant extends Base{

    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="poder")
    private String poder;
    @Column(name="dna")
    private String dna;
    @Column(name="isMutant")
    private boolean isMutant;
}
