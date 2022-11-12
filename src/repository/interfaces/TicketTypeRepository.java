package repository.interfaces;

import model.data.TicketType;

import java.util.List;

public interface TicketTypeRepository extends CrudRepository<String, TicketType>{

    List<TicketType> getAllTypes();
}
