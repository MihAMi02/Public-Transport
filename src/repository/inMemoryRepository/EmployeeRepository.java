package repository.inMemoryRepository;

import model.data.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements repository.interfaces.EmployeeRepository {

    List<Employee> employeeList;

    public EmployeeRepository(){
        this.employeeList = new ArrayList<>();
        populate();
    }

    private void populate(){
        Employee employee1 = new Employee(1, "5060809123456", "Circa Honorius Edward Adrian", "Driver", "Floreasca", 2300);
        Employee employee2 = new Employee(2, "5060809123455", "Dabu Oprica Geani", "Director", "Floreasca", 5000);
        this.employeeList.add(employee1);
        this.employeeList.add(employee2);
    }

    @Override
    public boolean add(Employee entity) {
        boolean found = false;
        for(Employee employee : employeeList){
            if(employee.getCnp() == entity.getCnp()){
                found = true;
                break;
            }
        }
        if(!found){
            this.employeeList.add(entity);
            return true;
        }
        return false;
    }

    @Override
    public Employee remove(String s) {
        Employee temp = this.find(s);
        if(temp != null){
            this.employeeList.remove(temp);
        }
        return temp;
    }

    @Override
    public void update(Employee newEntity, String s) {
        for(int i = 0; i < this.employeeList.size(); i++){
            if(this.employeeList.get(i).getCnp().equals(s)){
                this.employeeList.set(i, newEntity);
            }
        }
    }

    @Override
    public Employee find(String s) {
        for(Employee employee : employeeList){
            if(employee.getCnp().equals(s)){
                return employee;
            }
        }
        return null;
    }

    @Override
    public List<Employee> filterByRole(String role) {
        return null;
    }

    @Override
    public List<Employee> filterByWorkplace(String workplace) {
        return null;
    }

    @Override
    public List<Employee> sortBySalary(boolean ascending) {
        return null;
    }

    @Override
    public List<Employee> sortByName(boolean ascending) {
        return null;
    }
}
