package ru.gb;

/**
 * TODO: Доработать в рамках домашней работы
 */
public class Freelancer extends Employee{
    public static int dailyRate;
    @Override
    public void calculateSalary() {
       salary = dailyRate * 8 * 20.8;
    }

    protected Freelancer(String surName, String name, double salary) {
        super(surName, name, salary);
    }
}
