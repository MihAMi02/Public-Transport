package Verkehrbestrieb_exceptions;



public class ProgramNotFoundException extends VerkehrsbetriebException {
    public ProgramNotFoundException(String errorMessage){
        super(errorMessage);
    }
}