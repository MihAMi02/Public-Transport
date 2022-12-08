package model.data;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DieselVehicle extends Vehicle
{


//    @Id
//    @Getter
//    @Setter
//    private String vin;

    private String type;
    private int euronorm;
    public DieselVehicle(String vin, String make, String model, int built, int capacity, String type, int euronorm, Depot depot) {
        super(vin, make, model, built, capacity, depot);
        this.type = type;
        this.euronorm = euronorm;
//        this.vin=vin;
    }

    public DieselVehicle() {
        super();
    }

    public DieselVehicle(String vin, String make, String model, int built, int cap, int electricefficiency, int euronorm, Depot depot) {
    }

    public DieselVehicle(String vin, String make, String model, int built, int cap, int electricefficiency, String type, int euronorm, Depot depot) {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getEuronorm() {
        return euronorm;
    }

    public void setEuronorm(int euronorm)
    {
        this.euronorm = euronorm;
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
                ", euronorm=" + euronorm +
                "}";
    }
}



