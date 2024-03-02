package com.example.projekt1_weilguni_12139417;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatriculationSorter {

    public static String sortMatriculationNumber(String matriculationNumber) {
        List<Integer> evenDigits = new ArrayList<>();
        List<Integer> oddDigits = new ArrayList<>();

        // Durchlaufen der Matrikelnummer und Sammeln von geraden und ungeraden Ziffern
        for (int i = 0; i < matriculationNumber.length(); i++) {
            int digit = Character.getNumericValue(matriculationNumber.charAt(i));
            if (digit % 2 == 0) {
                evenDigits.add(digit);
            } else {
                oddDigits.add(digit);
            }
        }

        // Sortieren der gesammelten Ziffern
        Collections.sort(evenDigits);
        Collections.sort(oddDigits);

        // Kombinieren der sortierten Ziffern in der gewünschten Reihenfolge
        StringBuilder sortedMatriculation = new StringBuilder();
        for (int digit : evenDigits) {
            sortedMatriculation.append(digit);
        }
        for (int digit : oddDigits) {
            sortedMatriculation.append(digit);
        }

        return sortedMatriculation.toString();
    }
}