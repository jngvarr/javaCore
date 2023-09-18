package ru.gb;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Доработать в рамках домашней работы
 */
public class Freelancer extends Employee {

    @Override
    public double calculateSalary() {
        return random.nextInt(2000) * 8 * 20.8;
    }
    public static List<Employee> getEmployees(int count){
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++)
            employees.add(getInstance());
        return employees;
    }

    public static Employee getInstance(){
        return new Freelancer(
                surNames[random.nextInt(surNames.length)],
                names[random.nextInt(surNames.length)]);
    }
    protected Freelancer(String surName, String name) {
        super(surName, name);
        this.salary = calculateSalary();

    }
    @Override
    public String toString() {
        return String.format("%s %s; Фрилансер; Среднемесячная заработная плата (почасовая оплата): %.2f (руб.)",
                surName, name, salary);
    }
}
