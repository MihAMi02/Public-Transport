package registrationSystem;

import model.Line;
import model.Station;

public interface EditStationToLine {
    boolean addStationToLine(Station station, Line line);

    boolean removeStationToLine(Station station, Line line);


}
