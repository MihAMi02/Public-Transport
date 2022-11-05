package registrationSystem;

import model.Depot;
import model.Vehicle;

import java.util.List;

public class MaintenanceEmployee extends User implements GetVehicles,GetDepots,UpdateVehicle
{


    @Override
    public List<Depot> showDepots() {
        return null;
    }

    @Override
    public Depot getDepot(String name) {
        return null;
    }

    @Override
    public List<Vehicle> showVehicles() {
        return null;
    }

    @Override
    public Vehicle getVehicle(String vin) {
        return null;
    }


    @Override
    public boolean updateVehicle(Vehicle newVehicle, Vehicle oldVehicle) {
        return false;
    }
}


