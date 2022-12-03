package repository.interfaces;

import model.data.Ticket;
import model.data.Customer;

import java.util.List;

public interface UserRepository extends CrudRepository<String, Customer>{
    Customer findByUsernameAndPassword(String username, String password);

    List<Ticket> getUserFares(String username);

    void addFare(String username, Ticket ticket);

    void delFare(String username, Ticket ticket);
}
