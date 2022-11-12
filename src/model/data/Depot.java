package model.data;

import java.util.ArrayList;
import java.util.List;

public class Depot {

    private String name;

    List<Vehicle> vehicles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Depot(String name) {
        this.name=name;
        this.vehicles = new ArrayList<>();
    }

    /**
     * Gets all Vehicles from the depot
     * @return list of Vehicles
     */
    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    /**
     * sets the given List as the depot's vehicles
     * @param vehicles list of vehicles to be set
     */
    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * adds a given vehicle to a depot
     * @param v vehicle to be added to a depot
     */
    public void addVehicle(Vehicle v)
    {
        vehicles.add(v);
    }

    /**
     * removes vehicle with given string from depot
     * @param vin vin of vehicle
     * @return removed vehicle
     */
    public Vehicle removeVehicle(String vin)
    {
        Vehicle temp = this.findVehicle(vin);
        if(temp != null){
            this.vehicles.remove(temp);
        }
        return temp;
    }

    private Vehicle findVehicle(String vin){
        for(Vehicle vehicle : vehicles){
            if(vehicle.getVin().equals(vin)){
                return vehicle;
            }
        }
        return null;
    }

}


