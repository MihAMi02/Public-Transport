package registrationSystem;

import model.*;

import java.util.List;


public class Director extends User implements GetLine,GetDepots,GetEmployees,GetVehicles,EditLines,EditDepots,EditEmployees,EditVehicles,UpdateVehicle,UpdateDepot {


    public Director(String username, String password) {
        super(username, password);
    }

    @Override
    public boolean addDepot(Depot depot) {
        return false;
    }

    @Override
    public boolean removeDepot(Depot depot) {
        return false;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return false;
    }

    @Override
    public boolean removeEmployee(Employee employee) {
        return false;
    }

    @Override
    public boolean addLine(Line line) {
        return false;
    }

    @Override
    public boolean removeLine(Line line) {
        return false;
    }

    @Override
    public boolean addVehicle(Vehicle vehicle) {
        return false;
    }

    @Override
    public boolean removeVehicle(Vehicle vehicle) {
        return false;
    }

    @Override
    public List<Depot> showDepots() {
        return null;
    }

    @Override
    public Depot getDepot(String name) {
        return null;
    }

    @Override
    public List<Vehicle> showVehicles() {
        return null;
    }

    @Override
    public Vehicle getVehicle(String vin) {
        return null;
    }

    @Override
    public List<Employee> showEmployee() {
        return null;
    }

    @Override
    public List<Line> showLine() {
        return null;
    }

    @Override
    public Line getLine(String lineNumber) {
        return null;
    }

    @Override
    public boolean updateVehicle(Vehicle newVehicle, Vehicle oldVehicle) {
        return false;
    }

    @Override
    public boolean updateDepot(Depot newDepot, Depot oldDepot) {
        return false;
    }
}



