package model.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double value;
    private String type;
    private LocalDate buyDate;
    @Getter
    @Setter
    @ManyToOne
    private Customer userid;

    public Ticket() {

    }

    public LocalDate getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(LocalDate buyDate) {
        this.buyDate = buyDate;
    }


    public Ticket(double value, String type, LocalDate date) {
        this.value = value;
        this.type = type;
        this.buyDate = date;
    }

    public Ticket(double value, String type, int id) {
        this.value = value;
        this.type = type;
        this.id = id;
    }

    public Ticket(double value, String type, int id, LocalDate date) {
        this.value = value;
        this.type = type;
        this.id = id;
        this.buyDate = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
