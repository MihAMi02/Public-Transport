package repository.inMemoryRepository;

import model.data.TicketType;
import model.data.TicketingSalePoint;

import java.util.ArrayList;
import java.util.List;

public class TicketTypeRepository implements repository.interfaces.TicketTypeRepository {
    private List<TicketType> ticketTypes;

    public TicketTypeRepository(){
        this.ticketTypes = new ArrayList<>();
        populate();
    }

    private void populate(){
        ticketTypes.add(new TicketType("Bilet", 3.0f));
        ticketTypes.add(new TicketType("Abonament 24h", 8.0f));
        ticketTypes.add(new TicketType("Abonament 72h", 20.0f));
        ticketTypes.add(new TicketType("Abonament 1 sapt", 30.0f));
        ticketTypes.add(new TicketType("Abonament 1 luna", 80.0f));
        ticketTypes.add(new TicketType("Abonament 6 luni", 400.0f));
        ticketTypes.add(new TicketType("Abonament 12 luni", 700.0f));
    }



    @Override
    public List<TicketType> getAllTypes() {
        return this.ticketTypes;
    }

    @Override
    public boolean add(TicketType entity) {
        boolean found = false;
        for(TicketType type : ticketTypes){
            if(type.getType().equals(entity.getType())){
                found = true;
                break;
            }
        }
        if(!found){
            this.ticketTypes.add(entity);
            return true;
        }
        return false;
    }

    @Override
    public TicketType remove(String s) {
        TicketType temp = this.find(s);
        if(temp != null){
            ticketTypes.remove(temp);
        }
        return temp;
    }

    @Override
    public void update(TicketType newEntity, String s) {
        for(int i = 0; i<this.ticketTypes.size(); i++){
            if(this.ticketTypes.get(i).getType().equals(s)){
                this.ticketTypes.set(i, newEntity);
            }
        }
    }

    @Override
    public TicketType find(String s) {
        for(TicketType type : ticketTypes){
            if(type.getType().equals(s)){
                return type;
            }
        }
        return null;
    }
}
