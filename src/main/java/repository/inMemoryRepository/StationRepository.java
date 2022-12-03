package repository.inMemoryRepository;

import model.comparators.StationNameComparator;
import model.data.Line;
import model.data.Station;

import java.util.ArrayList;
import java.util.List;

public class StationRepository implements repository.interfaces.StationRepository {

    private List<Station> stationList;

    public StationRepository(){
        this.stationList = new ArrayList<>();
        populate();
    }

    private void populate(){
        Station station1 = new Station(1, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        Station station2 = new Station(2, "Scoala Gimnaziala nr. 127", "Str. Muntii Carpati, Nr. 30");
        Station station3 = new Station(3, "Bucur Obor", "Sos. Colentina, Nr. 2");
        Line line2 = new Line("327", "Bus", "");
        List<Station> stationList1 = new ArrayList<>();
        stationList1.add(station1);
        stationList1.add(station2);
        station1.addLine(line2);
        station2.addLine(line2);
        this.stationList.add(station1);
        this.stationList.add(station2);
        this.stationList.add(station3);
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
