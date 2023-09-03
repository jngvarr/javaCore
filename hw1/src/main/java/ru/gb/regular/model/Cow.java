package ru.gb.regular.model;

public class Cow extends Animal {
    /**
     * Класс-наследник
     */
    public Cow() {
    }

    @Override
    public String voice() {
        return "Мууууууу!";
    }
}
