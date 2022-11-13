package repository.interfaces;

import model.data.Line;
import model.data.Station;

import java.util.List;

public interface StationRepository extends CrudRepository<Integer, Station> {
    List<Station> sortByName(boolean ascending);

    List<Station> filterByName(String name);

    Integer getNextStationID();

    void addStation(Integer stationID, String line);
    void delStation(Integer stationID, String line);
}
