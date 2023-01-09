package repository.hibernateRepository;

import model.comparators.DepotNameComparator;
import model.comparators.EmployeeNameComparator;
import model.data.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeRepository implements repository.interfaces.EmployeeRepository
{

    List<Employee> employeeList;
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
    EntityManager manager = factory.createEntityManager();

    private void fetch()
    {
        factory = Persistence.createEntityManagerFactory("default");
        manager = factory.createEntityManager();
        employeeList = manager.createQuery("SELECT employee FROM Employee employee").getResultList();
    }


    public EmployeeRepository()
    {
        this.employeeList = new ArrayList<>();
        fetch();
    }


    @Override
    public boolean add(Employee entity)
    {
        manager.getTransaction().begin();
        boolean found = false;
        for(Employee employee : employeeList){
            if(Objects.equals(employee.getCnp(), entity.getCnp())){
                found = true;
                break;
            }
        }
        if(!found){
            this.employeeList.add(entity);
            manager.persist(entity);
            manager.getTransaction().commit();
            return true;
        }
        manager.getTransaction().commit();
        return false;
    }

    @Override
    public Employee remove(String s)
    {
        manager.getTransaction().begin();
        Employee temp = this.find(s);
        if(temp != null){
            this.employeeList.remove(temp);
            manager.remove(temp);
        }
        manager.getTransaction().commit();
        return temp;
    }

    @Override
    public void update(Employee newEntity, String s)
    {
        manager.getTransaction().begin();
        Employee employee = this.find(s);
        employee = manager.find(Employee.class, employee.getEmployeeID());
        employee.setCnp(newEntity.getCnp());
        employee.setFullname(newEntity.getFullname());
        employee.setVehicle(newEntity.getVehicle());
        employee.setRole(newEntity.getRole());
        employee.setSalary(newEntity.getSalary());
        employee.setWorkplace(newEntity.getWorkplace());
        manager.getTransaction().commit();
        employeeList = manager.createQuery("SELECT employee FROM Employee employee").getResultList();
    }


    @Override
    public Employee find(String s)
    {

        for(Employee employee : employeeList){
            if(employee.getCnp().equals(s)){
                return employee;
            }
        }
        return null;
    }


    @Override
    public List<Employee> filterByRole(String role)
    {
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

        if(ascending)
        {
            this.employeeList.sort(new EmployeeNameComparator());
        }
        else
        {
            this.employeeList.sort(new EmployeeNameComparator().reversed());
        }
        return this.employeeList;
    }
}

