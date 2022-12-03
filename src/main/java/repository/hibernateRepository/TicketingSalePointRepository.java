package repository.hibernateRepository;

import model.data.TicketingSalePoint;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class TicketingSalePointRepository implements repository.interfaces.TicketingSalePointRepository {
    private List<TicketingSalePoint> ticketingSalePoints;

    public TicketingSalePointRepository(){
        this.ticketingSalePoints = new ArrayList<>();
        fetch();
    }

    private void fetch(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        ticketingSalePoints = manager.createQuery("SELECT ticketing FROM TicketingSalePoint ticketing").getResultList();
    }

    @Override
    public boolean add(TicketingSalePoint entity) {
        boolean found = false;
        for(TicketingSalePoint ticketingSalePoint : this.ticketingSalePoints){
            if(ticketingSalePoint.getId().equals(entity.getId())){
                found = true;
                break;
            }
        }
        if(!found){
            this.ticketingSalePoints.add(entity);
            return true;
        }
        return false;
    }

    @Override
    public TicketingSalePoint remove(String s) {
        TicketingSalePoint temp = this.find(s);
        if(temp != null){
            this.ticketingSalePoints.remove(temp);
        }
        return temp;
    }

    @Override
    public void update(TicketingSalePoint newEntity, String s) {
        for(int i=0; i<this.ticketingSalePoints.size(); i++){
            if(this.ticketingSalePoints.get(i).getId().equals(s)){
                this.ticketingSalePoints.set(i, newEntity);
            }
        }
    }

    @Override
    public TicketingSalePoint find(String s) {
        for(TicketingSalePoint ticketingSalePoint : this.ticketingSalePoints){
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
