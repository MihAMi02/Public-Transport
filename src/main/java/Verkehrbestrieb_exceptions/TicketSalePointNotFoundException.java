package Verkehrbestrieb_exceptions;

public class TicketSalePointNotFoundException extends VerkehrsbetriebException {
    public TicketSalePointNotFoundException(String errorMessage){
        super(errorMessage);
    }
}