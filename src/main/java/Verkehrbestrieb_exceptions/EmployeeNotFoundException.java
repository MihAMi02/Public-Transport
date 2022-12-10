package Verkehrbestrieb_exceptions;

public class EmployeeNotFoundException extends VerkehrsbetriebException {
    public EmployeeNotFoundException(String errorMessage){
        super(errorMessage);
    }
}