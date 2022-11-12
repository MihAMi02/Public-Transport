package model.data;

import controller.RegistrationSystem;

import java.util.ArrayList;
import java.util.List;


public class User {

   private String username;
   private String password;
   private UserType userType;
   private RegistrationSystem registrationSystem;
   private List<Ticket> tickets;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.userType = UserType.CUSTOMER;
        this.registrationSystem = null;
        this.tickets = new ArrayList<>();
    }

    public User(String username, String password, RegistrationSystem registrationSystem) {
        this.username = username;
        this.password = password;
        this.userType = UserType.CUSTOMER;
        this.registrationSystem = registrationSystem;
        this.tickets = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }

    public RegistrationSystem getRegistrationSystem() {
        return registrationSystem;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setRegistrationSystem(RegistrationSystem registrationSystem) {
        this.registrationSystem = registrationSystem;
    }

    public void addFare(Ticket ticket){
        this.tickets.add(ticket);
    }
}

