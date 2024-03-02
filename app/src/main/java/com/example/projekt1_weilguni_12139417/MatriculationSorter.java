package com.example.projekt1_weilguni_12139417;

public class MatriculationSorter {

    public static String sortMatriculationNumber(String matriculationNumber) {
        StringBuilder sortedMatriculation = new StringBuilder();
        StringBuilder evenDigits = new StringBuilder();
        StringBuilder oddDigits = new StringBuilder();

        for (int i = 0; i < matriculationNumber.length(); i++) {
            int digit = Character.getNumericValue(matriculationNumber.charAt(i));
            if (digit % 2 == 0) {
                evenDigits.append(digit);
            } else {
                oddDigits.append(digit);
            }
        }

        sortedMatriculation.append(evenDigits).append(oddDigits);

        return sortedMatriculation.toString();
    }
}
