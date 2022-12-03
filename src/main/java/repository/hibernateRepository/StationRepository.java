package repository.hibernateRepository;

import model.comparators.StationNameComparator;
import model.data.Line;
import model.data.Station;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class StationRepository implements repository.interfaces.StationRepository {
    private List<Station> stationList;

    public StationRepository(){
        this.stationList = new ArrayList<>();
        fetch();
    }

    private void fetch(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        stationList = manager.createQuery("SELECT station FROM Station station").getResultList();
    }


    @Override
    public boolean add(Station entity) {
        boolean found = false;
        for(Station station : this.stationList){
            if(station.getStationId() == entity.getStationId()){
                found = true;
                break;
            }
        }
        if(!found){
            this.stationList.add(entity);
            return true;
        }
        return false;
    }

    @Override
    public Station remove(Integer integer) {
        Station temp = this.find(integer);
        if(temp != null){
            this.stationList.remove(temp);
        }
        return temp;
    }

    @Override
    public void update(Station newEntity, Integer integer) {
        for(int i=0; i<this.stationList.size(); i++){
            if(this.stationList.get(i).getStationId() == integer){
                this.stationList.set(i, newEntity);
            }
        }
    }

    @Override
    public Station find(Integer integer) {
        for(Station station: this.stationList){
            if(station.getStationId() == integer){
                return station;
            }
        }
        return null;
    }

    @Override
    public List<Station> sortByName(boolean ascending) {
        if(ascending)
        {
            stationList.sort(new StationNameComparator());
        }
        else
        {
            stationList.sort(new StationNameComparator().reversed());
        }
        return stationList;
    }

    @Override
    public List<Station> filterByName(String name) {
        List<Station> result = new ArrayList<>();
        for(Station station: stationList){
            if(station.getName().equals(name)){
                result.add(station);
            }
        }
        return result;
    }

    @Override
    public Integer getNextStationID() {
        int nextID = 0;
        for(Station station: stationList){
            nextID = station.getStationId();
        }
        return nextID + 1;
    }

    @Override
    public void addStation(Integer stationID, Line line) {
        this.find(stationID).addLine(line);
    }

    @Override
    public void delStation(Integer stationID, Line line) {
        this.find(stationID).delLine(line);
    }
}
