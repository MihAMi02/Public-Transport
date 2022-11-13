package repository.interfaces;

import model.data.Line;
import model.data.Station;
import model.data.Ticket;

import java.util.List;

public interface LineRepository extends CrudRepository<String, Line> {
    List<Line> filterByType(String type);

    List<Line> sortByLineNumber(boolean ascending);

    List<Line> sortNumberUsedTickets(boolean ascending);

    void useTicketOn(Ticket ticket, String line);

    void addStation(String lineNumber, Station station);
    void delStation(String lineNumber, Station station);
}
