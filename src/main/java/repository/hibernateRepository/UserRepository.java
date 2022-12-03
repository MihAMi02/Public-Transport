package repository.hibernateRepository;

import model.data.Ticket;
import model.data.Customer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;


public class UserRepository implements repository.interfaces.UserRepository
{
    List<Customer> userList;

    public UserRepository(){
        this.userList = new ArrayList<>();
        fetch();
    }

    private void fetch(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        userList = manager.createQuery("SELECT customer FROM Customer customer").getResultList();
    }


    @Override
    public boolean add(Customer entity) {
        boolean found = false;
        for(Customer user : this.userList){
            if(user.getUsername().equals(entity.getUsername())){
                found = true;
                break;
            }
        }
        if(!found){
            this.userList.add(entity);
            return true;
        }
        return false;
    }

    @Override
    public Customer remove(String s) {
        Customer temp = this.find(s);
        if(temp != null){
            this.userList.remove(temp);
        }
        return temp;
    }

    @Override
    public void update(Customer newEntity, String s) {
        for(int i=0; i<this.userList.size(); i++){
            if(this.userList.get(i).getUsername().equals(s)){
                this.userList.set(i, newEntity);
            }
        }
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
    public void addFare(String username, Ticket ticket) {
        this.find(username).addFare(ticket);
    }

    public void delFare(String username, Ticket ticket){
        this.find(username).removeFare(ticket);
    }
}
