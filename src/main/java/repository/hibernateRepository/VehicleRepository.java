package repository.hibernateRepository;

import model.comparators.VehicleParkNumberComparators;
import model.comparators.VehicleYearComparator;
import model.data.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepository implements repository.interfaces.VehicleRepository
{
    List<Vehicle> vehicleList;



    EntityManagerFactory factory ;
    EntityManager manager ;

    public VehicleRepository(){
        this.vehicleList = new ArrayList<>();
        fetch();
    }

    private void fetch(){
        factory = Persistence.createEntityManagerFactory("default");
        manager = factory.createEntityManager();
        vehicleList = manager.createQuery("SELECT vehicle FROM Vehicle vehicle").getResultList();
    }


    @Override
    public boolean add(Vehicle entity)
    {
        manager.getTransaction().begin();
        boolean found = false;
        for(Vehicle vehicle : vehicleList)
        {
            if(vehicle.getVin().equals(entity.getVin()))
            {
                found = true;
                break;
            }

        }
        if(!found){
            this.vehicleList.add(entity);
            manager.persist(entity);
            System.out.println(manager.getTransaction().toString());
            manager.getTransaction().commit();
            return true;
        }
        manager.getTransaction().commit();
        return false;
    }

    @Override
    public Vehicle remove(String s)
    {
        manager.getTransaction().begin();
        Vehicle temp = this.find(s);
        if(temp != null)
        {
            this.vehicleList.remove(temp);
            manager.remove(temp);
            Depot temp2 = manager.find(Depot.class, temp.getDepot().getName());
//            temp2.removeVehicle(temp.getVin());
//            manager.persist(temp2);
        }
        manager.getTransaction().commit();
        return temp;
    }

    @Override
    public void update(Vehicle newEntity, String s)
    {
        manager.getTransaction().begin();
        Vehicle vehicle = manager.find(Vehicle.class, s);
        vehicle.setBuilt(newEntity.getBuilt());
        vehicle.setCapacity(newEntity.getCapacity());
        vehicle.setDepot(newEntity.getDepot());
        vehicle.setMake(newEntity.getMake());
        vehicle.setModel(newEntity.getModel());
        vehicle.setDriverID(newEntity.getDriverID());
        vehicle.setInMaintenance(newEntity.isInMaintenance());
        vehicle.setParkNumber(newEntity.getParkNumber());
        manager.getTransaction().commit();
        vehicleList = manager.createQuery("SELECT vehicle FROM Vehicle vehicle").getResultList();
    }

    @Override
    public Vehicle find(String s)
    {
        for(Vehicle vehicle : this.vehicleList)
        {
            if(vehicle.getVin().equals(s))
            {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public List<Vehicle> findVehicleByParkNumber(String parkNumber)
    {
        List<Vehicle> vehicles = new ArrayList<>();
        for(Vehicle vehicle : this.vehicleList)
        {
            if(vehicle.getParkNumber().equals(parkNumber))
            {
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
    public List<Vehicle> sortByYear(boolean ascending)
    {
        if(ascending)
        {
            vehicleList.sort(new VehicleYearComparator());
        }
        else
        {
            vehicleList.sort(new VehicleYearComparator().reversed());
        }
        return vehicleList;
    }

    @Override
    public List<Vehicle> filterByinMainetenanceStatus(boolean inMaintenance)
    {
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

    @Override
    public void refresh()
    {
        vehicleList = manager.createQuery("SELECT vehicle FROM Vehicle vehicle").getResultList();
    }
}
