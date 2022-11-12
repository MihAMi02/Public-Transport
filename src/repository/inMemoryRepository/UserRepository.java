package repository.inMemoryRepository;

import model.data.Ticket;
import model.data.User;
import model.data.UserType;

import java.util.ArrayList;
import java.util.List;



public class UserRepository implements repository.interfaces.UserRepository {

    List<User> userList;

    public UserRepository(){
        this.userList = new ArrayList<>();
        populate();
    }

    private void populate(){
        User user1 = new User("ionel12", "parola");
        User user2 = new User("geani_dabu", "1");
        User user3 = new User("gica_de_la_scularie", "nea");
        User user4 = new User("madam-salam", "troleibuzul");
        user1.addFare(new Ticket(3.0f, "Bilet", 1));
        user2.setUserType(UserType.DIRECTOR);
        user3.setUserType(UserType.MAINTENANCE);
        user4.setUserType(UserType.DISPATCHER);
        this.userList.add(user1);
        this.userList.add(user2);
        this.userList.add(user3);
        this.userList.add(user4);
    }

    @Override
    public boolean add(User entity) {
        boolean found = false;
        for(User user : this.userList){
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
    public User remove(String s) {
        User temp = this.find(s);
        if(temp != null){
            this.userList.remove(temp);
        }
        return temp;
    }

    @Override
    public void update(User newEntity, String s) {
        for(int i=0; i<this.userList.size(); i++){
            if(this.userList.get(i).getUsername().equals(s)){
                this.userList.set(i, newEntity);
            }
        }
    }

    @Override
    public User find(String s) {
        for(User user: this.userList){
            if(user.getUsername().equals(s))
                return user;
        }
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        for(User user: this.userList){
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


}


