package repository.interfaces;

import model.data.Station;

import java.util.List;

public interface StationRepository extends CrudRepository<Integer, Station> {
    List<Station> sortByName(boolean ascending);


}
