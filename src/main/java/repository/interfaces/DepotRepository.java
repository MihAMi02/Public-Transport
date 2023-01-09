package repository.interfaces;

import model.data.Depot;
import model.data.Vehicle;

import java.util.List;

public interface DepotRepository extends CrudRepository<String, Depot> {

    List<Depot> sortByName(boolean ascending);

    void moveVehicle(String fromDepot, String toDepot, String vin);

    void addVehicleToDepot(Depot depot, Vehicle vehicle);

    void delVehicleToDepot(Depot depot, Vehicle vehicle);

    void refresh();
}
