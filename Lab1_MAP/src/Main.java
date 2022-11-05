import model.*;


public class Main {
    public static void main(String[] args)
    {


        DieselVehicle d1=new DieselVehicle("VW","Passat",2020);
        System.out.println(d1);

        ElectricVehicle e1=new ElectricVehicle("VW","ID3",2022);
        System.out.println(e1);


        Line l1=new Line("A10","Busline","SpecialRoute");
        System.out.println(l1);


        Station s1=new Station(10,"Floreasca","Numarul 10");
        System.out.println(s1);



    }
}





