package repository.interfaces;

import model.data.Depot;

import java.util.List;

public interface DepotRepository extends CrudRepository<String, Depot> {

    List<Depot> sortByName(boolean ascending);

    void moveVehicle(String fromDepot, String toDepot, String vin);
}
