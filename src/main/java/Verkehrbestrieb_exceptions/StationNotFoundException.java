package Verkehrbestrieb_exceptions;

public class StationNotFoundException extends VerkehrsbetriebException {
    public StationNotFoundException(String errorMessage){
        super(errorMessage);
    }
}