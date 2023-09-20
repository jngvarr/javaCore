package ru.gb.exceptions;

public class MyArrayDataException extends Exception {
    private String name;
    private int dimX;
    private int dimY;


    public int getX() {
        return dimX;
    }

    public int getY() {
        return dimY;
    }

    public String getName() {
        return name;
    }


    public MyArrayDataException(String name, String message, int dimX, int dimY) {
        super(message);
        this.name = name;
        this.dimX = dimX;
        this.dimY = dimY;
    }
}
