package ru.gb.regular.model;

public class Cat extends Animal{
    /**
     * Класс-наследник
     */
    public Cat() {
    }

    @Override
    public String voice() {
       return "Мяу!";
    }
}
