package model.data;


import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DieselVehicle extends Vehicle
{




    private String type;
    private int euronorm;
    public DieselVehicle(String vin, String make, String model, int built, int capacity, String type, int euronorm) {
        super(vin, make, model, built, capacity);
        this.type = type;
        this.euronorm = euronorm;
    }

    public DieselVehicle() {
        super();
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



