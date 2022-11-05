package model.data;

public class DieselVehicle extends Vehicle
{
    private String type;
    private int euronorm;
    public DieselVehicle(String vin, String make, String model, int built, int capacity, String type, int euronorm) {
        super(vin, make, model, built, capacity);
        this.type = type;
        this.euronorm = euronorm;
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

    public void setEuronorm(int euronorm) {
        this.euronorm = euronorm;
    }
}



