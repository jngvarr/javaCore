package ru.gb.sample;

import ru.gb.regular.Decorator;
import ru.gb.regular.model.*;


public class Main {
    /**
     * Точка входа в программу. С неё всегда всё начинается.
     *
     * @param args стандартные аргументы командной строки
     */
    public static void main(String[] args) {
        Barn.animals.add(new Cat());
        Barn.animals.add(new Dog());
        Barn.animals.add(new Cow());
        Barn.animals.add(new Horse());
        for (Animal animal : Barn.animals) {
            System.out.println(Decorator.decorate(animal));
        }

    }
}