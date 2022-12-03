package repository.interfaces;

import model.data.Ticket;

public interface TicketRepository extends CrudRepository<Integer, Ticket> {
    public Integer getNextId();
}
