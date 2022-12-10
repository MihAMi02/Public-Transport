package Verkehrbestrieb_exceptions;

public class UserNotFoundException extends VerkehrsbetriebException {
    public UserNotFoundException(String errorMessage){
        super(errorMessage);
    }
}