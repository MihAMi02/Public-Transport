package registrationSystem;

import model.Station;

import java.util.List;

public interface GetStations {

    List<Station> getStations();

    List<Station> getStationsWithName(String name);



}
