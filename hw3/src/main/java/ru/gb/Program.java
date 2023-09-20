package ru.gb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import static ru.gb.Employee.*;

public class Program {

    public static void main(String[] args) throws ParseException {

//        LocalDate date =LocalDate.of(1970 + (int) (Math.random() * (2005 - 1970)),random.nextInt(12), random.nextInt(31));
//        System.out.println(date);


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

