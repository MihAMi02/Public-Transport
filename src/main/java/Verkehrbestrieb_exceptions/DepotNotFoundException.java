package Verkehrbestrieb_exceptions;

public class DepotNotFoundException extends VerkehrsbetriebException {
    public DepotNotFoundException(String errorMessage){
        super(errorMessage);
    }
}