package ru.gb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.gb.Employee.*;

public class Program {

    public static void main(String[] args) {

        Employee.employees = Worker.getEmployees(15);
        List<Employee> freelancers = Freelancer.getEmployees(15);

        for (Employee freelancer : freelancers) {
            freelancer.calculateSalary();
        }
        employees.addAll(freelancers);

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        Collections.sort(employees, new EmployeeDobComparator());
        System.out.println();

        for (Employee employee : employees) {
            System.out.println(employee);
        }

    }

}
