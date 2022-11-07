package repository.interfaces;

import model.data.Line;

import java.util.List;

public interface LineRepository extends CrudRepository<String, Line> {
    List<Line> filterByType(String type);

    List<Line> sortByLineNumber(boolean ascending);

    List<Line> sortNumberUsedTickets(boolean ascending);

}
