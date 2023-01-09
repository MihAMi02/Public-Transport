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
import static org.junit.jupiter.api.Assertions.assertNotNull;



public class HibernateTest
{

    private RegistrationSystem registrationSystem;



    @BeforeEach
    void setUp() throws EmployeeException
    {

        DepotRepository depotRepository         =new repository.hibernateRepository.DepotRepository();
        EmployeeRepository employeeRepository   =new repository.hibernateRepository.EmployeeRepository();
        LineRepository lineRepository           =new repository.hibernateRepository.LineRepository();
        ProgramRepository programRepository     =new repository.hibernateRepository.ProgramRepository();
        StationRepository stationRepository     =new repository.hibernateRepository.StationRepository();
        TicketingSalePointRepository ticketingSalePointRepository=new repository.hibernateRepository.TicketingSalePointRepository();
        UserRepository userRepository           =new repository.hibernateRepository.UserRepository();
        VehicleRepository vehicleRepository     =new repository.hibernateRepository.VehicleRepository();
        TicketTypeRepository ticketTypeRepository =new repository.hibernateRepository.TicketTypeRepository();
        TicketRepository ticketRepository       =new repository.hibernateRepository.TicketRepository();

        registrationSystem = RegistrationSystem.getInstance();
        registrationSystem.setDepotRepository(depotRepository);
        registrationSystem.setEmployeeRepository(employeeRepository);
        registrationSystem.setLineRepository(lineRepository);
        registrationSystem.setProgramRepository(programRepository);
        registrationSystem.setStationRepository(stationRepository);
        registrationSystem.setTicketingSalePointRepository(ticketingSalePointRepository);
        registrationSystem.setUserRepository(userRepository);
        registrationSystem.setVehicleRepository(vehicleRepository);
        registrationSystem.setTicketTypeRepository(ticketTypeRepository);
        registrationSystem.setTicketRepository(ticketRepository);
    }

    @org.junit.jupiter.api.Test
    void addDepot()
    {

        Depot depot = new Depot("void_testing");

        Vehicle bus = new DieselVehicle("void_WEB62809123456788", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, depot);
        Vehicle tram = new ElectricVehicle("void_359", "Electroputere Craiova", "V3A-93", 1999, 300, "Tram", 65, depot);

        depot.addVehicle(bus);
        depot.addVehicle(tram);

        registrationSystem.addVehicle(bus);
        registrationSystem.addVehicle(tram);
        registrationSystem.addDepot(depot);
        assertEquals(registrationSystem.findDepot("void_testing"), depot);
        registrationSystem.removeDepot("void_testing");
        registrationSystem.removeVehicle("void_WEB62809123456788");
        registrationSystem.removeVehicle("void_359");



    }


    @org.junit.jupiter.api.Test
    void removeDepot() {
        Depot depot = new Depot("void_testing");

        Vehicle bus = new DieselVehicle("void_WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, depot);

        registrationSystem.addVehicle(bus);
        depot.addVehicle(bus);
        registrationSystem.addDepot(depot);
        Depot result = registrationSystem.removeDepot("void_testing");
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
        assertNull(registrationSystem.removeDepot("void_testing"));
        registrationSystem.removeVehicle("void_WEB62809123456789");
    }

    @org.junit.jupiter.api.Test
    void updateDepot()
    {
        Depot depot = new Depot("void_testing");

        Vehicle bus = new DieselVehicle("void_WEB62809123456987", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, depot);
        depot.addVehicle(bus);
        registrationSystem.addVehicle(bus);
        registrationSystem.addDepot(depot);
        registrationSystem.updateDepot(depot, "void_testing");
        Depot result = registrationSystem.findDepot("void_testing");
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
        registrationSystem.removeDepot("void_testing");
        registrationSystem.removeVehicle("void_WEB62809123456987");
    }

    @org.junit.jupiter.api.Test
    void findDepot()
    {
        Depot depot = new Depot("void_testing");

        Vehicle bus = new DieselVehicle("void_WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, depot);
        depot.addVehicle(bus);
        registrationSystem.addVehicle(bus);
        registrationSystem.addDepot(depot);

        Depot result = registrationSystem.findDepot("void_testing");
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
        assertNotNull(registrationSystem.findDepot("void_testing"));
        registrationSystem.removeDepot("void_testing");
        registrationSystem.removeVehicle("void_WEB62809123456789");
    }

    @org.junit.jupiter.api.Test
    void addEmployee() throws EmployeeException {
        Employee employee=new Employee("1851213514783","Andrei Creci","Director","Depotplace",2000);

        registrationSystem.addEmployee(employee);

        assertEquals(registrationSystem.findEmployee(employee.getCnp()), employee);
        registrationSystem.removeEmployee("1851213514783");
    }

    @org.junit.jupiter.api.Test
    void removeEmployee() throws EmployeeException {
        Employee employee2 = new Employee("1851212210544", "Dabu Oprica Geani", "Director", "Floreasca", 5000);
        registrationSystem.addEmployee(employee2);
        Employee result=registrationSystem.removeEmployee(employee2.getCnp());

        assertEquals(employee2.getCnp(),result.getCnp());


    }

    @org.junit.jupiter.api.Test
    void updateEmployee() throws EmployeeException
    {

        Employee employee=new Employee("1851213514783","Andrei Creci","Director","Depotplace",2000);
        registrationSystem.addEmployee(employee);
        Employee employee2=new Employee("1851213514783","Andrei Creci","Director","Depotplace",3000);

        registrationSystem.updateEmployee(employee2,"1851213514783");

        Employee result = registrationSystem.findEmployee("1851213514783");
        assertNotNull(result);
        assertEquals(3000, result.getSalary());
        registrationSystem.removeEmployee("1851213514783");


    }

    @org.junit.jupiter.api.Test
    void findEmployee() throws EmployeeException {
        Employee employee2 = new Employee("1851212210544", "Dabu Oprica Geani", "Director", "Floreasca", 5000);
        registrationSystem.addEmployee(employee2);
        Employee result=registrationSystem.findEmployee("1851212210544");

        assertEquals(result.getCnp(),employee2.getCnp());
        registrationSystem.removeEmployee("1851212210544");

    }

    @org.junit.jupiter.api.Test
    void addLine() {
        Line line1 = new Line("void_320", "Bus", "", null);
        registrationSystem.addLine(line1);
        assertNotNull(registrationSystem.findLine("void_320"));
        registrationSystem.removeLine("void_320");

    }

    @org.junit.jupiter.api.Test
    void removeLine() {
        Line line2 = new Line("void_326", "Tram", "", null);
        registrationSystem.addLine(line2);
        Line result=registrationSystem.removeLine("void_326");
        assertEquals(line2.getLineNumber(),result.getLineNumber());



    }

    @org.junit.jupiter.api.Test
    void updateLine() {
        Line line3 = new Line("void_328", "Tram", "", null);
        registrationSystem.addLine(line3);
        line3.setType("Bus");
        registrationSystem.updateLine(line3,"void_328");
        Line result = registrationSystem.findLine("void_328");
        assertEquals(line3.getLineNumber(), result.getLineNumber());
        assertEquals(line3.getType(), result.getType());
    }

    @org.junit.jupiter.api.Test
    void findLine(){
        Line line2 = new Line("void_327", "Bus", "", null);
        registrationSystem.addLine(line2);
        Line result=registrationSystem.findLine("void_327");
        assertEquals(line2.getLineNumber(),result.getLineNumber());
        registrationSystem.removeLine("void_327");
    }

    @org.junit.jupiter.api.Test
    void addProgram() {
        Depot depot = new Depot("void_testing");
        Vehicle bus = new DieselVehicle("void_WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, depot);
        depot.addVehicle(bus);
        registrationSystem.addVehicle(bus);
        registrationSystem.addDepot(depot);

        int sId1=registrationSystem.getNextStationID();
        Station station1 = new Station(sId1, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        registrationSystem.addStation(station1);

        int sId2=registrationSystem.getNextStationID();
        Station station2 = new Station(sId2, "Scoala Gimnaziala nr. 127", "Str. Muntii Carpati, Nr. 30");
        registrationSystem.addStation(station2);

        List<Station> stationList1 = new ArrayList<>();
        stationList1.add(station1);
        stationList1.add(station2);

        Line line1 = new Line("void_327", "Bus", "", stationList1);
        registrationSystem.addLine(line1);

        int pId = registrationSystem.getNextProgramID();
        Program program = new Program(pId, bus, line1, "Evening", LocalDate.now());

        registrationSystem.addProgram(program);

        assertNotNull(registrationSystem.findProgram(pId));
        registrationSystem.removeProgram(pId);
        registrationSystem.removeLine("void_327");
        registrationSystem.removeStation(sId2);
        registrationSystem.removeStation(sId1);
        registrationSystem.removeDepot("void_testing");
        registrationSystem.removeVehicle("void_WEB62809123456789");
    }

    @org.junit.jupiter.api.Test
    void removeProgram() {
        Depot depot = new Depot("void_testing");
        Vehicle bus = new DieselVehicle("void_WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, depot);
        depot.addVehicle(bus);
        registrationSystem.addVehicle(bus);
        registrationSystem.addDepot(depot);

        int sId1=registrationSystem.getNextStationID();
        Station station1 = new Station(sId1, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        registrationSystem.addStation(station1);

        int sId2=registrationSystem.getNextStationID();
        Station station2 = new Station(sId2, "Scoala Gimnaziala nr. 127", "Str. Muntii Carpati, Nr. 30");
        registrationSystem.addStation(station2);

        List<Station> stationList1 = new ArrayList<>();
        stationList1.add(station1);
        stationList1.add(station2);

        Line line1 = new Line("void_327", "Bus", "", stationList1);
        registrationSystem.addLine(line1);

        int pId = registrationSystem.getNextProgramID();
        Program program = new Program(pId, bus, line1, "Evening", LocalDate.now());

        registrationSystem.addProgram(program);

        registrationSystem.removeProgram(pId);
        registrationSystem.removeLine("void_327");
        assertNull(registrationSystem.findProgram(pId));
        registrationSystem.removeStation(sId2);
        registrationSystem.removeStation(sId1);
        registrationSystem.removeDepot("void_testing");
        registrationSystem.removeVehicle("void_WEB62809123456789");
    }

    @org.junit.jupiter.api.Test
    void updateProgram() {
        Depot depot = new Depot("void_testing");
        Vehicle bus = new DieselVehicle("void_WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, depot);
        depot.addVehicle(bus);
        registrationSystem.addVehicle(bus);
        registrationSystem.addDepot(depot);

        int sId1=registrationSystem.getNextStationID();
        Station station1 = new Station(sId1, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        registrationSystem.addStation(station1);

        int sId2=registrationSystem.getNextStationID();
        Station station2 = new Station(sId2, "Scoala Gimnaziala nr. 127", "Str. Muntii Carpati, Nr. 30");
        registrationSystem.addStation(station2);

        List<Station> stationList1 = new ArrayList<>();
        stationList1.add(station1);
        stationList1.add(station2);

        Line line1 = new Line("void_327", "Bus", "", stationList1);
        registrationSystem.addLine(line1);

        int pId = registrationSystem.getNextProgramID();
        Program program = new Program(pId, bus, line1, "Evening", LocalDate.now());

        registrationSystem.addProgram(program);

        Program result = registrationSystem.findProgram(pId);
        assertEquals("Evening", result.getShift());
        program.setShift("Morning");
        registrationSystem.updateProgram(program, pId);

        result = registrationSystem.findProgram(pId);
        assertEquals("Morning", result.getShift());
        registrationSystem.removeProgram(pId);
        registrationSystem.removeLine("void_327");
        registrationSystem.removeStation(sId2);
        registrationSystem.removeStation(sId1);
        registrationSystem.removeDepot("void_testing");
        registrationSystem.removeVehicle("void_WEB62809123456789");

    }

    @org.junit.jupiter.api.Test
    void findProgram() {
        Depot depot = new Depot("void_testing");
        Vehicle bus = new DieselVehicle("void_WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, depot);
        depot.addVehicle(bus);
        registrationSystem.addVehicle(bus);
        registrationSystem.addDepot(depot);

        int sId1=registrationSystem.getNextStationID();
        Station station1 = new Station(sId1, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        registrationSystem.addStation(station1);

        int sId2=registrationSystem.getNextStationID();
        Station station2 = new Station(sId2, "Scoala Gimnaziala nr. 127", "Str. Muntii Carpati, Nr. 30");
        registrationSystem.addStation(station2);

        List<Station> stationList1 = new ArrayList<>();
        stationList1.add(station1);
        stationList1.add(station2);

        Line line1 = new Line("void_327", "Bus", "", stationList1);
        registrationSystem.addLine(line1);

        int pId = registrationSystem.getNextProgramID();
        Program program = new Program(pId, bus, line1, "Evening", LocalDate.now());

        registrationSystem.addProgram(program);

        assertNotNull(registrationSystem.findProgram(pId));
        registrationSystem.removeProgram(pId);
        registrationSystem.removeLine("void_327");
        registrationSystem.removeStation(sId2);
        registrationSystem.removeStation(sId1);
        registrationSystem.removeDepot("void_testing");
        registrationSystem.removeVehicle("void_WEB62809123456789");
    }

    @org.junit.jupiter.api.Test
    void addStation() {
        int stationId = registrationSystem.getNextStationID();
        Station station1 = new Station(stationId, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        registrationSystem.addStation(station1);
        assertNotNull(registrationSystem.findStation(stationId));
        registrationSystem.removeStation(stationId);

    }

    @org.junit.jupiter.api.Test
    void removeStation()
    {
        int sId = registrationSystem.getNextStationID();
        Station station1 = new Station(sId, "Scoala Gimnaziala nr. 141", "Str. Muntii Carpati, Nr. 8");
        registrationSystem.addStation(station1);

        Station result=registrationSystem.removeStation(sId);

        assertEquals(result.getStationId(),station1.getStationId());
        assertNull(registrationSystem.findStation(sId));


    }

    @org.junit.jupiter.api.Test
    void updateStation()
    {
        int sId = registrationSystem.getNextStationID();
        Station station3 = new Station(sId, "Scoala Gimnaziala nr. 128", "Str. Muntii Calimani, Nr. 31");
        registrationSystem.addStation(station3);
        station3.setAddress("VOID");
        registrationSystem.updateStation(station3,sId);
        Station result = registrationSystem.findStation(sId);
        assertEquals(result.getStationId(), station3.getStationId());
        assertEquals("VOID", result.getAddress());
        registrationSystem.removeStation(sId);
    }

    @org.junit.jupiter.api.Test
    void findStation() {
        int sId = registrationSystem.getNextStationID();
        Station station3 = new Station(sId, "Scoala Gimnaziala nr. 127", "Str. Muntii Calimani, Nr. 31");
        registrationSystem.addStation(station3);
        Station result = registrationSystem.findStation(sId);
        assertEquals(result.getStationId(),station3.getStationId());
        registrationSystem.removeStation(sId);
    }

    @org.junit.jupiter.api.Test
    void addTicketingSalePoint() {
        TicketingSalePoint ticketingSalePoint1 = new TicketingSalePoint("void_TVM_GAR_NORD", "Ticketing Machine");
        registrationSystem.addTicketingSalePoint(ticketingSalePoint1);
        assertNotNull(registrationSystem.findTicketingSalePoint("void_TVM_GAR_NORD"));
        registrationSystem.removeTicketingSalePoint("void_TVM_GAR_NORD");
    }

    @org.junit.jupiter.api.Test
    void removeTicketingSalePoint() {
        TicketingSalePoint ticketingSalePoint1 = new TicketingSalePoint("TVM_BUC_OBR", "Ticketing Machine");
        registrationSystem.addTicketingSalePoint(ticketingSalePoint1);
        TicketingSalePoint result = registrationSystem.removeTicketingSalePoint("TVM_BUC_OBR");
        assertEquals(ticketingSalePoint1.getId(),result.getId());
        assertEquals(ticketingSalePoint1.getType(),result.getType());
        assertEquals(ticketingSalePoint1.getSoldTickets(),result.getSoldTickets());
        assertNull(registrationSystem.findTicketingSalePoint("TVM_BUC_OBR"));
    }

    @org.junit.jupiter.api.Test
    void updateTicketingSalePoint() {
        TicketingSalePoint ticketingSalePoint1 = new TicketingSalePoint("void_TVM_BUC_OBR", "Ticketing Machine");
        registrationSystem.addTicketingSalePoint(ticketingSalePoint1);
        ticketingSalePoint1.setType("Ticketing Centre");
        registrationSystem.updateTicketingSalePoint(ticketingSalePoint1,"void_TVM_BUC_OBR");
        TicketingSalePoint result = registrationSystem.findTicketingSalePoint("void_TVM_BUC_OBR");
        assertEquals(ticketingSalePoint1.getId(),result.getId());
        assertEquals(ticketingSalePoint1.getType(), result.getType());
        assertEquals(ticketingSalePoint1.getSoldTickets(), result.getSoldTickets());
        registrationSystem.removeTicketingSalePoint("void_TVM_BUC_OBR");
    }

    @org.junit.jupiter.api.Test
    void findTicketingSalePoint() {
        TicketingSalePoint temp = new TicketingSalePoint("DNA_GHICA", "Ticketing Centre");
        registrationSystem.addTicketingSalePoint(temp);
        TicketingSalePoint result = registrationSystem.findTicketingSalePoint("DNA_GHICA");
        assertEquals(temp.getId(),result.getId());
        assertEquals(temp.getType(), result.getType());
        assertEquals(temp.getSoldTickets(), result.getSoldTickets());
        registrationSystem.removeTicketingSalePoint("DNA_GHICA");
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
        Customer user2 = new Customer("void_Testing", "1");
        registrationSystem.addUser(user2);
        Customer result=registrationSystem.removeUser("void_Testing");

        assertEquals(result.getUsername(),user2.getUsername());
        assertNull(registrationSystem.findUser("void_Testing"));

    }

    @org.junit.jupiter.api.Test
    void updateUser() {


        Customer user3 = new Customer("geani_dabu_clona", "2");
        registrationSystem.addUser(user3);

        registrationSystem.updateUser(user3,"geani_dabu_clona");
        Customer result=registrationSystem.findUser("geani_dabu_clona");
        assertEquals(result.getUsername(),user3.getUsername());
        registrationSystem.removeUser("geani_dabu_clona");

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
        Depot depot = new Depot("void_testing");

        Vehicle bus = new DieselVehicle("void_WEB62809123456788", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, depot);
        Vehicle tram = new ElectricVehicle("void_359", "Electroputere Craiova", "V3A-93", 1999, 300, "Tram", 65, depot);

        depot.addVehicle(bus);
        depot.addVehicle(tram);

        registrationSystem.addVehicle(bus);
        registrationSystem.addVehicle(tram);
        registrationSystem.addDepot(depot);

        registrationSystem.removeDepot("void_testing");
        registrationSystem.removeVehicle("void_359");
        assertNull(registrationSystem.findVehicle(tram.getVin()));
        assertNotNull(registrationSystem.findVehicle(bus.getVin()));
        registrationSystem.removeVehicle("void_WEB62809123456788");
    }


    @org.junit.jupiter.api.Test
    void updateVehicle()
    {
        Depot depot = new Depot("void_testing");
        Vehicle bus = new DieselVehicle("void_WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, depot);
        registrationSystem.addVehicle(bus);
        registrationSystem.addDepot(depot);
        bus.setInMaintenance(true);
        registrationSystem.updateVehicle(bus, "void_WEB62809123456789");
        assertTrue(registrationSystem.findVehicle("void_WEB62809123456789").isInMaintenance());
        registrationSystem.removeDepot("void_testing");
        registrationSystem.removeVehicle("void_WEB62809123456789");
    }



    @org.junit.jupiter.api.Test
    void findVehicle()
    {
        Depot depot = new Depot("void_testing");

        Vehicle bus = new DieselVehicle("void_WEB62809123456789", "Mercedes-Benz", "Citaro (O530.09)", 2008, 180, "Bus", 4, depot);

        registrationSystem.addVehicle(bus);
        registrationSystem.addDepot(depot);
        Vehicle result = registrationSystem.findVehicle("void_WEB62809123456789");
        assertNotNull(result);
        registrationSystem.removeVehicle("void_WEB62809123456789");
        registrationSystem.removeDepot("void_testing");
    }

    @org.junit.jupiter.api.Test
    void login()
    {
        Customer user1 = new Customer("ionel12", "parola");
        registrationSystem.login(user1.getUsername(), user1.getPassword());
    }


}
