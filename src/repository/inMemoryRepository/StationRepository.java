package repository.inMemoryRepository;

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
        this.stationList.add(station1);
        this.stationList.add(station2);
    }

    @Override
    public void add(Station entity) {
        boolean found = false;
        for(Station station : this.stationList){
            if(station.getStationId() == entity.getStationId()){
                found = true;
                break;
            }
        }
        if(!found){
            this.stationList.add(entity);
        }
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
}
