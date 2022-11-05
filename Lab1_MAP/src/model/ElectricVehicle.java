package model;

public class ElectricVehicle extends Vehicle
{


    public ElectricVehicle(String model, String type, int year)
    {

        super(model, type, year);
    }


    @Override
    public String toString() {
        return "ElectricVehicle{" +
                "depos=" + depos +
                '}';
    }
}



