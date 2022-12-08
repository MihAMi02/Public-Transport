package repository.hibernateRepository;

import model.data.TicketingSalePoint;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class TicketingSalePointRepository implements repository.interfaces.TicketingSalePointRepository {
    private List<TicketingSalePoint> ticketingSalePoints;

    EntityManagerFactory factory ;
    EntityManager manager ;

    public TicketingSalePointRepository(){
        this.ticketingSalePoints = new ArrayList<>();
        fetch();
    }

    private void fetch(){
        factory = Persistence.createEntityManagerFactory("default");
        manager=factory.createEntityManager();
        ticketingSalePoints = manager.createQuery("SELECT ticketing FROM TicketingSalePoint ticketing").getResultList();



    }

    @Override
    public boolean add(TicketingSalePoint entity) {
        manager.getTransaction().begin();
        boolean found = false;
        for(TicketingSalePoint ticketingSalePoint : this.ticketingSalePoints){
            if(ticketingSalePoint.getId().equals(entity.getId())){
                found = true;
                break;
            }
        }
        if(!found){
            this.ticketingSalePoints.add(entity);
            manager.persist(entity);
            manager.getTransaction().commit();
            return true;
        }
        manager.getTransaction().commit();
        return false;
    }

    @Override
    public TicketingSalePoint remove(String s) {
        manager.getTransaction().begin();
        TicketingSalePoint temp = this.find(s);
        if(temp != null){
            this.ticketingSalePoints.remove(temp);
            manager.remove(temp);
        }
        manager.getTransaction().commit();
        return temp;
    }

    @Override
    public void update(TicketingSalePoint newEntity, String s)
    {
        manager.getTransaction().begin();
        TicketingSalePoint ticketingSalePoint=new TicketingSalePoint();
        ticketingSalePoint.setId(newEntity.getId());
        manager.find(TicketingSalePointRepository.class,ticketingSalePoint.getId());
        ticketingSalePoint.setSoldTickets(newEntity.getSoldTickets());
        ticketingSalePoint.setType(newEntity.getType());
        ticketingSalePoints = manager.createQuery("SELECT ticketing FROM TicketingSalePoint ticketing").getResultList();

        manager.getTransaction().commit();
    }

    @Override
    public TicketingSalePoint find(String s)
    {
        for(TicketingSalePoint ticketingSalePoint : this.ticketingSalePoints)
        {
            if(ticketingSalePoint.getId().equals(s)){
                return ticketingSalePoint;
            }
        }
        return null;
    }

    @Override
    public List<TicketingSalePoint> filterByType(String type) {
        List<TicketingSalePoint> ticketingSalePoints = new ArrayList<>();
        for(TicketingSalePoint point: this.ticketingSalePoints){
            if(point.getType().equals(type)){
                ticketingSalePoints.add(point);
            }
        }
        return ticketingSalePoints;
    }
}
