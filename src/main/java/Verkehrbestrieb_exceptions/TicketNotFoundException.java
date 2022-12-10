package Verkehrbestrieb_exceptions;

public class TicketNotFoundException extends VerkehrsbetriebException {
    public TicketNotFoundException(String errorMessage){
        super(errorMessage);
    }
}