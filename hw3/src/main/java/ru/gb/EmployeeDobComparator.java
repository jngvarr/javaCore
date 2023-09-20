package ru.gb;
import java.util.Comparator;

public class EmployeeDobComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        int res = o1.getDob().compareTo(o2.getDob());
        return res;
    }
}

