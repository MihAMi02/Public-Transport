package repository.hibernateRepository;

import model.data.Ticket;
import model.data.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


public class UserRepository implements repository.interfaces.UserRepository
{
    List<Customer> userList;
    EntityManagerFactory factory;
    EntityManager manager;

    public UserRepository(){
        this.userList = new ArrayList<>();
        fetch();
    }

    private void fetch()
    {
        factory = Persistence.createEntityManagerFactory("default");
        manager = factory.createEntityManager();
        userList = manager.createQuery("SELECT customer FROM Customer customer").getResultList();
    }


    @Override
    public boolean add(Customer entity) {
        manager.getTransaction().begin();
        boolean found = false;
        for(Customer user : this.userList){
            if(user.getUsername().equals(entity.getUsername())){
                found = true;
                break;
            }
        }
        if(!found){
            this.userList.add(entity);
            manager.persist(entity);
            manager.getTransaction().commit();
            return true;
        }
        manager.getTransaction().commit();
        return false;
    }

    @Override
    public Customer remove(String s) {
        manager.getTransaction().begin();
        Customer temp = this.find(s);
        if(temp != null){
            this.userList.remove(temp);
            manager.remove(temp);
        }
        manager.getTransaction().commit();
        return temp;
    }

    @Override
    public void update(Customer newEntity, String s)
    {
        manager.getTransaction().begin();
        Customer customer =  manager.find(Customer.class, this.find(s).getUserID());
        customer.setUserType(newEntity.getUserType());
        customer.setUsername(newEntity.getUsername());
        customer.setPassword(newEntity.getPassword());
        manager.getTransaction().commit();
        userList = manager.createQuery("SELECT customer FROM Customer customer").getResultList();
    }

    @Override
    public Customer find(String s) {
        for(Customer user: this.userList){
            if(user.getUsername().equals(s))
                return user;
        }
        return null;
    }

    @Override
    public Customer findByUsernameAndPassword(String username, String password) {
        for(Customer user: this.userList){
            if(user.getUsername().equals(username) && user.getPassword().equals(password))
                return user;
        }
        return null;
    }

    @Override
    public List<Ticket> getUserFares(String username) {
        return this.find(username).getTickets();
    }

    @Override
    public void addFare(String username, Ticket ticket)
    {

        manager.getTransaction().begin();
        Customer customer = this.find(username);
        manager.find(Customer.class, customer.getUserID());

        Ticket temp = new Ticket();
        temp.setId(ticket.getId());
        manager.find(Ticket.class, temp.getId());
        ticket.setUserid(customer);

        customer.addFare(ticket);
        manager.getTransaction().commit();


    }


    public void delFare(String username, Ticket ticket){

        manager.getTransaction().begin();
        Customer customer = this.find(username);
        manager.find(Customer.class, customer.getUserID());

        Ticket temp = new Ticket();
        temp.setId(ticket.getId());
        manager.find(Ticket.class, temp.getId());
        ticket.setUserid(customer);

        customer.removeFare(ticket);

        manager.getTransaction().commit();
    }
}
