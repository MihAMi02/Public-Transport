package repository.interfaces;

import model.data.Depot;

public interface DepotRepository extends CrudRepository<String, Depot> {

    List<Depot> sortByName(boolean ascending);


}
