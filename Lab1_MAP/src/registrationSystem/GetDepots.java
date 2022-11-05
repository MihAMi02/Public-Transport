package registrationSystem;

import model.Depot;
import model.Vehicle;

import java.util.List;

public interface GetDepots {

    List<Depot> showDepots();


    Depot getDepot(String name);



    List<Vehicle> showVehicles();

}
