package ru.gb.regular.model;

public class Dog extends Animal {

    /**
     * Класс-наследник
     */
    public Dog() {
    }

    @Override
    public String voice() {
        return "Гав-Гав!";
    }
}
