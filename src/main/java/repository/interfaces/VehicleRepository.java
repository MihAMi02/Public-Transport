package repository.interfaces;

import model.data.Vehicle;

import java.util.List;

public interface VehicleRepository extends CrudRepository<String, Vehicle>
{
    List<Vehicle> findVehicleByParkNumber(String parkNumber);

    List<Vehicle> sortByParkNumber(boolean ascending);

    List<Vehicle> sortByYear(boolean ascending);

    List<Vehicle> filterByinMainetenanceStatus(boolean inMaintenance);

    void refresh();

}


