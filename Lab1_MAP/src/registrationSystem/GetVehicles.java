package registrationSystem;

import model.Vehicle;

import java.util.List;

public interface GetVehicles {


    List<Vehicle> showVehicles();

    Vehicle getVehicle(String vin);




}
