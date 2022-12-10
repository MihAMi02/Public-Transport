package Verkehrbestrieb_exceptions;

public class VehicleNotFoundException extends VerkehrsbetriebException {
    public VehicleNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
