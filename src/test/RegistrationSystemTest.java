package test;

import controller.RegistrationSystem;
import model.data.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import repository.interfaces.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationSystemTest {

    private RegistrationSystem registrationSystem;



    @BeforeEach
    void setUp(){
        DepotRepository depotRepository = new repository.inMemoryRepository.DepotRepository();
        EmployeeRepository employeeRepository=new repository.inMemoryRepository.EmployeeRepository();
        LineRepository lineRepository=new repository.inMemoryRepository.LineRepository();
        ProgramRepository programRepository=new repository.inMemoryRepository.ProgramRepository();
        StationRepository stationRepository=new repository.inMemoryRepository.StationRepository();
        TicketingSalePointRepository ticketingSalePointRepository=new repository.inMemoryRepository.TicketingSalePointRepository();
        UserRepository userRepository=new repository.inMemoryRepository.UserRepository();
        VehicleRepository vehicleRepository=new repository.inMemoryRepository.VehicleRepository();

        registrationSystem = RegistrationSystem.getInstance();
        registrationSystem.setDepotRepository(depotRepository);
        registrationSystem.setEmployeeRepository(employeeRepository);
        registrationSystem.setLineRepository(lineRepository);
        registrationSystem.setProgramRepository(programRepository);
        registrationSystem.setStationRepository(stationRepository);
        registrationSystem.setTicketingSalePointRepository(ticketingSalePointRepository);
        registrationSystem.setUserRepository(userRepository);
        registrationSystem.setVehicleRepository(vehicleRepository);
    }

    @org.junit.jupiter.api.Test
    void addDepot() {
        Depot depot = new Depot("Titan");

        Vehicle bus = new DieselVehicle("WEB62809123456788", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4);
        Vehicle tram = new ElectricVehicle("359", "Electroputere Craiova", "V3A-93", 1999, 300, "Tram", 65);

        depot.addVehicle(bus);
        depot.addVehicle(tram);

        registrationSystem.addDepot(depot);
        assertEquals(registrationSystem.findDepot("Titan"), depot);
        assertNotNull(registrationSystem.findDepot("Floreasca"));
        assertNotNull(registrationSystem.findDepot("Militari"));
    }

    @org.junit.jupiter.api.Test
    void removeDepot() {
        Depot depot = new Depot("Floreasca");

        Vehicle bus = new DieselVehicle("WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4);
        depot.addVehicle(bus);

        Depot result = registrationSystem.removeDepot("Floreasca");
        assertEquals(result.getName(), depot.getName());

        List<Vehicle> resultingVehicles = result.getVehicles();
        List<Vehicle> testedVehicles = new ArrayList<>();
        testedVehicles.add(bus);
        for(int i=0; i<testedVehicles.size(); i++){
            assertEquals(resultingVehicles.get(i).getVin(), testedVehicles.get(i).getVin());
            assertEquals(resultingVehicles.get(i).getMake(), testedVehicles.get(i).getMake());
            assertEquals(resultingVehicles.get(i).getModel(), testedVehicles.get(i).getModel());
            assertEquals(resultingVehicles.get(i).getBuilt(), testedVehicles.get(i).getBuilt());
            assertEquals(resultingVehicles.get(i).getCapacity(), testedVehicles.get(i).getCapacity());
        }
        assertNull(registrationSystem.removeDepot("Floreasca"));
    }

    @org.junit.jupiter.api.Test
    void updateDepot() {
        Depot depot = new Depot("Floreasca");

        Vehicle bus = new DieselVehicle("WEB62809123456987", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4);
        depot.addVehicle(bus);

        registrationSystem.updateDepot(depot, "Floreasca");
        Depot result = registrationSystem.findDepot("Floreasca");
        assertEquals(result.getName(), depot.getName());

        List<Vehicle> resultingVehicles = result.getVehicles();
        List<Vehicle> testedVehicles = new ArrayList<>();
        testedVehicles.add(bus);
        for(int i=0; i<testedVehicles.size(); i++){
            assertEquals(resultingVehicles.get(i).getVin(), testedVehicles.get(i).getVin());
            assertEquals(resultingVehicles.get(i).getMake(), testedVehicles.get(i).getMake());
            assertEquals(resultingVehicles.get(i).getModel(), testedVehicles.get(i).getModel());
            assertEquals(resultingVehicles.get(i).getBuilt(), testedVehicles.get(i).getBuilt());
            assertEquals(resultingVehicles.get(i).getCapacity(), testedVehicles.get(i).getCapacity());
        }
    }

    @org.junit.jupiter.api.Test
    void findDepot() {
        Depot depot = new Depot("Floreasca");

        Vehicle bus = new DieselVehicle("WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4);
        depot.addVehicle(bus);

        Depot result = registrationSystem.findDepot("Floreasca");
        assertEquals(result.getName(), depot.getName());

        List<Vehicle> resultingVehicles = result.getVehicles();
        List<Vehicle> testedVehicles = new ArrayList<>();
        testedVehicles.add(bus);
        for(int i=0; i<testedVehicles.size(); i++) {
            assertEquals(resultingVehicles.get(i).getVin(), testedVehicles.get(i).getVin());
            assertEquals(resultingVehicles.get(i).getMake(), testedVehicles.get(i).getMake());
            assertEquals(resultingVehicles.get(i).getModel(), testedVehicles.get(i).getModel());
            assertEquals(resultingVehicles.get(i).getBuilt(), testedVehicles.get(i).getBuilt());
            assertEquals(resultingVehicles.get(i).getCapacity(), testedVehicles.get(i).getCapacity());
        }
        assertNotNull(registrationSystem.findDepot("Floreasca"));
    }

    @org.junit.jupiter.api.Test
    void addEmployee() {
        Employee employee=new Employee(1,"712817981098","Andrei Creci","Chef","Depotplace",2000);

        registrationSystem.addEmployee(employee);

        assertEquals(registrationSystem.findEmployee(employee.getCnp()), employee);
        assertNotNull(registrationSystem.findEmployee("5060809123456"));
        assertNotNull(registrationSystem.findEmployee("5060809123455"));
    }

    @org.junit.jupiter.api.Test
    void removeEmployee() {
        Employee employee2 = new Employee(2, "5060809123455", "Dabu Oprica Geani", "Director", "Floreasca", 5000);
        Employee result=registrationSystem.removeEmployee(employee2.getCnp());

        assertEquals(employee2.getCnp(),result.getCnp());


    }

    @org.junit.jupiter.api.Test
    void updateEmployee() {

        Employee employee=new Employee(1,"712817981098","Andrei Creci","Chef","Depotplace",2000);
        registrationSystem.updateEmployee(employee,"5060809123455");

        assertNotNull(registrationSystem.findEmployee("712817981098"));



    }

    @org.junit.jupiter.api.Test
    void findEmployee() {
        Employee employee2 = new Employee(2, "5060809123455", "Dabu Oprica Geani", "Director", "Floreasca", 5000);

        Employee result=registrationSystem.findEmployee("5060809123455");

        assertEquals(result.getCnp(),employee2.getCnp());

    }

    @org.junit.jupiter.api.Test
    void addLine() {
        Line line1 = new Line("320", "Bus", "", null);

        registrationSystem.addLine(line1);
       assertNotNull(registrationSystem.findLine("320"));


    }

    @org.junit.jupiter.api.Test
    void removeLine() {
    }

    @org.junit.jupiter.api.Test
    void updateLine() {
    }

    @org.junit.jupiter.api.Test
    void findLine() {
    }

    @org.junit.jupiter.api.Test
    void addProgram() {



    }

    @org.junit.jupiter.api.Test
    void removeProgram() {
    }

    @org.junit.jupiter.api.Test
    void updateProgram() {
    }

    @org.junit.jupiter.api.Test
    void findProgram() {
    }

    @org.junit.jupiter.api.Test
    void addStation() {

    }

    @org.junit.jupiter.api.Test
    void removeStation() {
    }

    @org.junit.jupiter.api.Test
    void updateStation() {
    }

    @org.junit.jupiter.api.Test
    void findStation() {
    }

    @org.junit.jupiter.api.Test
    void addTicketingSalePoint() {
    }

    @org.junit.jupiter.api.Test
    void removeTicketingSalePoint() {
    }

    @org.junit.jupiter.api.Test
    void updateTicketingSalePoint() {
    }

    @org.junit.jupiter.api.Test
    void findTicketingSalePoint() {
    }

    @org.junit.jupiter.api.Test
    void addUser() {




    }

    @org.junit.jupiter.api.Test
    void removeUser() {
    }

    @org.junit.jupiter.api.Test
    void updateUser() {
    }

    @org.junit.jupiter.api.Test
    void findUser() {
    }

    @org.junit.jupiter.api.Test
    void addVehicle() {

    }

    @org.junit.jupiter.api.Test
    void removeVehicle() {
    }

    @org.junit.jupiter.api.Test
    void updateVehicle() {
    }

    @org.junit.jupiter.api.Test
    void findVehicle() {
    }

    @org.junit.jupiter.api.Test
    void login() {
    }
}