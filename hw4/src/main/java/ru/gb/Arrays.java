package ru.gb;

import ru.gb.view.Console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;

public class Arrays {

    public void createArray() throws IOException {
        String[][] stringsArray = new String[4][4];
        int i = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(new Console().consoleQuery()))) {
            while (reader.ready()) {
                stringsArray[i] = reader.readLine().split(" ");
                i++;
            }
        }
    }

    public void printTowDimArray(String[][] arr) {
        for (int j = 0; j < arr.length; j++) {
            for (int k = 0; k < arr[j].length; k++) {
                System.out.print(arr[j][k]);
            }
            System.out.println();
        }
    }
}




