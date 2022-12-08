package model.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity

public class Line
{

    public Line() {

    }

    @Override
    public String toString() {
        return "Line{" +
                "lineNumber='" + lineNumber + '\'' +
                ", type='" + type + '\'' +
                ", specialRequirement='" + specialRequirement + '\'' +
                ", numUsedTickets=" + usedTickets.size() +
                ", stationsList=" + stationsList +
                '}';
    }

    @Id
    private String lineNumber;
    private String type;
    private String specialRequirement;


    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "Ticket_Line", joinColumns = @JoinColumn(name = "lineNumber", nullable = true), inverseJoinColumns = @JoinColumn(name = "id", nullable = true))
    private List<Ticket> usedTickets;
    @Getter
    @Setter
    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "Station_Line", joinColumns = @JoinColumn(name = "lineNumber", nullable = true), inverseJoinColumns = @JoinColumn(name = "stationId", nullable = true))
    private List<Station> stationsList;



    public Line(String lineNumber, String type, String specialRequirement, List<Station> stationsList)
    {
        this.lineNumber = lineNumber;
        this.type = type;
        this.specialRequirement = specialRequirement;
        this.stationsList = stationsList;
        this.usedTickets= new ArrayList<>();

    }

    public Line(String lineNumber, String type, String specialRequirement) {
        this.lineNumber = lineNumber;
        this.type = type;
        this.specialRequirement = specialRequirement;
        this.stationsList = new ArrayList<>();
        this.usedTickets= new ArrayList<>();

    }

    public List<Ticket> getUsedTickets() {
        return usedTickets;
    }

    public void setUsedTickets(List<Ticket> usedTickets) {
        this.usedTickets = usedTickets;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSpecialRequirement() {
        return specialRequirement;
    }

    public void setSpecialRequirement(String specialRequirement) {
        this.specialRequirement = specialRequirement;
    }

    public void useTicketOn(Ticket ticket){
        this.usedTickets.add(ticket);
    }

    public void addStation(Station station){
        this.stationsList.add(station);
    }

    public void delStation(Station station){
        this.stationsList.remove(station);
    }
}
