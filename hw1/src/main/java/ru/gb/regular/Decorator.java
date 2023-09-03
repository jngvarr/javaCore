package ru.gb.regular;

import ru.gb.regular.model.Animal;

/**
 * Декоратор. Он декорирует, то есть, накладывает на результат декорации.
 * Внешний вид важен, поэтому этот класс существует.
 */
public class Decorator {

    /**
     * Функция декорирования результата работы приложения
     *
     * @param animal животное, что-то говорящее
     * @return Отформатированная строка.
     */
    public static void decorate(Animal animal) {
        /*
         * Метод создает строку, на основе вида животного,
         * добавляя строку результата выполнения метода voice()
         * */
        System.out.println("The " + animal.getClass().getSimpleName() + " says: "+ animal.voice());
    }

}