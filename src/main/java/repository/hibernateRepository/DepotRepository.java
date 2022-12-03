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


public class DepotRepository implements repository.interfaces.DepotRepository {

    private List<Depot> depotList;


    private void fetch()
    {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("default");
        EntityManager manager = factory.createEntityManager();
        depotList = manager.createQuery("SELECT depot FROM Depot depot").getResultList();

    }


    public DepotRepository() {
        this.depotList = new ArrayList<>();
        fetch();
    }

    @Override
    public boolean add(Depot entity) {
        boolean found = false;
        for(Depot depot : this.depotList){
            if(depot.getName().equals(entity.getName())){
                found = true;
                break;
            }
        }
        if(!found) {
            this.depotList.add(entity);
            return true;
        }
        return false;
    }

    @Override
    public Depot remove(String s) {
        Depot temp = this.find(s);
        if(temp != null){
            this.depotList.remove(temp);
        }
        return temp;
    }

    @Override
    public void update(Depot newEntity, String s) {
        for(int i = 0; i < this.depotList.size(); i++){
            if(depotList.get(i).getName().equals(s)){
                this.depotList.set(i, newEntity);
                return;
            }
        }
    }

    @Override
    public Depot find(String s) {
        for(Depot depot : this.depotList){
            if(depot.getName().equals(s)){
                return depot;
            }
        }
        return null;
    }

    @Override
    public List<Depot> sortByName(boolean ascending) {
        if(ascending){
            this.depotList.sort(new DepotNameComparator());
        } else {
            this.depotList.sort(new DepotNameComparator().reversed());
        }
        return this.depotList;
    }

    @Override
    public void moveVehicle(String fromDepot, String toDepot, String vin) {
        Vehicle temp = this.find(fromDepot).removeVehicle(vin);
        this.find(toDepot).addVehicle(temp);
    }

}
