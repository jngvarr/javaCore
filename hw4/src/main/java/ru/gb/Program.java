package ru.gb;

import ru.gb.exceptions.MyArrayDataException;
import ru.gb.exceptions.MyArraySizeException;
import ru.gb.model.Arrays;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {
        Arrays arrays = new Arrays();
        try {
            System.out.println("Обработка первого массива: ");
            String[][] arr1 = arrays.createArray("src\\main\\java\\ru\\gb\\sources\\source.txt");
            arrays.printTowDimArray(arr1);
            System.out.printf("Сумма элементов массива равна = %d.\n", arrays.sumElements(arr1));
            System.out.println();
        } catch (MyArraySizeException e) {
            System.out.printf("Ошибка \"%s\" при попытке загрузки массива из файла %s (Размер массива более %d.)\n",
                    e.getName(), e.getMessage(), e.getSize());
        } catch (MyArrayDataException e) {
            System.out.printf("Ошибка \"%s\" при попытке суммирования элементов массива. Элемент [%d,%d] - " +
                    "формат не верен (source - %s.)\n", e.getName(), e.getX(), e.getY(),e.getMessage());
        }
        try {
            System.out.println("Обработка второго массива: ");
            String[][] arr2 = arrays.createArray("src\\main\\java\\ru\\gb\\sources\\wrongDataSource.txt");
            arrays.printTowDimArray(arr2);
            System.out.printf("Сумма элементов массива равна = %d.\n", arrays.sumElements(arr2));
            System.out.println();
        } catch (MyArraySizeException e) {
            System.out.printf("Ошибка \"%s\" при попытке загрузки массива из файла %s (Размер массива более %d.)\n",
                    e.getName(), e.getMessage(), e.getSize());
        } catch (MyArrayDataException e) {
            System.out.printf("Ошибка \"%s\" при попытке суммирования элементов массива. Элемент [%d,%d] - " +
                    "формат не верен (source - %s.)\n", e.getName(), e.getX(), e.getY(),e.getMessage());
        }
        try {
            System.out.println();
            System.out.println("Обработка третьего массива: \nМассив из файла wrongDataSource.txt");
            String[][] arr3 = arrays.createArray("src\\main\\java\\ru\\gb\\sources\\wrongSizeSource.txt");
            arrays.printTowDimArray(arr3);
            System.out.printf("Сумма элементов массива равна = %d.\n", arrays.sumElements(arr3));

        } catch (MyArraySizeException e) {
            System.out.printf("Ошибка \"%s\" при попытке загрузки массива из файла %s (Размер массива более %d.)\n",
                    e.getName(), e.getMessage(), e.getSize());
        } catch (MyArrayDataException e) {
            System.out.printf("Ошибка \"%s\" при попытке суммирования элементов массива. Элемент [%d,%d] - " +
                    "формат не верен (source - %s.)\n", e.getName(), e.getX(), e.getY(),e.getMessage());
        }
    }
}

