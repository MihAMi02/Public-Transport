package repository.interfaces;

import model.data.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<String, Employee> {
    List<Employee> filterByRole(String role);

    List<Employee> filterByWorkplace(String workplace);

    List<Employee> sortBySalary(boolean ascending);

    List<Employee> sortByName(boolean ascending);
}
