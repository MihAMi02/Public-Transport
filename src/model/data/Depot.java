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


    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(Vehicle v)
    {

        vehicles.add(v);
    }

    public void removeVehicle(Vehicle v)
    {

        vehicles.remove(v);
    }



    public void showVehicle()
    {

        for(int i=0;i<vehicles.size();i++)
        {
            System.out.println(vehicles.get(i));
        }

    }

    @Override
    public String toString() {
        return "Depos{" +
                "name='" + name + '\'' +
                '}';
    }

}


