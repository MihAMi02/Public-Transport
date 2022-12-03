package repository.hibernateRepository;

import model.data.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository implements repository.interfaces.EmployeeRepository {

    List<Employee> employeeList;
    private void fetch(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        employeeList = manager.createQuery("SELECT employee FROM Employee employee").getResultList();
    }


    public EmployeeRepository(){
        this.employeeList = new ArrayList<>();
        fetch();
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
