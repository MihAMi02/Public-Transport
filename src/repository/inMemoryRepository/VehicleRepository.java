package repository.inMemoryRepository;

import model.comparators.VehicleParkNumberComparators;
import model.data.DieselVehicle;
import model.data.ElectricVehicle;
import model.data.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements repository.interfaces.VehicleRepository {

    List<Vehicle> vehicleList;

    public VehicleRepository(){
        this.vehicleList = new ArrayList<>();
        populate();
    }

    private void populate(){
        Vehicle bus = new DieselVehicle("WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4);
        Vehicle tram = new ElectricVehicle("3344", "CKD Tatra", "T4R", 1977, 100, "Tram", 65);
        this.vehicleList.add(bus);
        this.vehicleList.add(tram);
    }

    @Override
    public void add(Vehicle entity) {
        boolean found = false;
        for(Vehicle vehicle : vehicleList){
            if(vehicle.getVin().equals(entity.getVin())){
                found = true;
                break;
            }
        }
        if(!found){
            this.vehicleList.add(entity);
        }
    }

    @Override
    public Vehicle remove(String s) {
        Vehicle temp = this.find(s);
        if(temp != null){
            this.vehicleList.remove(temp);
        }
        return temp;
    }

    @Override
    public void update(Vehicle newEntity, String s) {
        for(int i=0; i<this.vehicleList.size(); i++){
            if(this.vehicleList.get(i).getVin().equals(s)){
                this.vehicleList.set(i, newEntity);
            }
        }
    }

    @Override
    public Vehicle find(String s) {
        for(Vehicle vehicle : this.vehicleList){
            if(vehicle.getVin().equals(s)){
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public List<Vehicle> findVehicleByParkNumber(String parkNumber) {
        List<Vehicle> vehicles = new ArrayList<>();
        for(Vehicle vehicle : this.vehicleList){
            if(vehicle.getParkNumber().equals(parkNumber)){
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> sortByParkNumber(boolean ascending)
    {
        if(ascending){
            vehicleList.sort(new VehicleParkNumberComparators());
        }
        else{
            vehicleList.sort(new VehicleParkNumberComparators().reversed());
        }
        return vehicleList;
    }

    @Override
    public List<Vehicle> sortByYear(boolean ascending) {
        if(ascending)
        {
            vehicleList.sort(new VehicleParkNumberComparators());
        }
        else
        {
            vehicleList.sort(new VehicleParkNumberComparators().reversed());
        }
        return vehicleList;
    }

    @Override
    public List<Vehicle> filterByinMainetenanceStatus(boolean inMaintenance) {
        List<Vehicle> resultList=new ArrayList<>();
        for(Vehicle v:this.vehicleList)
        {
            if(v.isInMaintenance()==inMaintenance)
            {
                resultList.add(v);
            }
        }
        return resultList;
    }
}
