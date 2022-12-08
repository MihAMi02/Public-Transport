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

    EntityManagerFactory factory ;

    EntityManager manager ;

    public StationRepository(){
        this.stationList = new ArrayList<>();
        fetch();
    }

    private void fetch(){
        factory = Persistence.createEntityManagerFactory("default");
        manager = factory.createEntityManager();
        stationList = manager.createQuery("SELECT station FROM Station station").getResultList();
    }


    @Override
    public boolean add(Station entity) {
        manager.getTransaction().begin();
        boolean found = false;
        for(Station station : this.stationList){
            if(station.getStationId() == entity.getStationId()){
                found = true;
                break;
            }
        }
        if(!found){
            this.stationList.add(entity);
            manager.persist(entity);
            manager.getTransaction().commit();
            return true;
        }
        manager.getTransaction().commit();
        return false;
    }

    @Override
    public Station remove(Integer integer) {
        manager.getTransaction().begin();
        Station temp = this.find(integer);
        if(temp != null){
            this.stationList.remove(temp);
            manager.remove(temp);
        }
        manager.getTransaction().commit();
        return temp;
    }

    @Override
    public void update(Station newEntity, Integer integer)
    {
        manager.getTransaction().begin();
        Station station=new Station();
        station.setStationId(newEntity.getStationId());
        manager.find(Station.class,station.getStationId());
        station.setAddress(newEntity.getAddress());
        station.setName(newEntity.getName());
//        station.setLinesCalling(newEntity.getLinesCalling());
        manager.getTransaction().commit();
        stationList = manager.createQuery("SELECT station FROM Station station").getResultList();
    }

    @Override
    public Station find(Integer integer)
    {
        for(Station station: this.stationList){
            if(station.getStationId() == integer){
                return station;
            }
        }
        return null;
    }

    @Override
    public List<Station> sortByName(boolean ascending)
    {

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

//    @Override
//    public void addStation(Integer stationID, Line line) {
//        this.manager.getTransaction().begin();
//        Station temp = this.find(stationID);
//        manager.find(Station.class, temp.getStationId());
//        temp.addLine(line);
//        this.manager.getTransaction().commit();
//    }
//
//    @Override
//    public void delStation(Integer stationID, Line line) {
//        this.find(stationID).delLine(line);
//    }
}


