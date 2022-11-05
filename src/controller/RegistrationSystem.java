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

    /**
     * generate Singleton Instance of registration system
     * @return Singleton Instance of registration system
     */
    public static RegistrationSystem getInstance(){
        if(single_instance == null)
            single_instance = new RegistrationSystem();
        return  single_instance;
    }

    //for depots

    /**
     * Adds a given depot to the given instance
     * @param depot new depot to be added
     */
    public void addDepot(Depot depot){
        this.depotRepository.add(depot);
    }

    /**
     * Removes the depot from the depot lists
     * @param s Name of depot to be removed
     * @return removed depot
     */
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
    /**
     * Adds given Program to the repository
     * @param program new Program to be added
     */
     public void addProgram(Program program)
     {
     this.programRepository.add(program);
     }

     /**
     * Removes Program with given id
     * @param s id of program to be removed
     * @return removed program
     */
    public Program removeProgram(Integer s)
    {
        return this.programRepository.remove(s);
    }

    /**
     * updates info of program with given id
     * @param newProgram Program with updated info
     * @param s id of program to be updated
     */
    public void updateProgram(Program newProgram,Integer s)
    {
        this.programRepository.update(newProgram,s);
    }

    /**
     * finds Program with given id
     * @param s id of program to be found
     * @return found program
     */
    public Program findProgram(Integer s)
    {
        return this.programRepository.find(s);
    }

    //for stations
    /**
     * Adds given Station to the repository
     * @param station new Station to be added
     */
    public void addStation(Station station)
    {
        this.stationRepository.add(station);
    }

    /**
     * Removes Station with given id
     * @param s id of station to be removed
     * @return removed station
     */
    public Station removeStation(Integer s)
    {
        return this.stationRepository.remove(s);
    }

    /**
     * Updates Station with given id
     * @param newStation Station with updated info
     * @param s id of station to be updated
     */
    public void updateStation(Station newStation,Integer s)
    {
        this.stationRepository.update(newStation,s);
    }

    /**
     * Finds station with given id
     * @param s id of station to be found
     * @return station with id
     */
    public Station findStation(Integer s)
    {
        return this.stationRepository.find(s);
    }

    //for ticketing sale points

    /**
     * Adds given TicketingSalePoint to the repository
     * @param ticketingSalePoint new TSP to be added
     */
    public void addTicketingSalePoint(TicketingSalePoint ticketingSalePoint){
        this.ticketingSalePointRepository.add(ticketingSalePoint);
    }

    /**
     * Removes TicketingSalePoint with given id
     * @param s id for TSP to be removed
     * @return removed TSP
     */
    public TicketingSalePoint removeTicketingSalePoint(String s){
        return this.ticketingSalePointRepository.remove(s);
    }

    /**
     * Updates information of TicketingSalePoint with given id
     * @param newTicketingSalePoint TSP with updated info
     * @param s given TSP id
     */
    public void updateTicketingSalePoint(TicketingSalePoint newTicketingSalePoint, String s){
        this.ticketingSalePointRepository.update(newTicketingSalePoint, s);
    }

    /**
     * Finds ticketing sale point with given id
     * @param s id of tsp to be found
     * @return tsp with given id
     */
    public TicketingSalePoint findTicketingSalePoint(String s){
        return this.ticketingSalePointRepository.find(s);
    }

    //for users
    /**
     * Adds given user
     * @param user User to be added
     */
    public void addUser(User user)
    {
        this.userRepository.add(user);
    }

    /**
     * Removes user with given username
     * @param s Username of User to be removed
     * @return removed user
     */
    public User removeUser(String s)
    {
        return this.userRepository.remove(s);
    }

    /**
     * Updates user with given username
     * @param newUser User Object with updated information
     * @param s Username of user to be updated
     */
    public void updateUser(User newUser,String s)
    {

        this.userRepository.update(newUser,s);
    }

    /**
     * Find user with given username
     * @param s Username to be searched after
     * @return User with given username
     */
    public User findUser(String s)
    {
        return this.userRepository.find(s);
    }

    //for vehicles

    /**
     * Adds a new Vehicle to the repository
     * @param vehicle new Vehicle Object to be added
     */
    public void addVehicle(Vehicle vehicle)
    {
        this.vehicleRepository.add(vehicle);
    }

    /**
     * removes Vehicle with given VIN Identification
     * @param s VIN Identification for Vehicle to be removed
     * @return removed vehicle
     */
    public Vehicle removeVehicle(String s)
    {
        return this.vehicleRepository.remove(s);
    }


    /**
     * sets the depot repository
     * @param depotRepository instance of class which implements depotRepository interface
     */
    public void setDepotRepository(DepotRepository depotRepository) {
        this.depotRepository = depotRepository;
    }


    /**
     * sets the employee repository
     * @param employeeRepository instance of class which implements employeeRepository interface
     */
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    /**
     * sets the line repository
     * @param lineRepository instance of class which implements lineRepository interface
     */
    public void setLineRepository(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }


    /**
     * sets the program repository
     * @param programRepository instance of class which implements programRepository interface
     */
    public void setProgramRepository(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }


    /**
     * sets the station repository
     * @param stationRepository instance of class which implements stationRepository interface
     */
    public void setStationRepository(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }


    /**
     * sets the ticketingSalePoint repository
     * @param ticketingSalePointRepository instance of class which implements ticketingSalePointRepository interface
     */
    public void setTicketingSalePointRepository(TicketingSalePointRepository ticketingSalePointRepository) {
        this.ticketingSalePointRepository = ticketingSalePointRepository;
    }


    /**
     * sets the user repository
     * @param userRepository instance of class which implements userRepository interface
     */
    public void setUserRepository(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    /**
     * sets the vehicle repository
     * @param vehicleRepository instance of class which implements vehicleRepository interface
     */
    public void setVehicleRepository(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    /**
     * updates a Vehicle with the given VIN Identification
     * @param newVehicle Vehicle with updated attributes
     * @param s VIN identification for Vehicle
     */
    public void updateVehicle(Vehicle newVehicle, String s)
    {
        this.vehicleRepository.update(newVehicle,s);
    }

    /**
     * Finds a vehicle in the repository
     * @param s VIN identification for a vehicle
     * @return  Vehicle with given VIN
     */
    public Vehicle findVehicle(String s)
    {
        return this.vehicleRepository.find(s);
    }

    /**
     * Does the login for a given username and password
     * @param username given username
     * @param password given password
     */
    public void login(String username, String password){
        User user = userRepository.findByUsernameAndPassword(username, password);
        if(user==null){
            System.out.println("You do not have an account!");
        }
    }
}



