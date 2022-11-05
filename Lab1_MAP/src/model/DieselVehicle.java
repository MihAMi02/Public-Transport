package model;

public class DieselVehicle extends Vehicle
{


    public DieselVehicle(String model, String type, int year)
    {

        super(model, type, year);
    }

    @Override
    public String toString() {
        return "DieselVehicle{" +
                "depos=" + depos +
                '}';
    }


}



