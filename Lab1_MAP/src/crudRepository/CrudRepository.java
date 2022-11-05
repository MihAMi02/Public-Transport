package crudRepository;

import model.*;
import registrationSystem.User;

import java.util.List;

public abstract  class CrudRepository {

    List<Depot> depots;

    List<Vehicle> vehicles;

    List<Employee> employees;

    List<Line> lines;

    List<Program> programs;

    List<Station> stations;

    List<User> users;

    List<TicketingSalePoint> ticketingSalePoints;

    public List<Depot> getDepots() {
        return depots;
    }

    public void setDepots(List<Depot> depots) {
        this.depots = depots;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    public List<Station> getStations() {
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<TicketingSalePoint> getTicketingSalePoints() {
        return ticketingSalePoints;
    }

    public void setTicketingSalePoints(List<TicketingSalePoint> ticketingSalePoints) {
        this.ticketingSalePoints = ticketingSalePoints;
    }

}
