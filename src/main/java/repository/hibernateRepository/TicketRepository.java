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

    EntityManagerFactory factory ;

    EntityManager manager ;

    public TicketRepository(){
        this.tickets = new ArrayList<>();
        fetch();
    }

    private void fetch(){
        factory = Persistence.createEntityManagerFactory("default");
        manager = factory.createEntityManager();
        tickets = manager.createQuery("SELECT ticket FROM Ticket ticket").getResultList();
    }

    @Override
    public boolean add(Ticket entity) {
        manager.getTransaction().begin();
        boolean found = false;
        for(Ticket ticket: tickets){
            if(Objects.equals(ticket.getId(), entity.getId())){
                found = true;
                break;
            }
        }

        if(!found){
            this.tickets.add(entity);
            manager.persist(entity);
            manager.getTransaction().commit();
            return true;
        }
        manager.getTransaction().commit();
        return false;
    }

    @Override
    public Ticket remove(Integer integer) {
        manager.getTransaction().begin();
        Ticket temp = this.find(integer);
        if(temp != null){
            manager.remove(temp);
            this.tickets.remove(temp);
        }
        manager.getTransaction().commit();
        return temp;
    }

    @Override
    public void update(Ticket newEntity, Integer integer)
    {
       manager.getTransaction().begin();
       Ticket ticket=new Ticket();
       ticket.setId(newEntity.getId());
       manager.find(Ticket.class,ticket.getId());
       ticket.setType(newEntity.getType());
       ticket.setValue(newEntity.getValue());
       ticket.setUserid(newEntity.getUserid());
       ticket.setBuyDate(newEntity.getBuyDate());
       manager.getTransaction().commit();
       tickets = manager.createQuery("SELECT ticket FROM Ticket ticket").getResultList();
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
