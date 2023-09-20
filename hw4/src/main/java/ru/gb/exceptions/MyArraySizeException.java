package ru.gb.exceptions;

public class MyArraySizeException extends Exception {
    private String name;
    private int size;

    public int getSize() {
        return size;
    }

    public String getName() {
        return name;
    }

    public MyArraySizeException(String name, String message, int size) {
        super(message);
        this.size = size;
        this.name = name;
    }
}
