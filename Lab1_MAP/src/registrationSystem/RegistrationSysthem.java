package registrationSystem;


import model.Depot;
import model.Vehicle;


import java.util.ArrayList;
import java.util.List;


public class RegistrationSysthem {

    List<Depot> deposList=new ArrayList<>();

    List<Vehicle> vehicleList=new ArrayList<>();



    public RegistrationSysthem() {

    }

    public void addDepo(Depot d)
    {

        deposList.add(d);
    }

    public void removeDepo(Depot d)
    {

        deposList.remove(d);
    }

    public void addVehicles(Vehicle v)
    {

        vehicleList.add(v);
    }

    public void removeVehicle(Vehicle v)
    {

        vehicleList.remove(v);
    }



    public void registertoVehicles(Depot d,Vehicle v)
    {

        if(!vehicleList.contains(v))
        {
            return;
        }

        if(!deposList.contains(d))
        {
            return;
        }


        d.addVehicle(v);
        v.addDepos(d);



    }


    public void updateWayVehicles(Vehicle v)
    {

        if(!vehicleList.contains(v))
        {
            return;
        }


       //v.setDepos((List<Depot>) v);

    }


    public void showVehicle()
    {
        for(int i=0;i<vehicleList.size();i++)
        {
            System.out.println(vehicleList.get(i));
        }

    }

    public void showDepos()
    {
        for(int i=0;i<deposList.size();i++)
        {
            System.out.println(deposList.get(i));
        }

    }

    public void showVehiclesWithDepos()
    {

        for(int i=0;i<vehicleList.size();i++)
        {
            System.out.println(vehicleList.get(i));
            System.out.println(vehicleList.get(i).getDepos());

        }


    }

    public void showDeposWithVehicles()
    {
        for(int i=0;i<deposList.size();i++)
        {
            System.out.println(deposList.get(i));
            System.out.println(deposList.get(i).getVehicles());

        }


    }

    public void removeVehiclefromDepo(Vehicle v,Depot d)
    {
        if(!vehicleList.contains(v))
        {
            return;
        }

        if(!deposList.contains(d))
        {
            return;
        }

        v.removeDepos(d);
        d.removeVehicle(v);


    }



}



