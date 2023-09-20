package ru.gb;

import java.time.LocalDate;

import static ru.gb.Employee.random;

public class RandomDay {
    int minDay = (int) LocalDate.of(1900, 1, 1).toEpochDay();
    int maxDay = (int) LocalDate.of(2015, 1, 1).toEpochDay();
    long randomDay = minDay + random.nextInt(maxDay - minDay);

    LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);

    public LocalDate getRandomBirthDate(){
        return randomBirthDate;
    }
}
