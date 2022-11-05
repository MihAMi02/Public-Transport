package controller;

import model.data.*;
import repository.interfaces.*;

public class RegistrationSystem
{


    DepotRepository depotRepository;
    EmployeeRepository employeeRepository;
    LineRepository lineRepository;
    ProgramRepository programRepository;
    StationRepository stationRepository;
    TicketingSalePointRepository ticketingSalePointRepository;
    UserRepository userRepository;
    VehicleRepository vehicleRepository;

    private static RegistrationSystem single_instance = null;

    private RegistrationSystem(){

    }

    public static RegistrationSystem getInstance(){
        if(single_instance == null)
            single_instance = new RegistrationSystem();
        return  single_instance;
    }

    //for depots
    public void addDepot(Depot depot){
        this.depotRepository.add(depot);
    }

    public Depot removeDepot(String s){
        return this.depotRepository.remove(s);
    }

    /**
     *
     * @param newDepot that is going to be added to the list
     * @param s going to uodate the list of depots
     */

    public void updateDepot(Depot newDepot, String s){
        this.depotRepository.update(newDepot, s);
    }


    /**
     * The function is going to find a depot looking for a string
     * @param s is going to find a depot after looking about a certain String
     * @return the depotRepository after finding it
     */
    public Depot findDepot(String s){
        return this.depotRepository.find(s);
    }

    /**
     * It is going to add a new employee to the employeeRepository
     * @param e that is going to be added to the employeeRepository
     */



    //for employees
    public void addEmployee(Employee e){
        this.employeeRepository.add(e);
    }

    /**
     * Removes a user from the employeeRepos when looking for a given string
     * @param s
     * @return the remaining list of employeeRepos
     */
    public Employee removeEmployee(String s){
        return this.employeeRepository.remove(s);
    }


    /**
     *The function is going to update the employeeRepos
     * @param newEmployee that is going to be updated
     * @param s is going to be returned
     */
    public void updateEmployee(Employee newEmployee, String s){
        this.employeeRepository.update(newEmployee, s);
    }


    /**
     * Finding an employee just looking for a string
     * @param s is the parameter used for finding the employee
     * @return return in the final the employeeRepository
     */
    public Employee findEmployee(String s){
        return this.employeeRepository.find(s);
    }

    /**
     * Is going to add a new line to the lineRepo
     * @param line is going to be added to the lineRepository
     */
    //for lines
    public void addLine(Line line){
        this.lineRepository.add(line);
    }

    /**
     *The function is going to remove a line from lineRepo when looking about a string
     * @param s
     * @return the remaining lineRepo
     */
    public Line removeLine(String s){
       return this.lineRepository.remove(s);
    }


    /**
     * The function is going to update the lineRepos
     * @param newLine that is going to be updated
     * @param s the newest version of  lineRepos
     */
    public void updateLine(Line newLine, String s){
        this.lineRepository.update(newLine, s);
    }


    /**
     * Finding a line after looking for a certain string
     * @param s after looking
     * @return the lineRepos
     */
     public Line findLine(String s){
     return this.lineRepository.find(s);
     }

    //for programs
    public void addProgram(Program program)
    {
        this.programRepository.add(program);
    }


    public Program removeProgram(Integer s)
    {
        return this.programRepository.remove(s);
    }

    public void updateProgram(Program newProgram,Integer s)
    {
        this.programRepository.update(newProgram,s);
    }

    public Program findProgram(Integer s)
    {
        return this.programRepository.find(s);
    }

    //for stations
    public void addStation(Station station)
    {
        this.stationRepository.add(station);
    }

    public Station removeStation(Integer s)
    {
        return this.stationRepository.remove(s);
    }

    public void updateStation(Station newStation,Integer s)
    {
        this.stationRepository.update(newStation,s);
    }

    public Station findStation(Integer s)
    {
        return this.stationRepository.find(s);
    }

    //for ticketing sale points
    public void addTicketingSalePoint(TicketingSalePoint ticketingSalePoint){
        this.ticketingSalePointRepository.add(ticketingSalePoint);
    }

    public TicketingSalePoint removeTicketingSalePoint(String s){
        return this.ticketingSalePointRepository.remove(s);
    }

    public void updateTicketingSalePoint(TicketingSalePoint newTicketingSalePoint, String s){
        this.ticketingSalePointRepository.update(newTicketingSalePoint, s);
    }

    public TicketingSalePoint findTicketingSalePoint(String s){
        return this.ticketingSalePointRepository.find(s);
    }

    //for users
    public void addUser(User user)
    {
        this.userRepository.add(user);
    }

    public User removeUser(String s)
    {
        return this.userRepository.remove(s);
    }

    public void updateUser(User newUser,String s)
    {

        this.userRepository.update(newUser,s);
    }

    public User findUser(String s)
    {
        return this.userRepository.find(s);
    }

    //for vehicles
    public void addVehicle(Vehicle vehicle)
    {
        this.vehicleRepository.add(vehicle);
    }

    public Vehicle removeVehicle(String s)
    {
        return this.vehicleRepository.remove(s);
    }

    public void setDepotRepository(DepotRepository depotRepository) {
        this.depotRepository = depotRepository;
    }

    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void setLineRepository(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    public void setProgramRepository(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public void setStationRepository(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    public void setTicketingSalePointRepository(TicketingSalePointRepository ticketingSalePointRepository) {
        this.ticketingSalePointRepository = ticketingSalePointRepository;
    }

    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public void setVehicleRepository(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public void updateVehicle(Vehicle newVehicle, String s)
    {
        this.vehicleRepository.update(newVehicle,s);
    }

    public Vehicle findVehicle(String s)
    {
        return this.vehicleRepository.find(s);
    }


    public void login(String username, String password){
        User user = userRepository.findByUsernameAndPassword(username, password);
        if(user==null){
            System.out.println("You do not have an account!");
        }
    }
}



