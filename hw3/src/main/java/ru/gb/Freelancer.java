package ru.gb;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Доработать в рамках домашней работы
 */
public class Freelancer extends Employee {
    public static int dailyRate;

    @Override
    public double calculateSalary() {
        salary = dailyRate * 8 * 20.8;
        return salary;
    }
    public static List<Employee> getEmployees(int count){
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++)
            employees.add(getInstance());
        return employees;
    }

    protected Freelancer(String surName, String name) {
        super(surName, name);

    }
}
