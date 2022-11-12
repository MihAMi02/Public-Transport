package repository.interfaces;

import model.data.Ticket;
import model.data.User;

import java.util.List;

public interface UserRepository extends CrudRepository<String, User>{
    User findByUsernameAndPassword(String username, String password);

    List<Ticket> getUserFares(String username);

    void addFare(String username, Ticket ticket);
}
