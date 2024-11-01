package com.mutant.mutant.services;


import com.mutant.mutant.repositories.MutantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.BitSet;

@Service
public class MutantService {


    private final MutantRepository mutantRepository;
    private static final int SEQUENCE_LENGTH = 4;


    @Autowired
    public MutantService(MutantRepository mutantRepository) {
        this.mutantRepository = mutantRepository;
    }

    public boolean isMutant(String[] dna) {
        validateDna(dna);
        int n = dna.length;
        int sequenceCount = 0;
        BitSet checkedPositions = new BitSet(n * n);

        // Recorrer la matriz por fila y columna
        for (int index = 0; index < n * n; index++) {
            int row = index / n;
            int col = index % n;

            // Si la posición ya fue verificada, saltarla
            if (checkedPositions.get(index)) {
                continue;
            }

            // Verificar secuencias mutantes
            if (checkDirection(dna, row, col, n, 0, 1, checkedPositions)) { // Derecha
                sequenceCount++;
                if (sequenceCount > 1) return true;
            }

            if (checkDirection(dna, row, col, n, 1, 0, checkedPositions)) { // Abajo
                sequenceCount++;
                if (sequenceCount > 1) return true;
            }

            if (checkDirection(dna, row, col, n, 1, 1, checkedPositions)) { // Diagonal derecha-abajo
                sequenceCount++;
                if (sequenceCount > 1) return true;
            }

            if (checkDirection(dna, row, col, n, 1, -1, checkedPositions)) { // Diagonal izquierda-abajo
                sequenceCount++;
                if (sequenceCount > 1) return true;
            }
        }
        return sequenceCount > 1;
    }

    // Verificar en una dirección específica y marcar las posiciones ya revisadas
    private boolean checkDirection(String[] dna, int row, int col, int n, int rowInc, int colInc, BitSet checkedPositions) {
        if (isInBounds(row, col, rowInc, colInc, n)) {
            int index = row * n + col;

            // Si encontramos una secuencia consecutiva, la marcamos
            if (isConsecutive(dna, row, col, rowInc, colInc)) {
                markCheckedPositions(row, col, rowInc, colInc, n, checkedPositions);
                return true;
            }
        }
        return false;
    }

    // Verificar si hay secuencia consecutiva de 4 caracteres en una dirección
    private boolean isConsecutive(String[] dna, int row, int col, int rowInc, int colInc) {
        char currentChar = dna[row].charAt(col);
        for (int i = 1; i < SEQUENCE_LENGTH; i++) {
            if (dna[row + i * rowInc].charAt(col + i * colInc) != currentChar) {
                return false;
            }
        }
        return true;
    }

    // Marcar posiciones como ya revisadas en el BitSet
    private void markCheckedPositions(int row, int col, int rowInc, int colInc, int n, BitSet checkedPositions) {
        for (int i = 0; i < SEQUENCE_LENGTH; i++) {
            int newRow = row + i * rowInc;
            int newCol = col + i * colInc;
            checkedPositions.set(newRow * n + newCol);
        }
    }

    // Verificar si la dirección está dentro de los límites de la matriz
    private boolean isInBounds(int row, int col, int rowInc, int colInc, int n) {
        int newRow = row + (SEQUENCE_LENGTH - 1) * rowInc;
        int newCol = col + (SEQUENCE_LENGTH - 1) * colInc;
        return newRow >= 0 && newRow < n && newCol >= 0 && newCol < n;
    }

    // Validar la matriz de ADN
    private void validateDna(String[] dna) {
        if (dna == null || dna.length == 0) {
            throw new IllegalArgumentException("La matriz de ADN no puede ser nula o vacía.");
        }
        int n = dna.length;
        for (String row : dna) {
            if (row.length() != n) {
                throw new IllegalArgumentException("La matriz de ADN debe ser cuadrada.");
            }
            for (char c : row.toCharArray()) {
                if (c != 'A' && c != 'T' && c != 'C' && c != 'G') {
                    throw new IllegalArgumentException("La matriz de ADN contiene caracteres no válidos.");
                }
            }
        }
    }
}
