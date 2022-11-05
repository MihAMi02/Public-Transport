package repository.inMemoryRepository;

import model.data.Depot;
import model.data.DieselVehicle;
import model.data.ElectricVehicle;
import model.data.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class DepotRepository implements repository.interfaces.DepotRepository {

    private List<Depot> depotList;

    public DepotRepository() {
        this.depotList = new ArrayList<>();
        populate();
    }

    private void populate(){
        Depot depot1 = new Depot("Floreasca");
        Depot depot2 = new Depot("Militari");

        Vehicle bus = new DieselVehicle("WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4);
        Vehicle tram = new ElectricVehicle("3344", "CKD Tatra", "T4R", 1977, 100, "Tram", 65);

        depot1.addVehicle(bus);
        depot2.addVehicle(tram);

        this.depotList.add(depot1);
        this.depotList.add(depot2);
    }


    @Override
    public void add(Depot entity) {
        boolean found = false;
        for(Depot depot : this.depotList){
            if(depot.getName().equals(entity.getName())){
                found = true;
                break;
            }
        }
        if(!found) {
            this.depotList.add(entity);
        }
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
}
