package repository.hibernateRepository;

import model.comparators.DepotNameComparator;
import model.data.Depot;
import model.data.DieselVehicle;
import model.data.ElectricVehicle;
import model.data.Vehicle;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;


public class DepotRepository implements repository.interfaces.DepotRepository
{

    private List<Depot> depotList;

    EntityManagerFactory factory ;
    EntityManager manager ;


    private void fetch()
    {
        factory = Persistence.createEntityManagerFactory("default");
        manager = factory.createEntityManager();
        depotList = manager.createQuery("SELECT depot FROM Depot depot").getResultList();

    }


    public DepotRepository()
    {
        this.depotList = new ArrayList<>();
        fetch();
    }

    @Override
    public boolean add(Depot entity)
    {
        manager.getTransaction().begin();
        boolean found = false;
        for(Depot depot : this.depotList){
            if(depot.getName().equals(entity.getName())){
                found = true;
                break;
            }
        }
        if(!found) {
            this.depotList.add(entity);
            manager.persist(entity);
            return true;
        }
        manager.getTransaction().commit();
        return false;
    }

    @Override
    public Depot remove(String s)
    {
        manager.getTransaction().begin();
        Depot temp = this.find(s);
        if(temp != null)
        {
            this.depotList.remove(temp);
            manager.remove(temp);
        }
        manager.getTransaction().commit();
        return temp;
    }

    @Override
    public void update(Depot newEntity, String s)
    {
       manager.getTransaction().begin();
       Depot depot = new Depot();
       depot.setName(newEntity.getName());
       manager.find(Depot.class,newEntity.getName());
       depot.setEmployee(newEntity.getEmployee());
       depot.setAddress(newEntity.getAddress());
       depot.setVehicles(newEntity.getVehicles());
       manager.getTransaction().commit();
       depotList = manager.createQuery("SELECT depot FROM Depot depot").getResultList();
    }

    @Override
    public Depot find(String s)
    {
        for(Depot depot : this.depotList)
        {
            if(depot.getName().equals(s))
            {
                return depot;
            }
        }
        return null;
    }

    @Override
    public List<Depot> sortByName(boolean ascending)
    {
        if(ascending)
        {
            this.depotList.sort(new DepotNameComparator());
        }
        else
        {
            this.depotList.sort(new DepotNameComparator().reversed());
        }
        return this.depotList;
    }

    @Override
    public void moveVehicle(String fromDepot, String toDepot, String vin)
    {
        this.delVehicleToDepot(manager.find(Depot.class, fromDepot),manager.find(Vehicle.class, vin));
        this.addVehicleToDepot(manager.find(Depot.class, toDepot),manager.find(Vehicle.class, vin));

        manager.getTransaction().begin();
        Vehicle temp = manager.find(Vehicle.class, vin);
        temp.setDepot(this.find(toDepot));
        manager.getTransaction().commit();
    }

    @Override
    public void addVehicleToDepot(Depot depot, Vehicle vehicle){
        manager.getTransaction().begin();
        manager.find(Depot.class, depot.getName());
        manager.find(Vehicle.class, vehicle.getVin());
        depot.addVehicle(vehicle);
        manager.getTransaction().commit();
    }

    @Override
    public void delVehicleToDepot(Depot depot, Vehicle vehicle){
        manager.getTransaction().begin();
        manager.find(Depot.class, depot.getName());
        manager.find(Vehicle.class, vehicle.getVin());
        depot.removeVehicle(vehicle.getVin());
        manager.getTransaction().commit();
    }

}
