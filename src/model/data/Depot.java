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
     * removes a given Vehicle from a Depot
     * @param v Vehicle to be removed from a depot
     */
    public void removeVehicle(Vehicle v)
    {
        vehicles.remove(v);
    }

}


