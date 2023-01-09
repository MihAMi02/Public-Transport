package model.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="vehicle")
public abstract class Vehicle
{

    @Id
    @Column(name = "vin")
    private String vin;
    private String parkNumber;
    private String make;
    private String model;
    private int built;
    private int capacity;


    @ManyToMany(mappedBy = "vehicles")
    private List<Depot> depot;


    @Getter
    @Setter
    @OneToOne
//    @JoinColumn(referencedColumnName = "EmployeeID")
    private Employee driverID;



    private boolean inMaintenance;

    public Vehicle(String vin, String make, String model, int built, int capacity, Depot depot)
    {
        this.vin = vin;
        this.parkNumber = null;
        this.make = make;
        this.model = model;
        this.built = built;
        this.capacity = capacity;
        this.driverID = null;
        this.inMaintenance = false;
        this.depot = new ArrayList<>();
        this.depot.add(depot);
    }

    public Vehicle() {

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

    public boolean isInMaintenance() {
        return inMaintenance;
    }

    public void setInMaintenance(boolean inMaintenance) {
        this.inMaintenance = inMaintenance;
    }

    public void addDepot(Depot depot){
        this.depot.add(depot);
    }

    public void removeDepot(Depot depot){
        this.depot.remove(depot);
    }

    public void setDepot(Depot depot){
        this.depot.clear();
        this.depot.add(depot);
    }

    public Depot getDepot(){
        return this.depot.get(0);
    }
}
