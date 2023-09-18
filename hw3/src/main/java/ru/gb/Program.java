package ru.gb;

import java.util.Collections;
import java.util.List;

import static ru.gb.Employee.*;

public class Program {

    public static void main(String[] args) {

        Employee.employees = Worker.getEmployees(15);

        Employee.employees.add(new Freelancer(surNames[random.nextInt(surNames.length)],
                names[random.nextInt(surNames.length)],calculateSalary()));



        for (Employee employee: employees) {
            System.out.println(employee);
        }

        Collections.sort(employees, new EmployeeNameComparator());
        System.out.println();

        for (Employee employee: employees) {
            System.out.println(employee);
        }

    }

}
