package repository.hibernateRepository;

import model.data.TicketType;
import model.data.TicketingSalePoint;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class TicketTypeRepository implements repository.interfaces.TicketTypeRepository
{
    private List<TicketType> ticketTypes;

    public TicketTypeRepository(){
        this.ticketTypes = new ArrayList<>();
        fetch();
    }

    private void fetch()
    {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        ticketTypes = manager.createQuery("SELECT ticket FROM TicketType ticket").getResultList();
        manager.getTransaction().commit();
    }

    @Override
    public List<TicketType> getAllTypes()
    {
        return this.ticketTypes;
    }

    @Override
    public boolean add(TicketType entity)
    {
        boolean found = false;
        for(TicketType type : ticketTypes)
        {
            if(type.getType().equals(entity.getType()))
            {
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
