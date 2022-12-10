package model.comparators;

import model.data.Depot;
import model.data.Employee;

import java.util.Comparator;

public class EmployeeNameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return CharSequence.compare(o1.getFullname(), o2.getFullname());
    }
}
