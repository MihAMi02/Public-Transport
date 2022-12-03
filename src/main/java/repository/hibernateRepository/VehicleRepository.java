package repository.hibernateRepository;

import model.comparators.VehicleParkNumberComparators;
import model.data.DieselVehicle;
import model.data.ElectricVehicle;
import model.data.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements repository.interfaces.VehicleRepository
{
    List<Vehicle> vehicleList;

    public VehicleRepository(){
        this.vehicleList = new ArrayList<>();
        fetch();
    }

    private void fetch(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        vehicleList = manager.createQuery("SELECT vehicle FROM Vehicle vehicle").getResultList();
    }


    @Override
    public boolean add(Vehicle entity) {
        boolean found = false;
        for(Vehicle vehicle : vehicleList){
            if(vehicle.getVin().equals(entity.getVin())){
                found = true;
                break;
            }
        }
        if(!found){
            this.vehicleList.add(entity);
            return true;
        }
        return false;
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
