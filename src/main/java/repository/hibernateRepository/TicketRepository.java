package repository.hibernateRepository;

import model.data.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TicketRepository implements repository.interfaces.TicketRepository
{
    private List<Ticket> tickets;

    public TicketRepository(){
        this.tickets = new ArrayList<>();
        fetch();
    }

    private void fetch(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        tickets = manager.createQuery("SELECT ticket FROM Ticket ticket").getResultList();
    }

    @Override
    public boolean add(Ticket entity) {
        boolean found = false;
        for(Ticket ticket: tickets){
            if(Objects.equals(ticket.getId(), entity.getId())){
                found = true;
                break;
            }
        }
        if(!found){
            this.tickets.add(entity);
            return true;
        }
        return false;
    }

    @Override
    public Ticket remove(Integer integer) {
        Ticket temp = this.find(integer);
        if(temp != null){
            this.tickets.remove(temp);
        }
        return temp;
    }

    @Override
    public void update(Ticket newEntity, Integer integer) {
        for(int i = 0; i < this.tickets.size(); i++){
            if(this.tickets.get(i).getId().equals(integer)){
                this.tickets.set(i, newEntity);
            }
        }
    }

    @Override
    public Ticket find(Integer integer) {
        for(Ticket ticket: tickets){
            if(ticket.getId().equals(integer))
                return ticket;
        }
        return null;
    }

    @Override
    public Integer getNextId() {
        int nextId = 0;
        for(Ticket ticket: tickets){
            nextId = ticket.getId();
        }
        return nextId + 1;
    }
}
