package ru.gb;

public class MyArraySizeException extends Exception {
    @Override
    public String getMessage() {
//        System.out.println("Несоответствие размера архива");
        return "Несоответствие размера архива";
    }
}
