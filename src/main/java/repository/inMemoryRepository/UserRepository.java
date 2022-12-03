package repository.inMemoryRepository;

import model.data.Ticket;
import model.data.Customer;
import model.data.UserType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class UserRepository implements repository.interfaces.UserRepository {

    List<Customer> userList;

    public UserRepository(){
        this.userList = new ArrayList<>();
        populate();
    }

    private void populate(){
        Customer user1 = new Customer("ionel12", "parola");
        Customer user2 = new Customer("geani_dabu", "1");
        Customer user3 = new Customer("gica_de_la_scularie", "nea");
        Customer user4 = new Customer("madam-salam", "troleibuzul");
        Ticket ticket = new Ticket(700.0f, "Abonament 6 luni", 1, LocalDate.parse("2022-11-13"));
        user1.addFare(ticket);
        user2.setUserType(UserType.DIRECTOR);
        user3.setUserType(UserType.MAINTENANCE);
        user4.setUserType(UserType.DISPATCHER);
        this.userList.add(user1);
        this.userList.add(user2);
        this.userList.add(user3);
        this.userList.add(user4);
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


