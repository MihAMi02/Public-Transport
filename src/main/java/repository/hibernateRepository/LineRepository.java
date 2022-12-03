package repository.hibernateRepository;

import model.comparators.LineNumberComparator;
import model.comparators.LineUsedTicketsComparator;
import model.data.Line;
import model.data.Station;
import model.data.Ticket;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class LineRepository implements repository.interfaces.LineRepository {
    List<Line> lineList;

    private void fetch(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        lineList = manager.createQuery("SELECT line FROM Line line").getResultList();
    }


    public LineRepository() {
        this.lineList = new ArrayList<>();
        fetch();
    }



    @Override
    public boolean add(Line entity) {
        boolean found = false;
        for(Line line : this.lineList){
            if(line.getLineNumber().equals(entity.getLineNumber())){
                found = true;
                break;
            }
        }
        if(!found){
            this.lineList.add(entity);
            return true;
        }
        return false;
    }

    @Override
    public Line remove(String s) {
        Line temp = this.find(s);
        if(temp != null){
            this.lineList.remove(temp);
        }
        return temp;
    }

    @Override
    public void update(Line newEntity, String s) {
        for(int i=0; i<this.lineList.size(); i++){
            if(this.lineList.get(i).getLineNumber().equals(s)){
                this.lineList.set(i, newEntity);
            }
        }
    }

    @Override
    public Line find(String s) {
        for(Line line : this.lineList){
            if(line.getLineNumber().equals(s)){
                return line;
            }
        }
        return null;
    }

    @Override
    public List<Line> filterByType(String type) {
        List<Line> lines = new ArrayList<>();
        for(Line line : lineList){
            if(line.getType().equals(type))
                lines.add(line);
        }
        return lines;
    }


    @Override
    public List<Line> sortByLineNumber(boolean ascending) {
        if (ascending) {
            this.lineList.sort(new LineNumberComparator());
        } else {
            this.lineList.sort(new LineNumberComparator().reversed());
        }
        return this.lineList;
    }

    @Override
    public List<Line> sortNumberUsedTickets(boolean ascending) {
        if(ascending) {
            this.lineList.sort(new LineUsedTicketsComparator());
        } else {
            this.lineList.sort(new LineUsedTicketsComparator().reversed());
        }
        return this.lineList;
    }

    @Override
    public void useTicketOn(Ticket ticket, String line) {
        this.find(line).useTicketOn(ticket);
    }

    @Override
    public void addStation(String lineNumber, Station station) {
        this.find(lineNumber).addStation(station);
    }

    @Override
    public void delStation(String lineNumber, Station station) {
        this.find(lineNumber).delStation(station);
    }
}
