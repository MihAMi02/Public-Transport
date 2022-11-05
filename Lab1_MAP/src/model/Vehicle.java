package model;

import java.util.ArrayList;
import java.util.List;


public abstract class Vehicle {
    private String model;

    private String type;

    private int year;



    List<Depot> depos=new ArrayList<Depot>();


    public List<Depot> getDepos() {
        return depos;
    }

    public void setDepos(List<Depot> depos) {
        this.depos = depos;
    }




    public Vehicle(String model, String type, int year) {
        this.model = model;
        this.type = type;
        this.year = year;


    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void addDepos(Depot depo)
    {

        depos.add(depo);
    }

    public void removeDepos(Depot depo)
    {

        depos.remove(depo);
    }






    public void showDepos()
    {

        for(int i=0;i<depos.size();i++)
        {
            System.out.println(depos.get(i));
        }


    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", year=" + year +
                ", w="  +
                '}';
    }


}


