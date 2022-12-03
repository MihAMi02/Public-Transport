package model.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity

public class TicketingSalePoint {

    @Id

    private String id;
    private String type;
    @OneToMany
    private List<Ticket> soldTickets;

    public TicketingSalePoint(String id, String type) {
        this.id = id;
        this.type = type;
        this.soldTickets = new ArrayList<>();
    }

    public TicketingSalePoint() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Ticket> getSoldTickets() {
        return soldTickets;
    }

    public void setSoldTickets(List<Ticket> soldTickets) {
        this.soldTickets = soldTickets;
    }

    @Override
    public String toString() {
        return "TicketingSalePoint{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", soldTickets=" + soldTickets +
                '}';
    }
}
