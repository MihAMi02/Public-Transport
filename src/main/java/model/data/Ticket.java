package model.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@AllArgsConstructor
public class Ticket {

    @Id
    private int id;

    private double value;
    private String type;
    private LocalDate buyDate;
    @Getter
    @Setter
    @ManyToOne(cascade = CascadeType.MERGE)
    private Customer userid;

    @Getter
    @Setter
    @ManyToMany(mappedBy = "usedTickets")
    private List<Line> linesUsedOn;

    public Ticket() {
        linesUsedOn = new ArrayList<>();
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

    public void addLine(Line line){
        this.linesUsedOn.add(line);
    }

    public void delLine(Line line){
        this.linesUsedOn.remove(line);
    }

    public Line getLine(String lineNumber) {
        for(Line line: this.linesUsedOn){
            if(Objects.equals(line.getLineNumber(), lineNumber)){
                return line;
            }
        }
        return null;
    }


}
