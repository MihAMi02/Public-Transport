package model.data;

import java.util.ArrayList;
import java.util.List;


public abstract class Vehicle {
    private String vin;
    private String parkNumber;
    private String make;
    private String model;
    private int built;
    private int capacity;
    private int driverID;
    private boolean inMaintenance;

    public Vehicle(String vin, String make, String model, int built, int capacity) {
        this.vin = vin;
        this.parkNumber = null;
        this.make = make;
        this.model = model;
        this.built = built;
        this.capacity = capacity;
        this.driverID = 0;
        this.inMaintenance = false;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getParkNumber() {
        return parkNumber;
    }

    public void setParkNumber(String parkNumber) {
        this.parkNumber = parkNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getBuilt() {
        return built;
    }

    public void setBuilt(int built) {
        this.built = built;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getDriverID() {
        return driverID;
    }

    public void setDriverID(int driverID) {
        this.driverID = driverID;
    }

    public boolean isInMaintenance() {
        return inMaintenance;
    }

    public void setInMaintenance(boolean inMaintenance) {
        this.inMaintenance = inMaintenance;
    }
}
