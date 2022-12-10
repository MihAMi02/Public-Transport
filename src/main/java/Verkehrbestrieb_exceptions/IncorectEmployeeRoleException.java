package Verkehrbestrieb_exceptions;

public class IncorectEmployeeRoleException extends EmployeeException {
    public IncorectEmployeeRoleException(String errorMessage){
        super(errorMessage);
    }
}