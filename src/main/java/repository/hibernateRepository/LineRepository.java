package repository.hibernateRepository;

import model.comparators.LineNumberComparator;
import model.comparators.LineUsedTicketsComparator;
import model.data.Line;
import model.data.Station;
import model.data.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class LineRepository implements repository.interfaces.LineRepository
{
    List<Line> lineList;

    EntityManagerFactory factory ;
    EntityManager manager ;

    private void fetch(){
         factory = Persistence.createEntityManagerFactory("default");
         manager = factory.createEntityManager();
        lineList = manager.createQuery("SELECT line FROM Line line").getResultList();
    }


    public LineRepository() {
        this.lineList = new ArrayList<>();
        fetch();
    }



    @Override
    public boolean add(Line entity) {
        manager.getTransaction().begin();
        boolean found = false;
        for(Line line : this.lineList){
            if(line.getLineNumber().equals(entity.getLineNumber())){
                found = true;
                break;
            }
        }
        if(!found){
            this.lineList.add(entity);
            manager.persist(entity);
            manager.getTransaction().commit();
            return true;
        }
        manager.getTransaction().commit();
        return false;
    }

    @Override
    public Line remove(String s)
    {
        manager.getTransaction().begin();
        Line temp = this.find(s);
        if(temp != null)
        {
            this.lineList.remove(temp);
            manager.remove(temp);
        }
        manager.getTransaction().commit();
        return temp;
    }

    @Override
    public void update(Line newEntity, String s)
    {
       manager.getTransaction().begin();
       Line line =new Line();
       line.setLineNumber(newEntity.getLineNumber());
       manager.find(Line.class,line.getLineNumber());
       line.setType(newEntity.getType());
       line.setSpecialRequirement(newEntity.getSpecialRequirement());
       line.setStationsList(newEntity.getStationsList());
       line.setUsedTickets(newEntity.getUsedTickets());
       manager.getTransaction().commit();
       lineList = manager.createQuery("SELECT line FROM Line line").getResultList();

    }

    @Override
    public Line find(String s)
    {
        for(Line line : this.lineList){
            if(line.getLineNumber().equals(s))
            {
                return line;
            }
        }
        return null;
    }

    @Override
    public List<Line> filterByType(String type)
    {
        manager.getTransaction().begin();
        Query query = manager.createQuery("select line from Line line where line.type=:type_name");
        query.setParameter("type_name", type);
        List<Line> lines = query.getResultList();
        manager.getTransaction().commit();
        return lines;
    }


    @Override
    public List<Line> sortByLineNumber(boolean ascending)
    {
        manager.getTransaction().begin();
        Query query = manager.createQuery("select line from Line line order by line.lineNumber ASC");
        if(!ascending)
            query = manager.createQuery("select line from Line line order by line.lineNumber DESC");
        List<Line> lines = query.getResultList();
        manager.getTransaction().commit();
        return lines;
    }

    @Override
    public List<Line> sortNumberUsedTickets(boolean ascending) {
        manager.getTransaction().begin();
        Query query = manager.createQuery("select line from Line line order by line.usedTickets.size ASC");
        if(!ascending)
            query = manager.createQuery("select line from Line line order by line.usedTickets.size DESC");

        List<Line> lines = query.getResultList();
        manager.getTransaction().commit();
        return lines;
    }

    @Override
    public void useTicketOn(Ticket ticket, String line) {
        manager.getTransaction().begin();
        Line temp = this.find(line);
        manager.find(Line.class, temp.getLineNumber());
        manager.find(Ticket.class, ticket.getId());
        temp.useTicketOn(ticket);
        manager.getTransaction().commit();
    }

    @Override
    public void addStation(String lineNumber, Station station)
    {
        manager.getTransaction().begin();
        Line temp = this.find(lineNumber);
        manager.find(Line.class, temp.getLineNumber());
        manager.find(Station.class, station.getStationId());
        temp.addStation(station);
        station.addLine(temp);
        manager.getTransaction().commit();
    }

    @Override
    public void delStation(String lineNumber, Station station) {
        manager.getTransaction().begin();
        Line temp = this.find(lineNumber);
        manager.find(Line.class, temp.getLineNumber());
        manager.find(Station.class, station.getStationId());
        temp.delStation(station);
        station.delLine(temp);
        manager.getTransaction().commit();
    }
}

