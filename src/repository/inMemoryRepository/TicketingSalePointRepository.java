package repository.inMemoryRepository;

import model.data.TicketingSalePoint;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TicketingSalePointRepository implements repository.interfaces.TicketingSalePointRepository {

    private List<TicketingSalePoint> ticketingSalePoints;

    public TicketingSalePointRepository(){
        this.ticketingSalePoints = new ArrayList<>();
        populate();
    }

    private void populate(){
        TicketingSalePoint ticketingSalePoint1 = new TicketingSalePoint("DNA_GHICA", "Ticketing Centre");
        TicketingSalePoint ticketingSalePoint2 = new TicketingSalePoint("TVM_BUC_OBR", "Ticketing Machine");
        this.ticketingSalePoints.add(ticketingSalePoint1);
        this.ticketingSalePoints.add(ticketingSalePoint2);
    }

    @Override
    public void add(TicketingSalePoint entity) {
        boolean found = false;
        for(TicketingSalePoint ticketingSalePoint : this.ticketingSalePoints){
            if(ticketingSalePoint.getId().equals(entity.getId())){
                found = true;
                break;
            }
        }
        if(!found){
            this.ticketingSalePoints.add(entity);
        }
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
