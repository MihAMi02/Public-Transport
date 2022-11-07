package repository.interfaces;

import model.data.TicketingSalePoint;

import java.util.List;

public interface TicketingSalePointRepository extends CrudRepository<String, TicketingSalePoint>{

    List<TicketingSalePoint> filterByType(String type);




}
