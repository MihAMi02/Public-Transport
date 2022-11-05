package registrationSystem;

import model.Line;
import model.Station;
import model.Vehicle;

import java.util.List;


public class Dispatcher extends User implements GetLine,GetVehicles,GetStations,GetStationToLine,GetVehicleToLine,EditStationToLine,EditStations,EditVehicleToLine,UpdateStation
{



    @Override
    public List<Line> showLine() {

        return null;
    }

    @Override
    public Line getLine(String lineNumber) {
        return null;
    }

    @Override
    public List<Vehicle> showVehicles() {

        return null;
    }

    @Override
    public Vehicle getVehicle(String vin) {
        return null;
    }


    @Override
    public boolean addStationToLine(Station station, Line line) {
        return false;
    }

    @Override
    public boolean removeStationToLine(Station station, Line line) {
        return false;
    }

    @Override
    public boolean addStation(Station station) {
        return false;
    }

    @Override
    public boolean removeStation(Station station) {
        return false;
    }

    @Override
    public boolean addVehicleToLine(Vehicle vehicle, Line line) {
        return false;
    }

    @Override
    public List<Station> showStationToLines(Line line) {
        return null;
    }

    @Override
    public List<Station> getStations() {
        return null;
    }

    @Override
    public List<Station> getStationsWithName(String name) {
        return null;
    }

    @Override
    public List<Vehicle> getVehicleToLine(Line line) {
        return null;
    }

    @Override
    public boolean updateStation(Station newStation, Station oldStation) {
        return false;
    }
}
