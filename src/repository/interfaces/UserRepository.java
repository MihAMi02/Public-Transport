package repository.interfaces;

import model.data.User;

public interface UserRepository extends CrudRepository<String, User>{
    User findByUsernameAndPassword(String username, String password);



}
