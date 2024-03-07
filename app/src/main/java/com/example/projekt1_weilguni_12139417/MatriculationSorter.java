package com.example.projekt1_weilguni_12139417;

import java.util.ArrayList;
import java.util.List;

public class MatriculationSorter {

    public static String sortMatriculationNumber(String matriculationNumber) {
        List<Integer> evenDigits = new ArrayList<>();
        List<Integer> oddDigits = new ArrayList<>();

        collectEvenAndOddDigits(matriculationNumber,evenDigits,oddDigits);

        //Händisches Sortieren
        sortList(evenDigits);
        sortList(oddDigits);

        // Sortieren durch den Aufruf von Sort
        //Collections.sort(evenDigits);
        //Collections.sort(oddDigits);

        return sortedNumberList(evenDigits,oddDigits);
    }


    private static void collectEvenAndOddDigits(String matriculationNumber, List<Integer> evenDigits, List<Integer> oddDigits) {
        for (int i = 0; i < matriculationNumber.length(); i++) {
            int digit = Character.getNumericValue(matriculationNumber.charAt(i));
            if (digit % 2 == 0) {
                evenDigits.add(digit);
            } else {
                oddDigits.add(digit);
            }
        }
    }

    private static void sortList(List<Integer> list){

        for(int i = 0; i < list.size(); i++) {

            int min = i;

            for (int j = i+1; j < list.size(); j++) {

                if (list.get(j) < list.get(min)) {
                    min = j;
                }
            }
            int temp = list.get(i);
            list.set(i, list.get(min));
            list.set(min, temp);
        }
    }

    private static String sortedNumberList(List<Integer> evenDigits, List<Integer> oddDigits){

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