package ru.gb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.gb.Employee.*;

public class Program {

    public static void main(String[] args) {

        Employee.employees = Worker.getEmployees(15);

        List<Employee> freelancers = new ArrayList<>();
        freelancers.add((new Freelancer(surNames[random.nextInt(surNames.length)],
                names[random.nextInt(surNames.length)])));


        for (Employee employee : employees) {
            System.out.println(employee);
        }

        Collections.sort(employees, new EmployeeNameComparator());
        System.out.println();

        for (Employee employee : employees) {
            System.out.println(employee);
        }

    }

}
