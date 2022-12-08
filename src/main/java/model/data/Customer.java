package model.data;

import controller.RegistrationSystem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity


public class Customer {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userID;
    @Getter
    @Setter
    private String username;
    private String password;
    private UserType userType;
    @Transient
    private RegistrationSystem registrationSystem;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Ticket> tickets;

    public Customer(String username, String password) {
        this.username = username;
        this.password = password;
        this.userType = UserType.CUSTOMER;
        this.registrationSystem = null;
        this.tickets = new ArrayList<>();
    }

    public Customer(String username, String password, RegistrationSystem registrationSystem) {
        this.username = username;
        this.password = password;
        this.userType = UserType.CUSTOMER;
        this.registrationSystem = registrationSystem;
        this.tickets = new ArrayList<>();
    }

    public Customer() {

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

    public void removeFare(Ticket ticket){
        this.tickets.remove(ticket);
    }
}

