package model.data;

public class ElectricVehicle extends Vehicle
{
    private String type;
    private int electricEfficiency;

    public ElectricVehicle(String vin, String make, String model, int built, int capacity, String type, int electricEfficiency) {
        super(vin, make, model, built, capacity);
        this.type = type;
        this.electricEfficiency = electricEfficiency;
    }
}



