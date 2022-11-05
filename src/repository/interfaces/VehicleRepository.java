package repository.interfaces;

import model.data.Vehicle;

import java.util.List;

public interface VehicleRepository extends CrudRepository<String, Vehicle> {
    List<Vehicle> findVehicleByParkNumber(String parkNumber);
}
