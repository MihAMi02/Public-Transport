package Verkehrbestrieb_exceptions;

public class LineNotFoundException extends VerkehrsbetriebException {
    public LineNotFoundException(String errorMessage){
        super(errorMessage);
    }
}