package ru.gb.model;

import ru.gb.exceptions.MyArrayDataException;
import ru.gb.exceptions.MyArraySizeException;
import ru.gb.view.ConsoleQuery;

import java.io.*;

public class Arrays {
    File file = null;

    public String[][] createArray(String path) throws IOException, MyArraySizeException {
        String[][] stringsArray = new String[4][4];
        int i = 0;
        //File file = new File(new ConsoleQuery().consoleQuery());
        file = new File(path);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while (reader.ready()) {
                if (i > 3) throw new MyArraySizeException("Данные не верны", file.getName(), stringsArray.length);
                stringsArray[i] = reader.readLine().split(" ");
                i++;
            }
        }
        return stringsArray;
    }

    public void printTowDimArray(String[][] arr) {
        System.out.printf("Массив из файла %s:\n", file.getName());
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j].length; k++) {
                System.out.print(arr[j][k]);
            }
            System.out.println();
        }
    }

    public int sumElements(String[][] arr) throws MyArrayDataException {
        int sum = 0;
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j].length; k++) {
                try {
                    sum += Integer.parseInt(arr[j][k]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Формат данных не верен", file.getName(), j, k);
                }
            }
        }
        return sum;
    }
}




