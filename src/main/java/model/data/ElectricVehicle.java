package model.data;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ElectricVehicle extends Vehicle
{



    private String type;
    private int electricEfficiency;

    public ElectricVehicle(String vin, String make, String model, int built, int capacity, String type, int electricEfficiency, Depot depot) {
        super(vin, make, model, built, capacity, depot);
        this.type = type;
        this.electricEfficiency = electricEfficiency;
    }

    public ElectricVehicle() {

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getElectricEfficiency() {
        return electricEfficiency;
    }

    public void setElectricEfficiency(int electricEfficiency) {
        this.electricEfficiency = electricEfficiency;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "vin='" + super.getVin() + '\'' +
                ", parkNumber='" + super.getParkNumber() + '\'' +
                ", make='" + super.getMake() + '\'' +
                ", model='" + super.getModel() + '\'' +
                ", built=" + super.getBuilt() +
                ", capacity=" + super.getCapacity() +
                ", driverID=" + super.getDriverID() +
                ", inMaintenance=" + super.isInMaintenance() +
                ", type=" + type +
                ", electricEff=" + electricEfficiency +
                "}";
    }
}



