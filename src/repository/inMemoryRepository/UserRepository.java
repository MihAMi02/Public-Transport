package repository.inMemoryRepository;

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
        user2.setUserType(UserType.DIRECTOR);
        this.userList.add(user1);
        this.userList.add(user2);
    }

    @Override
    public void add(User entity) {
        boolean found = false;
        for(User user : this.userList){
            if(user.getUsername().equals(entity.getUsername())){
                found = true;
                break;
            }
        }
        if(!found){
            this.userList.add(entity);
        }
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
}


