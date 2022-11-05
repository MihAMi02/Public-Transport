package model;

import model.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Depot {

    private String name;

    List<Vehicle> vehicles=new ArrayList<>();

    public Depot(String name) {
        this.name=name;
    }


    public List<model.Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<model.Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void addVehicle(model.Vehicle v)
    {

        vehicles.add(v);
    }

    public void removeVehicle(model.Vehicle v)
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


