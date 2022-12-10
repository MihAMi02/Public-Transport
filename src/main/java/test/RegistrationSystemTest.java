package test;

import Verkehrbestrieb_exceptions.EmployeeException;
import Verkehrbestrieb_exceptions.IncorrectCNPException;
import controller.RegistrationSystem;
import model.data.*;
import org.junit.jupiter.api.BeforeEach;
import repository.interfaces.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;






import static org.junit.jupiter.api.Assertions.*;

class RegistrationSystemTest
{

    private RegistrationSystem registrationSystem;



    @BeforeEach
    void setUp() throws EmployeeException
    {
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
    void addDepot()
    {

        Depot depot = new Depot("Titan");

        Vehicle bus = new DieselVehicle("WEB62809123456788", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, null);
        Vehicle tram = new ElectricVehicle("359", "Electroputere Craiova", "V3A-93", 1999, 300, "Tram", 65, null);

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

        Vehicle bus = new DieselVehicle("WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, null);
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
    void updateDepot()
    {
        Depot depot = new Depot("Floreasca");

        Vehicle bus = new DieselVehicle("WEB62809123456987", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, null);
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

        Vehicle bus = new DieselVehicle("WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, null);
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
    void addEmployee() throws IncorrectCNPException {
        Employee employee=new Employee(1,"712817981098","Andrei Creci","Chef","Depotplace",2000);

        registrationSystem.addEmployee(employee);

        assertEquals(registrationSystem.findEmployee(employee.getCnp()), employee);
        assertNotNull(registrationSystem.findEmployee("5060809123456"));
        assertNotNull(registrationSystem.findEmployee("5060809123455"));
    }

    @org.junit.jupiter.api.Test
    void removeEmployee() throws IncorrectCNPException {
        Employee employee2 = new Employee(2, "5060809123455", "Dabu Oprica Geani", "Director", "Floreasca", 5000);
        Employee result=registrationSystem.removeEmployee(employee2.getCnp());

        assertEquals(employee2.getCnp(),result.getCnp());


    }

    @org.junit.jupiter.api.Test
    void updateEmployee() throws IncorrectCNPException {

        Employee employee=new Employee(1,"712817981098","Andrei Creci","Chef","Depotplace",2000);
        registrationSystem.updateEmployee(employee,"5060809123455");

        assertNotNull(registrationSystem.findEmployee("712817981098"));



    }

    @org.junit.jupiter.api.Test
    void findEmployee() throws IncorrectCNPException {
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
        Line line2 = new Line("326", "Tram", "", null);
        registrationSystem.addLine(line2);
        Line result=registrationSystem.removeLine("326");
        assertEquals(line2.getLineNumber(),result.getLineNumber());



    }

    @org.junit.jupiter.api.Test
    void updateLine() {
        Line line3 = new Line("328", "Tram", "", null);
        registrationSystem.updateLine(line3,"327");
        Line result = registrationSystem.findLine("328");
        assertEquals(line3.getLineNumber(), result.getLineNumber());
    }

    @org.junit.jupiter.api.Test
    void findLine(){
        Line line2 = new Line("327", "Bus", "", null);
        Line result=registrationSystem.findLine("327");
        assertEquals(line2.getLineNumber(),result.getLineNumber());
    }

    @org.junit.jupiter.api.Test
    void addProgram() {
        Vehicle bus = new DieselVehicle("WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, null);

        Station station1 = new Station(1, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        Station station2 = new Station(2, "Scoala Gimnaziala nr. 127", "Str. Muntii Carpati, Nr. 30");
        List<Station> stationList1 = new ArrayList<>();
        stationList1.add(station1);
        stationList1.add(station2);
        Line line1 = new Line("327", "Bus", "", stationList1);

        Program program = new Program(2, bus, line1, "Evening", LocalDate.now());


        registrationSystem.addProgram(program);

        assertNotNull(registrationSystem.findProgram(1));
        assertNotNull(registrationSystem.findProgram(2));
    }

    @org.junit.jupiter.api.Test
    void removeProgram() {
        Program result = registrationSystem.removeProgram(1);
        assertNull(registrationSystem.findProgram(1));
        assertEquals(result.getLine().getLineNumber(), "327");
    }

    @org.junit.jupiter.api.Test
    void updateProgram() {
        Vehicle bus = new DieselVehicle("WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, null);

        Station station1 = new Station(1, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        Station station2 = new Station(2, "Scoala Gimnaziala nr. 127", "Str. Muntii Carpati, Nr. 30");
        List<Station> stationList1 = new ArrayList<>();
        stationList1.add(station1);
        stationList1.add(station2);
        Line line1 = new Line("327", "Bus", "", stationList1);

        Program program = new Program(1, bus, line1, "Evening", LocalDate.now());
        registrationSystem.updateProgram(program, 1);
        Program result = registrationSystem.findProgram(1);
        assertEquals(result.getLine(), program.getLine());
        assertEquals(result.getId(), program.getId());
        assertEquals(result.getDate(), program.getDate());
        assertEquals(result.getV(), program.getV());
        assertEquals(result.getShift(), program.getShift());

    }

    @org.junit.jupiter.api.Test
    void findProgram() {
        Vehicle bus = new DieselVehicle("WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, null);

        Station station1 = new Station(1, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        Station station2 = new Station(2, "Scoala Gimnaziala nr. 127", "Str. Muntii Carpati, Nr. 30");
        List<Station> stationList1 = new ArrayList<>();
        stationList1.add(station1);
        stationList1.add(station2);
        Line line1 = new Line("327", "Bus", "", stationList1);

        Program program = new Program(1, bus, line1, "Morning", LocalDate.now());
        Program result = registrationSystem.findProgram(1);
        assertEquals(result.getLine().getLineNumber(), program.getLine().getLineNumber());
        assertEquals(result.getId(), program.getId());
        assertEquals(result.getV().getVin(), program.getV().getVin());
        assertEquals(result.getShift(), program.getShift());
    }

    @org.junit.jupiter.api.Test
    void addStation() {
        Station station1 = new Station(1, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        registrationSystem.addStation(station1);
        assertNotNull(station1.getStationId());


    }

    @org.junit.jupiter.api.Test
    void removeStation() {
        Station station1 = new Station(1, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        Station station2 = new Station(2, "Scoala Gimnaziala nr. 127", "Str. Muntii Carpati, Nr. 30");

        registrationSystem.addStation(station2);

        Station result=registrationSystem.removeStation(2);

        assertEquals(result.getStationId(),station2.getStationId());
        assertNull(registrationSystem.findStation(2));


    }

    @org.junit.jupiter.api.Test
    void updateStation() {
        Station station3 = new Station(3, "Scoala Gimnaziala nr. 128", "Str. Muntii Calimani, Nr. 31");
        registrationSystem.updateStation(station3,2);
        Station result = registrationSystem.findStation(3);
        assertEquals(result.getStationId(), station3.getStationId());
    }

    @org.junit.jupiter.api.Test
    void findStation() {
        Station station3 = new Station(2, "Scoala Gimnaziala nr. 127", "Str. Muntii Calimani, Nr. 31");
        Station result = registrationSystem.findStation(2);
        assertEquals(result.getStationId(),station3.getStationId());
    }

    @org.junit.jupiter.api.Test
    void addTicketingSalePoint() {
        TicketingSalePoint ticketingSalePoint1 = new TicketingSalePoint("TVM_GAR_NORD", "Ticketing Machine");
        registrationSystem.addTicketingSalePoint(ticketingSalePoint1);
        assertNotNull(registrationSystem.findTicketingSalePoint("TVM_GAR_NORD"));
        assertNotNull(registrationSystem.findTicketingSalePoint("TVM_BUC_OBR"));
        assertNotNull(registrationSystem.findTicketingSalePoint("DNA_GHICA"));
    }

    @org.junit.jupiter.api.Test
    void removeTicketingSalePoint() {
        TicketingSalePoint ticketingSalePoint1 = new TicketingSalePoint("TVM_BUC_OBR", "Ticketing Machine");
        TicketingSalePoint result = registrationSystem.removeTicketingSalePoint("TVM_BUC_OBR");
        assertEquals(ticketingSalePoint1.getId(),result.getId());
        assertEquals(ticketingSalePoint1.getType(),result.getType());
        assertEquals(ticketingSalePoint1.getSoldTickets(),result.getSoldTickets());
        assertNull(registrationSystem.findTicketingSalePoint("TVM_BUC_OBR"));
        assertNotNull(registrationSystem.findTicketingSalePoint("DNA_GHICA"));
    }

    @org.junit.jupiter.api.Test
    void updateTicketingSalePoint() {
        TicketingSalePoint temp = registrationSystem.findTicketingSalePoint("DNA_GHICA");
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(new Ticket(3.0f, "Bilet", 1));
        temp.setSoldTickets(tickets);
        registrationSystem.updateTicketingSalePoint(temp,"DNA_GHICA");
        TicketingSalePoint result = registrationSystem.findTicketingSalePoint("DNA_GHICA");
        assertEquals(temp.getId(),result.getId());
        assertEquals(temp.getType(), result.getType());
        assertEquals(temp.getSoldTickets(), result.getSoldTickets());
    }

    @org.junit.jupiter.api.Test
    void findTicketingSalePoint() {
        TicketingSalePoint temp = new TicketingSalePoint("DNA_GHICA", "Ticketing Centre");
        TicketingSalePoint result = registrationSystem.findTicketingSalePoint("DNA_GHICA");
        assertEquals(temp.getId(),result.getId());
        assertEquals(temp.getType(), result.getType());
        assertEquals(temp.getSoldTickets(), result.getSoldTickets());
    }

    @org.junit.jupiter.api.Test
    void addUser() {
        Customer user1 = new Customer("ionel12", "parola");
        registrationSystem.addUser(user1);
        assertNotNull(user1.getUsername());

    }

    @org.junit.jupiter.api.Test
    void removeUser() {
        Customer user1 = new Customer("ionel12", "parola");
        Customer user2 = new Customer("geani_dabu", "1");
        registrationSystem.addUser(user2);
        Customer result=registrationSystem.removeUser("geani_dabu");

        assertEquals(result.getUsername(),user2.getUsername());
        assertNull(registrationSystem.findUser("geani_dabu"));

    }

    @org.junit.jupiter.api.Test
    void updateUser() {


        Customer user3 = new Customer("geani_dabu_clona", "2");
        registrationSystem.addUser(user3);

        registrationSystem.updateUser(user3,"geani_dabu_clona");
        Customer result=registrationSystem.findUser("geani_dabu_clona");
        assertEquals(result.getUsername(),user3.getUsername());

    }

    @org.junit.jupiter.api.Test
    void findUser() {
        Customer user3 = new Customer("geani_dabu_clona", "2");
        registrationSystem.addUser(user3);
        Customer result=registrationSystem.findUser("geani_dabu_clona");
        assertEquals(user3.getUsername(),result.getUsername());


    }

    @org.junit.jupiter.api.Test
    void addVehicle()
    {
        Vehicle bus = new DieselVehicle("WEB62809123456790", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, null);
        registrationSystem.addVehicle(bus);
        assertNotNull(registrationSystem.findVehicle(bus.getVin()));

    }

    @org.junit.jupiter.api.Test
    void removeVehicle()
    {
        Vehicle bus = new DieselVehicle("WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, null);
        Vehicle tram = new ElectricVehicle("3344", "CKD Tatra", "T4R", 1977, 100, "Tram", 65, null);
        registrationSystem.addVehicle(tram);
        Vehicle result=registrationSystem.removeVehicle(tram.getVin());
        assertEquals(result.getVin(),tram.getVin());
        assertNull(registrationSystem.findVehicle(tram.getVin()));
        assertNotNull(registrationSystem.findVehicle(bus.getVin()));
    }

    @org.junit.jupiter.api.Test
    void updateVehicle()
    {
        Vehicle bus = new DieselVehicle("WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, null);
        bus.setInMaintenance(true);
        registrationSystem.updateVehicle(bus, "WEB62809123456789");
        assertTrue(registrationSystem.findVehicle("WEB62809123456789").isInMaintenance());
    }

    @org.junit.jupiter.api.Test
    void findVehicle()
    {
        Vehicle result = registrationSystem.findVehicle("WEB62809123456789");
        assertNotNull(result);
    }

    @org.junit.jupiter.api.Test
    void login()
    {
        Customer user1 = new Customer("ionel12", "parola");
        registrationSystem.login(user1.getUsername(), user1.getPassword());
    }
}

