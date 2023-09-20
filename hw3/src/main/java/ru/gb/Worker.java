package ru.gb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class Worker extends Employee{

    private Worker(String surName, String name, double salary, LocalDate dob){
        super(surName, name, salary);
        //System.out.println("Constructor - Worker");
    }

    public static Employee getInstance() throws ParseException {
        return new Worker(
                surNames[random.nextInt(surNames.length)],
                names[random.nextInt(surNames.length)],
                random.nextInt( 250000),
                LocalDate.of((1970 + (int) (Math.random() * (2005 - 1970))),random.nextInt(12), random.nextInt(28)));
    }

    public static List<Employee> getEmployees(int count) throws ParseException {
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < count; i++)
            employees.add(getInstance());
        return employees;
    }

    @Override
    public double calculateSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return String.format("%s %s; Рабочий; Среднемесячная заработная плата (фиксированная месячная оплата): %.2f (руб.). Дата рождени: %s,",
                surName, name, salary, getDob().toString());
    }
}
