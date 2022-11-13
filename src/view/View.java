package view;

import controller.RegistrationSystem;
import jdk.jshell.spi.ExecutionControl;
import model.data.*;
import repository.interfaces.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class View {
    private static View single_instance = null;
    final private RegistrationSystem controller;
    private UserType loggedInUserType;
    private String username;

    private View(){
        this.loggedInUserType = null;
        this.controller = RegistrationSystem.getInstance();
    }

    public static View getInstance(){
        if(single_instance == null)
            single_instance = new View();
        return  single_instance;
    }

    private void logIn(){
        String password;
        do {
            System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
            System.out.println("Logging in");
            Scanner in = new Scanner(System.in);
            System.out.print("username: ");
            this.username = in.nextLine();
            System.out.print("password: ");
            password = in.nextLine();
            this.loggedInUserType = controller.login(username, password);
        } while (this.loggedInUserType == null);
        System.out.println("Successfully logged in as: " + username);
    }

    private void signUp(){
        System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
        System.out.println("Signing up");
        String password;
        while(true) {
            Scanner in = new Scanner(System.in);
            System.out.print("username: ");
            this.username = in.nextLine();
            System.out.print("password: ");
            password = in.nextLine();
            User user = new User(username, password);
            if(controller.addUser(user))
                break;
            System.out.println("\nUsername already taken, try again");
        }
        System.out.println("Successfully created account: " + username);
        System.out.println("Successfully logged in as: " + username);
    }

    private void setUpInMemory(){
        DepotRepository depotRepository = new repository.inMemoryRepository.DepotRepository();
        EmployeeRepository employeeRepository=new repository.inMemoryRepository.EmployeeRepository();
        LineRepository lineRepository=new repository.inMemoryRepository.LineRepository();
        ProgramRepository programRepository=new repository.inMemoryRepository.ProgramRepository();
        StationRepository stationRepository=new repository.inMemoryRepository.StationRepository();
        TicketingSalePointRepository ticketingSalePointRepository=new repository.inMemoryRepository.TicketingSalePointRepository();
        UserRepository userRepository=new repository.inMemoryRepository.UserRepository();
        VehicleRepository vehicleRepository=new repository.inMemoryRepository.VehicleRepository();
        TicketTypeRepository ticketTypeRepository = new repository.inMemoryRepository.TicketTypeRepository();
        TicketRepository ticketRepository = new repository.inMemoryRepository.TicketRepository();

        controller.setDepotRepository(depotRepository);
        controller.setEmployeeRepository(employeeRepository);
        controller.setLineRepository(lineRepository);
        controller.setProgramRepository(programRepository);
        controller.setStationRepository(stationRepository);
        controller.setTicketingSalePointRepository(ticketingSalePointRepository);
        controller.setUserRepository(userRepository);
        controller.setVehicleRepository(vehicleRepository);
        controller.setTicketTypeRepository(ticketTypeRepository);
        controller.setTicketRepository(ticketRepository);
    }

    private void viewFares(){
        System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
        List<Ticket> tickets = this.controller.getUserFares(username);
        for(TicketType type : this.controller.getTicketTypes()){
            int count = 0;
            for(Ticket ticket : tickets){
                if(ticket.getType().equals(type.getType())){
                    count++;
                }
            }
            System.out.println(type.getType() + " : " + count);
        }
        System.out.println("Press enter to return");
        Scanner in = new Scanner(System.in);
        in.nextLine();
    }

    private void buyFare(){
        System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
        System.out.println("Available fares");
        int num = 1;
        for(TicketType type : this.controller.getTicketTypes()){
            System.out.println(num + " - " + type.getType() + "\t" + type.getValue() + " RON");
            num++;
        }
        Scanner in = new Scanner(System.in);
        int value = in.nextInt();
        if(value == 1){
            System.out.println("How many?");
            int amount = in.nextInt();
            int nextId = this.controller.getNextTicketID();
            for(int i=0; i<amount; i++){
                this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(0).getValue(), this.controller.getTicketTypes().get(0).getType(), nextId, LocalDate.now()));
                this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(0).getValue(), this.controller.getTicketTypes().get(0).getType(), nextId, LocalDate.now()));
                nextId++;
            }
        } else if (value == 2) {
            int nextId = this.controller.getNextTicketID();
            this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(1).getValue(), this.controller.getTicketTypes().get(1).getType(), nextId, LocalDate.now()));
            this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(1).getValue(), this.controller.getTicketTypes().get(1).getType(), nextId, LocalDate.now()));
        } else if (value == 3) {
            int nextId = this.controller.getNextTicketID();
            this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(2).getValue(), this.controller.getTicketTypes().get(2).getType(), nextId, LocalDate.now()));
            this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(2).getValue(), this.controller.getTicketTypes().get(2).getType(), nextId, LocalDate.now()));
        } else if (value == 4) {
            int nextId = this.controller.getNextTicketID();
            this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(3).getValue(), this.controller.getTicketTypes().get(3).getType(), nextId, LocalDate.now()));
            this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(3).getValue(), this.controller.getTicketTypes().get(3).getType(), nextId, LocalDate.now()));
        } else if (value == 5) {
            int nextId = this.controller.getNextTicketID();
            this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(4).getValue(), this.controller.getTicketTypes().get(4).getType(), nextId, LocalDate.now()));
            this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(4).getValue(), this.controller.getTicketTypes().get(4).getType(), nextId, LocalDate.now()));
        } else if (value == 6) {
            int nextId = this.controller.getNextTicketID();
            this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(5).getValue(), this.controller.getTicketTypes().get(5).getType(), nextId, LocalDate.now()));
            this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(5).getValue(), this.controller.getTicketTypes().get(5).getType(), nextId, LocalDate.now()));
        } else if (value == 7) {
            int nextId = this.controller.getNextTicketID();
            this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(6).getValue(), this.controller.getTicketTypes().get(6).getType(), nextId, LocalDate.now()));
            this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(6).getValue(), this.controller.getTicketTypes().get(6).getType(), nextId, LocalDate.now()));
        }
    }

    private void useTicket(){
        Scanner lineScanner = new Scanner(System.in);
        System.out.println("Use ticket on line: ");
        String line = lineScanner.nextLine();
        for(Ticket ticket : this.controller.getUserFares(username)){
            if(ticket.getType().equals("Abonament 12 luni")){
                long year = ChronoUnit.YEARS.between(ticket.getBuyDate(), LocalDate.now());
                if(year < 1){
                    this.controller.useTicketOnLine(ticket,line);
                    return;
                } else {
                    this.controller.delTicketFromUser(username, ticket);
                }
            }
        }
        for(Ticket ticket : this.controller.getUserFares(username)){
            if(ticket.getType().equals("Abonament 6 luni")){
                long months = ChronoUnit.MONTHS.between(ticket.getBuyDate(), LocalDate.now());
                if(months < 6){
                    this.controller.useTicketOnLine(ticket,line);
                    return;
                } else {
                    this.controller.delTicketFromUser(username, ticket);
                }
            }
        }
        for(Ticket ticket : this.controller.getUserFares(username)){
            if(ticket.getType().equals("Abonament 1 luna")){
                long months = ChronoUnit.MONTHS.between(ticket.getBuyDate(), LocalDate.now());
                if(months < 1){
                    this.controller.useTicketOnLine(ticket,line);
                    return;
                } else {
                    this.controller.delTicketFromUser(username, ticket);
                }
            }
        }
        for(Ticket ticket : this.controller.getUserFares(username)){
            if(ticket.getType().equals("Abonament 1 sapt")){
                long weeks = ChronoUnit.WEEKS.between(ticket.getBuyDate(), LocalDate.now());
                if(weeks < 1){
                    this.controller.useTicketOnLine(ticket,line);
                    return;
                } else {
                    this.controller.delTicketFromUser(username, ticket);
                }
            }
        }
        for(Ticket ticket : this.controller.getUserFares(username)){
            if(ticket.getType().equals("Abonament 72h")){
                long days = ChronoUnit.DAYS.between(ticket.getBuyDate(), LocalDate.now());
                if(days < 3){
                    this.controller.useTicketOnLine(ticket,line);
                    return;
                } else {
                    this.controller.delTicketFromUser(username, ticket);
                }
            }
        }
        for(Ticket ticket : this.controller.getUserFares(username)){
            if(ticket.getType().equals("Abonament 24h")){
                long days = ChronoUnit.DAYS.between(ticket.getBuyDate(), LocalDate.now());
                if(days < 1){
                    this.controller.useTicketOnLine(ticket,line);
                    return;
                } else {
                    this.controller.delTicketFromUser(username, ticket);
                }
            }
        }
        for(Ticket ticket : this.controller.getUserFares(username)){
            if(ticket.getType().equals("Bilet")){
                this.controller.useTicketOnLine(ticket,line);
                this.controller.delTicketFromUser(username, ticket);
            }
        }
    }

    private void maintenanceVehicles() {
        while(true){
            System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
            System.out.println("1 - Find vehicle by VIN");
            System.out.println("2 - Find vehicles by Park Number");
            System.out.println("3 - Find vehicles in maintenance");
            System.out.println("4 - Sort vehicles by Park Number");
            System.out.println("5 - Sort vehicles by Build Year");
            System.out.println("6 - Update Vehicle");
            System.out.println("7 - Return");
            Scanner in = new Scanner(System.in);
            int answer = in.nextInt();
            if (answer == 1) {
                System.out.print("VIN: ");
                Scanner in2 = new Scanner(System.in);
                String VIN = in2.nextLine();
                System.out.println(this.controller.findVehicle(VIN));
                System.out.println("Press enter to return");
                in2.nextLine();
            } else if (answer == 2) {
                System.out.print("Park Number: ");
                Scanner in2 = new Scanner(System.in);
                String parkNumber = in2.nextLine();
                System.out.println(this.controller.findByParkNumber(parkNumber));
                System.out.println("Press enter to return");
                in2.nextLine();
            } else if (answer == 3) {
                System.out.print("Show vehicles in maintenance? (y/n)");
                Scanner in2 = new Scanner(System.in);
                String ans = in2.nextLine();
                if(ans.equals("y")){
                    List<Vehicle> vehicles =  this.controller.filterInMaintenance(true);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                } else if (ans.equals("n")) {
                    List<Vehicle> vehicles = this.controller.filterInMaintenance(false);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                }
                Scanner in3 = new Scanner(System.in);
                System.out.println("Press enter to return");
                in3.nextLine();
            } else if (answer == 4) {
                System.out.print("Ascending? (y/n)");
                Scanner in2 = new Scanner(System.in);
                String ans = in2.nextLine();
                if(ans.equals("y")){
                    List<Vehicle> vehicles = this.controller.sortVehByParkNumber(true);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                } else if (ans.equals("n")) {
                    List<Vehicle> vehicles = this.controller.sortVehByParkNumber(false);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                }
                Scanner in3 = new Scanner(System.in);
                System.out.println("Press enter to return");
                in3.nextLine();
            } else if (answer == 5) {
                System.out.print("Ascending? (y/n)");
                Scanner in2 = new Scanner(System.in);
                String ans = in2.nextLine();
                if(ans.equals("y")){
                    List<Vehicle> vehicles = this.controller.sortVehByYear(true);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                } else if (ans.equals("n")) {
                    List<Vehicle> vehicles = this.controller.sortVehByYear(false);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                }
                Scanner in3 = new Scanner(System.in);
                System.out.println("Press enter to return");
                in3.nextLine();
            } else if (answer == 6) {
                System.out.println("What vehicle should be edited?");
                System.out.print("VIN: ");
                Scanner updateInfo = new Scanner(System.in);
                String VIN = updateInfo.nextLine();
                Vehicle vehicle = this.controller.findVehicle(VIN);
                ElectricVehicle copy = new ElectricVehicle(vehicle.getVin(), vehicle.getMake(), vehicle.getModel(), vehicle.getBuilt(), vehicle.getCapacity(), "invalid", 0);
                boolean usingCopy = false;
                if(vehicle instanceof DieselVehicle){
                    System.out.println("Is this vehicle being converted to electric? (y/n)");
                    Scanner in2 = new Scanner(System.in);
                    String ans = in2.nextLine();
                    if (ans.equals("y")){
                        usingCopy = true;
                    }
                }
                System.out.println("Input k to keep following details");
                System.out.println("ParkNumber: " + vehicle.getParkNumber());
                String input = updateInfo.nextLine();
                if(!input.equals("k")){
                    if(usingCopy){
                        copy.setParkNumber(input);
                    } else {
                        vehicle.setParkNumber(input);
                    }
                }
                System.out.println("Make: " + vehicle.getMake());
                input = updateInfo.nextLine();
                if(!input.equals("k")){
                    if(usingCopy){
                        copy.setMake(input);
                    } else {
                        vehicle.setMake(input);
                    }
                }
                System.out.println("Model: " + vehicle.getModel());
                input = updateInfo.nextLine();
                if(!input.equals("k")){
                    if(usingCopy){
                        copy.setModel(input);
                    } else {
                        vehicle.setModel(input);
                    }
                }
                System.out.println("Built: " + vehicle.getBuilt());
                input = updateInfo.nextLine();
                if(!input.equals("k")){
                    if(usingCopy){
                        copy.setBuilt(Integer.parseInt(input));
                    } else {
                        vehicle.setBuilt(Integer.parseInt(input));
                    }
                }
                System.out.println("Capacity: " + vehicle.getCapacity());
                input = updateInfo.nextLine();
                if(!input.equals("k")){
                    if(usingCopy){
                        copy.setCapacity(Integer.parseInt(input));
                    } else {
                        vehicle.setCapacity(Integer.parseInt(input));
                    }
                }
                if ((vehicle instanceof ElectricVehicle ev)){
                    System.out.println("Type: " + ev.getType());
                    Scanner electric = new Scanner(System.in);
                    String ans = electric.nextLine();
                    if(!ans.equals("k")){
                        ev.setType(ans);
                    }
                    System.out.println("Electric eff: " + ev.getElectricEfficiency());
                    ans = electric.nextLine();
                    if(!ans.equals("k")){
                        ev.setElectricEfficiency(Integer.parseInt(ans));
                    }
                    this.controller.updateVehicle(ev,VIN);
                } else if (vehicle instanceof DieselVehicle dv) {
                    if(!usingCopy) {
                        System.out.println("Type: " + dv.getType());
                        Scanner electric = new Scanner(System.in);
                        String ans = electric.nextLine();
                        if (!ans.equals("k")) {
                            dv.setType(ans);
                        }
                        System.out.println("Euronorm: " + dv.getEuronorm());
                        ans = electric.nextLine();
                        if (!ans.equals("k")) {
                            dv.setEuronorm(Integer.parseInt(ans));
                        }
                        this.controller.updateVehicle(dv,VIN);
                    } else {
                        System.out.println("Type: " + copy.getType());
                        Scanner electric = new Scanner(System.in);
                        String ans = electric.nextLine();
                        if (!ans.equals("k")) {
                            copy.setType(ans);
                        }
                        System.out.println("Electric Eff: " + copy.getElectricEfficiency());
                        ans = electric.nextLine();
                        if (!ans.equals("k")) {
                            copy.setElectricEfficiency(Integer.parseInt(ans));
                        }
                        copy.setParkNumber(vehicle.getParkNumber());
                        this.controller.removeVehicle(VIN);
                        this.controller.addVehicle(copy);
                    }
                }

            } else if (answer == 7) {
                break;
            }
        }
    }

    private void directorVehicles(){
        while(true){
            System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
            System.out.println("1 - Find vehicle by VIN");
            System.out.println("2 - Find vehicles by Park Number");
            System.out.println("3 - Find vehicles in maintenance");
            System.out.println("4 - Sort vehicles by Park Number");
            System.out.println("5 - Sort vehicles by Build Year");
            System.out.println("6 - Update Vehicle");
            System.out.println("7 - Add Vehicle");
            System.out.println("8 - Remove Vehicle");
            System.out.println("9 - Return");
            Scanner in = new Scanner(System.in);
            int answer = in.nextInt();
            if (answer == 1) {
                System.out.print("VIN: ");
                Scanner in2 = new Scanner(System.in);
                String VIN = in2.nextLine();
                System.out.println(this.controller.findVehicle(VIN));
                System.out.println("Press enter to return");
                in2.nextLine();
            } else if (answer == 2) {
                System.out.print("Park Number: ");
                Scanner in2 = new Scanner(System.in);
                String parkNumber = in2.nextLine();
                System.out.println(this.controller.findByParkNumber(parkNumber));
                System.out.println("Press enter to return");
                in2.nextLine();
            } else if (answer == 3) {
                System.out.print("Show vehicles in maintenance? (y/n)");
                Scanner in2 = new Scanner(System.in);
                String ans = in2.nextLine();
                if(ans.equals("y")){
                    List<Vehicle> vehicles =  this.controller.filterInMaintenance(true);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                } else if (ans.equals("n")) {
                    List<Vehicle> vehicles = this.controller.filterInMaintenance(false);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                }
                Scanner in3 = new Scanner(System.in);
                System.out.println("Press enter to return");
                in3.nextLine();
            } else if (answer == 4) {
                System.out.print("Ascending? (y/n)");
                Scanner in2 = new Scanner(System.in);
                String ans = in2.nextLine();
                if(ans.equals("y")){
                    List<Vehicle> vehicles = this.controller.sortVehByParkNumber(true);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                } else if (ans.equals("n")) {
                    List<Vehicle> vehicles = this.controller.sortVehByParkNumber(false);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                }
                Scanner in3 = new Scanner(System.in);
                System.out.println("Press enter to return");
                in3.nextLine();
            } else if (answer == 5) {
                System.out.print("Ascending? (y/n)");
                Scanner in2 = new Scanner(System.in);
                String ans = in2.nextLine();
                if(ans.equals("y")){
                    List<Vehicle> vehicles = this.controller.sortVehByYear(true);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                } else if (ans.equals("n")) {
                    List<Vehicle> vehicles = this.controller.sortVehByYear(false);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                }
                Scanner in3 = new Scanner(System.in);
                System.out.println("Press enter to return");
                in3.nextLine();
            } else if (answer == 6) {
                System.out.println("What vehicle should be edited?");
                System.out.print("VIN: ");
                Scanner updateInfo = new Scanner(System.in);
                String VIN = updateInfo.nextLine();
                Vehicle vehicle = this.controller.findVehicle(VIN);
                ElectricVehicle copy = new ElectricVehicle(vehicle.getVin(), vehicle.getMake(), vehicle.getModel(), vehicle.getBuilt(), vehicle.getCapacity(), "invalid", 0);
                boolean usingCopy = false;
                if(vehicle instanceof DieselVehicle){
                    System.out.println("Is this vehicle being converted to electric? (y/n)");
                    Scanner in2 = new Scanner(System.in);
                    String ans = in2.nextLine();
                    if (ans.equals("y")){
                        usingCopy = true;
                    }
                }
                System.out.println("Input k to keep following details");
                System.out.println("ParkNumber: " + vehicle.getParkNumber());
                String input = updateInfo.nextLine();
                if(!input.equals("k")){
                    if(usingCopy){
                        copy.setParkNumber(input);
                    } else {
                        vehicle.setParkNumber(input);
                    }
                }
                System.out.println("Make: " + vehicle.getMake());
                input = updateInfo.nextLine();
                if(!input.equals("k")){
                    if(usingCopy){
                        copy.setMake(input);
                    } else {
                        vehicle.setMake(input);
                    }
                }
                System.out.println("Model: " + vehicle.getModel());
                input = updateInfo.nextLine();
                if(!input.equals("k")){
                    if(usingCopy){
                        copy.setModel(input);
                    } else {
                        vehicle.setModel(input);
                    }
                }
                System.out.println("Built: " + vehicle.getBuilt());
                input = updateInfo.nextLine();
                if(!input.equals("k")){
                    if(usingCopy){
                        copy.setBuilt(Integer.parseInt(input));
                    } else {
                        vehicle.setBuilt(Integer.parseInt(input));
                    }
                }
                System.out.println("Capacity: " + vehicle.getCapacity());
                input = updateInfo.nextLine();
                if(!input.equals("k")){
                    if(usingCopy){
                        copy.setCapacity(Integer.parseInt(input));
                    } else {
                        vehicle.setCapacity(Integer.parseInt(input));
                    }
                }
                if ((vehicle instanceof ElectricVehicle ev)){
                    System.out.println("Type: " + ev.getType());
                    Scanner electric = new Scanner(System.in);
                    String ans = electric.nextLine();
                    if(!ans.equals("k")){
                        ev.setType(ans);
                    }
                    System.out.println("Electric eff: " + ev.getElectricEfficiency());
                    ans = electric.nextLine();
                    if(!ans.equals("k")){
                        ev.setElectricEfficiency(Integer.parseInt(ans));
                    }
                    this.controller.updateVehicle(ev,VIN);
                } else if (vehicle instanceof DieselVehicle dv) {
                    if(!usingCopy) {
                        System.out.println("Type: " + dv.getType());
                        Scanner electric = new Scanner(System.in);
                        String ans = electric.nextLine();
                        if (!ans.equals("k")) {
                            dv.setType(ans);
                        }
                        System.out.println("Euronorm: " + dv.getEuronorm());
                        ans = electric.nextLine();
                        if (!ans.equals("k")) {
                            dv.setEuronorm(Integer.parseInt(ans));
                        }
                        this.controller.updateVehicle(dv,VIN);
                    } else {
                        System.out.println("Type: " + copy.getType());
                        Scanner electric = new Scanner(System.in);
                        String ans = electric.nextLine();
                        if (!ans.equals("k")) {
                            copy.setType(ans);
                        }
                        System.out.println("Electric Eff: " + copy.getElectricEfficiency());
                        ans = electric.nextLine();
                        if (!ans.equals("k")) {
                            copy.setElectricEfficiency(Integer.parseInt(ans));
                        }
                        copy.setParkNumber(vehicle.getParkNumber());
                        this.controller.removeVehicle(VIN);
                        this.controller.addVehicle(copy);
                    }
                }

            } else if (answer == 7) {
                System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
                System.out.println("Adding Vehicle...");
                System.out.println("Is the vehicle a diesel or electric?");
                System.out.println("Type 'DIESEL' or 'ELECTRIC'");
                Scanner input = new Scanner(System.in);
                String ans = input.nextLine();
                if (ans.equals("DIESEL")){
                    Scanner vehicleInput = new Scanner(System.in);
                    System.out.print("VIN: ");
                    String VIN = vehicleInput.nextLine();
                    System.out.print("parkNumber: ");
                    String parkNum = vehicleInput.nextLine();
                    System.out.print("make: ");
                    String make = vehicleInput.nextLine();
                    System.out.print("model: ");
                    String model = vehicleInput.nextLine();
                    System.out.print("built: ");
                    int built = vehicleInput.nextInt();
                    System.out.print("capacity: ");
                    int cap = vehicleInput.nextInt();
                    System.out.print("type: ");
                    String type = vehicleInput.nextLine();
                    System.out.print("euronorm: ");
                    int euronorm = vehicleInput.nextInt();
                    DieselVehicle dv = new DieselVehicle(VIN, make, model, built, cap, type, euronorm);
                    dv.setParkNumber(parkNum);
                    this.controller.addVehicle(dv);
                } else if (ans.equals("ELECTRIC")) {
                    Scanner vehicleInput = new Scanner(System.in);
                    System.out.print("VIN: ");
                    String VIN = vehicleInput.nextLine();
                    System.out.print("parkNumber: ");
                    String parkNum = vehicleInput.nextLine();
                    System.out.print("make: ");
                    String make = vehicleInput.nextLine();
                    System.out.print("model: ");
                    String model = vehicleInput.nextLine();
                    System.out.print("built: ");
                    int built = vehicleInput.nextInt();
                    System.out.print("capacity: ");
                    int cap = vehicleInput.nextInt();
                    System.out.print("type: ");
                    String type = vehicleInput.nextLine();
                    System.out.print("electricEfficiency: ");
                    int eff = vehicleInput.nextInt();
                    ElectricVehicle ev = new ElectricVehicle(VIN, make, model, built, cap, type, eff);
                    ev.setParkNumber(parkNum);
                    this.controller.addVehicle(ev);
                }
            } else if (answer == 8) {
                System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
                System.out.println("What vehicle should be removed?");
                System.out.print("VIN: ");
                Scanner ans = new Scanner(System.in);
                this.controller.removeVehicle(ans.nextLine());
            } else if (answer == 9) {
                break;
            }
        }
    }

    private void directorDepots() {
        while(true){
            System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
            System.out.println("1 - Find depot");
            System.out.println("2 - Add depot");
            System.out.println("3 - Remove depot");
            System.out.println("4 - Sort depots by name");
            System.out.println("5 - Move vehicle");
            System.out.println("6 - Return");
            Scanner in = new Scanner(System.in);
            int answer = in.nextInt();
            if(answer == 1){
                Scanner input = new Scanner(System.in);
                String name = input.nextLine();
                System.out.println(this.controller.findDepot(name));
            } else if (answer == 2) {
                Scanner input = new Scanner(System.in);
                String name = input.nextLine();
                this.controller.addDepot(new Depot(name));
            } else if (answer == 3) {
                Scanner input = new Scanner(System.in);
                String name = input.nextLine();
                this.controller.removeDepot(name);
            } else if (answer == 4) {
                Scanner input = new Scanner(System.in);
                System.out.println("Ascending? (y/n)");
                String ans = input.nextLine();
                if (ans.equals("y")){
                    List<Depot> temp = this.controller.sortDepots(true);
                    for(Depot depot:temp){
                        System.out.println(depot);
                    }
                } else if (ans.equals("n")){
                    List<Depot> temp = this.controller.sortDepots(false);
                    for(Depot depot:temp){
                        System.out.println(depot);
                    }
                }
            } else if (answer == 5) {
                Scanner input = new Scanner(System.in);
                System.out.println("Depot from where to move: ");
                String oldDepotName = input.nextLine();
                System.out.println("Depot where to move to: ");
                String newDepotName = input.nextLine();
                System.out.println("VIN of vehicle to be moved: ");
                String vin = input.nextLine();
                this.controller.moveVehicle(oldDepotName, newDepotName, vin);
            } else if (answer == 6) {
                break;
            }
        }
    }

    private void directorLines(){
        while(true){
            System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
            System.out.println("1 - Find line");
            System.out.println("2 - Add line");
            System.out.println("3 - Remove line");
            System.out.println("4 - Return");
            Scanner in = new Scanner(System.in);
            int answer = in.nextInt();
            if (answer == 1){
                Scanner input = new Scanner(System.in);
                System.out.print("Line number: ");
                String lineNum = input.nextLine();
                System.out.println(this.controller.findLine(lineNum));
            } else if (answer == 2) {
                Scanner input = new Scanner(System.in);
                System.out.print("Line number: ");
                String lineNum = input.nextLine();
                System.out.print("Type: ");
                String type = input.nextLine();
                System.out.print("Special requirement: ");
                String sReq = input.nextLine();
                this.controller.addLine(new Line(lineNum,type,sReq));
            } else if (answer == 3) {
                Scanner input = new Scanner(System.in);
                System.out.print("Line number: ");
                String lineNum = input.nextLine();
                this.controller.removeLine(lineNum);
            } else if (answer == 4) {
                break;
            }
        }
    }

    private void dispatcherVehicles() {
        while (true) {
            System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
            System.out.println("1 - Find vehicle by VIN");
            System.out.println("2 - Find vehicles by Park Number");
            System.out.println("3 - Find vehicles in maintenance");
            System.out.println("4 - Sort vehicles by Park Number");
            System.out.println("5 - Sort vehicles by Build Year");
            System.out.println("6 - Move Vehicle");
            System.out.println("7 - Return");
            Scanner in = new Scanner(System.in);
            int answer = in.nextInt();
            if (answer == 1) {
                System.out.print("VIN: ");
                Scanner in2 = new Scanner(System.in);
                String VIN = in2.nextLine();
                System.out.println(this.controller.findVehicle(VIN));
                System.out.println("Press enter to return");
                in2.nextLine();
            } else if (answer == 2) {
                System.out.print("Park Number: ");
                Scanner in2 = new Scanner(System.in);
                String parkNumber = in2.nextLine();
                System.out.println(this.controller.findByParkNumber(parkNumber));
                System.out.println("Press enter to return");
                in2.nextLine();
            } else if (answer == 3) {
                System.out.print("Show vehicles in maintenance? (y/n)");
                Scanner in2 = new Scanner(System.in);
                String ans = in2.nextLine();
                if(ans.equals("y")){
                    List<Vehicle> vehicles =  this.controller.filterInMaintenance(true);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                } else if (ans.equals("n")) {
                    List<Vehicle> vehicles = this.controller.filterInMaintenance(false);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                }
                Scanner in3 = new Scanner(System.in);
                System.out.println("Press enter to return");
                in3.nextLine();
            } else if (answer == 4) {
                System.out.print("Ascending? (y/n)");
                Scanner in2 = new Scanner(System.in);
                String ans = in2.nextLine();
                if(ans.equals("y")){
                    List<Vehicle> vehicles = this.controller.sortVehByParkNumber(true);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                } else if (ans.equals("n")) {
                    List<Vehicle> vehicles = this.controller.sortVehByParkNumber(false);
                    for(Vehicle vehicle : vehicles){
                        System.out.println(vehicle.toString());
                    }
                }
                Scanner in3 = new Scanner(System.in);
                System.out.println("Press enter to return");
                in3.nextLine();
            } else if (answer == 5) {
                System.out.print("Ascending? (y/n)");
                Scanner in2 = new Scanner(System.in);
                String ans = in2.nextLine();
                if (ans.equals("y")) {
                    List<Vehicle> vehicles = this.controller.sortVehByYear(true);
                    for (Vehicle vehicle : vehicles) {
                        System.out.println(vehicle.toString());
                    }
                } else if (ans.equals("n")) {
                    List<Vehicle> vehicles = this.controller.sortVehByYear(false);
                    for (Vehicle vehicle : vehicles) {
                        System.out.println(vehicle.toString());
                    }
                }
                Scanner in3 = new Scanner(System.in);
                System.out.println("Press enter to return");
                in3.nextLine();
            } else if (answer == 6) {
                Scanner input = new Scanner(System.in);
                System.out.println("Depot from where to move: ");
                String oldDepotName = input.nextLine();
                System.out.println("Depot where to move to: ");
                String newDepotName = input.nextLine();
                System.out.println("VIN of vehicle to be moved: ");
                String vin = input.nextLine();
                this.controller.moveVehicle(oldDepotName, newDepotName, vin);
            } else if (answer == 7) {
                break;
            }
        }
    }

    private void dispatcherStations(){
        while (true) {
            System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
            System.out.println("1 - Add station");
            System.out.println("2 - Remove station");
            System.out.println("3 - Find stations with name");
            System.out.println("4 - Sort stations by name");
            System.out.println("5 - Update station");
            System.out.println("6 - Return");
            Scanner in = new Scanner(System.in);
            int answer = in.nextInt();
            if (answer == 1) {
                System.out.println("Name: ");
                Scanner input = new Scanner(System.in);
                String name = input.nextLine();
                System.out.println("Address: ");
                String address = input.nextLine();
                this.controller.addStation(new Station(this.controller.getNextStationID(), name, address));
            } else if (answer == 2) {
                System.out.print("Station ID: ");
                Scanner input = new Scanner(System.in);
                int id = input.nextInt();
                this.controller.removeStation(id);
            } else if (answer == 3) {
                System.out.println("Name: ");
                Scanner input = new Scanner(System.in);
                String name = input.nextLine();
                List<Station> result = this.controller.filterStationByName(name);
                for(Station station:result){
                    System.out.println(station);
                }
                System.out.println("Press enter to return");
                Scanner temp = new Scanner(System.in);
                temp.nextLine();
            } else if (answer == 4) {
                System.out.println("Ascending? (y/n)");
                Scanner input = new Scanner(System.in);
                String ans = input.nextLine();
                List<Station> result = new ArrayList<>();
                if(ans.equals("y")){
                    result = this.controller.sortStationsByName(true);
                } else if (ans.equals("n")) {
                    result = this.controller.sortStationsByName(false);
                }
                for(Station station:result){
                    System.out.println(station);
                }
                System.out.println("Press enter to return");
                Scanner temp = new Scanner(System.in);
                temp.nextLine();
            } else if (answer == 5) {
                Scanner input = new Scanner(System.in);
                System.out.println("What station should be edited?");
                System.out.print("Station ID: ");
                int sid = input.nextInt();
                Station oldStation = this.controller.findStation(sid);
                System.out.println("Input k to keep the values");
                System.out.println("Station Name: " + oldStation.getName());
                String ans = input.nextLine();
                if(!ans.equals("k")){
                    oldStation.setName(ans);
                }
                System.out.println("Address: " + oldStation.getAddress());
                ans = input.nextLine();
                if(!ans.equals("k")){
                    oldStation.setAddress(ans);
                }
                this.controller.updateStation(oldStation,sid);
            } else if (answer == 6) {
                break;
            }
        }
    }

    private void dispatcherLines(){
        while (true) {
            System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
            System.out.println("1 - Filter lines by type");
            System.out.println("2 - Sort lines by number of used tickets");
            System.out.println("3 - Sort lines by line number");
            System.out.println("4 - Add a station to a line");
            System.out.println("5 - Remove a station from a line");
            System.out.println("6 - Return");
            Scanner in = new Scanner(System.in);
            int answer = in.nextInt();
            if (answer == 1) {
                System.out.println("Type: ");
                Scanner input = new Scanner(System.in);
                String type = input.nextLine();
                List<Line> result = this.controller.filterLineByType(type);
                for(Line line : result){
                    System.out.println(line);
                }
                System.out.println("Press enter to return");
                Scanner temp = new Scanner(System.in);
                temp.nextLine();
            } else if (answer == 2) {
                Scanner input = new Scanner(System.in);
                System.out.println("Ascending? (y/n)");
                String ans = input.nextLine();
                List<Line> result;
                if (ans.equals("y")){
                    result = this.controller.sortLinesByTicketUse(true);
                    for(Line line : result){
                        System.out.println(line);
                    }
                } else if (ans.equals("n")) {
                    result = this.controller.sortLinesByTicketUse(false);
                    for(Line line : result){
                        System.out.println(line);
                    }
                }
                System.out.println("Press enter to return");
                Scanner temp = new Scanner(System.in);
                temp.nextLine();
            } else if (answer == 3) {
                Scanner input = new Scanner(System.in);
                System.out.println("Ascending? (y/n)");
                String ans = input.nextLine();
                List<Line> result;
                if (ans.equals("y")){
                    result = this.controller.sortLinesByLineNum(true);
                    for(Line line : result){
                        System.out.println(line);
                    }
                } else if (ans.equals("n")) {
                    result = this.controller.sortLinesByLineNum(false);
                    for(Line line : result){
                        System.out.println(line);
                    }
                }
                System.out.println("Press enter to return");
                Scanner temp = new Scanner(System.in);
                temp.nextLine();
            } else if (answer == 4) {
                Scanner input = new Scanner(System.in);
                System.out.print("Line number: ");
                String lineNum = input.nextLine();
                System.out.print("StationId: ");
                Scanner input2 = new Scanner(System.in);
                int sid = input2.nextInt();
                this.controller.addStationToLine(sid, lineNum);
            } else if (answer == 5) {
                Scanner input = new Scanner(System.in);
                System.out.print("Line number: ");
                String lineNum = input.nextLine();
                System.out.print("StationId: ");
                Scanner input2 = new Scanner(System.in);
                int sid = input2.nextInt();
                this.controller.delStationFromLine(sid, lineNum);
            } else if (answer == 6) {
                break;
            }
        }
    }

    private void dispatcherPrograms(){
        while (true) {
            System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
            System.out.println("1 - Add Program");
            System.out.println("2 - Filter Programs by vehicle");
            System.out.println("3 - Sort Programs by line");
            System.out.println("4 - Return");
            Scanner in = new Scanner(System.in);
            int answer = in.nextInt();
            if (answer == 1) {
                Scanner input = new Scanner(System.in);
                System.out.println("VIN: ");
                String vin = input.nextLine();
                System.out.println("Line: ");
                String lineN = input.nextLine();
                System.out.println("Shift: ");
                String shift = input.nextLine();
                System.out.println("date (YYYY-MM-DD): ");
                String date = input.nextLine();
                Vehicle veh = this.controller.findVehicle(vin);
                Line line = this.controller.findLine(lineN);
                this.controller.addProgram(new Program(this.controller.getNextProgramID(), veh, line, shift, LocalDate.parse(date)));
            } else if (answer == 2) {
                Scanner input = new Scanner(System.in);
                System.out.println("Ascending? (y/n)");
                String ans = input.nextLine();
                List<Program> result;
                if (ans.equals("y")){
                    result = this.controller.sortProgramsByLine(true);
                    for(Program prog : result){
                        System.out.println(prog);
                    }
                } else if (ans.equals("n")) {
                    result = this.controller.sortProgramsByLine(false);
                    for(Program prog : result){
                        System.out.println(prog);
                    }
                }
                System.out.println("Press enter to return");
                Scanner temp = new Scanner(System.in);
                temp.nextLine();
            } else if (answer == 3) {
                Scanner input = new Scanner(System.in);
                System.out.println("Vehicle VIN: ");
                String ans = input.nextLine();
                List<Program> result = this.controller.filterProgramsByVehicle(ans);
                for(Program prog : result){
                    System.out.println(prog);
                }
                System.out.println("Press enter to return");
                Scanner temp = new Scanner(System.in);
                temp.nextLine();

            } else if (answer == 4) {
                break;
            }
        }
    }

    public void mainMenu() {
        setUpInMemory();
        while(true){
            if(this.loggedInUserType == null) {
                System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
                System.out.println("1 - Log in");
                System.out.println("2 - Sign up");
                System.out.println("3 - Exit");
                Scanner in = new Scanner(System.in);
                int answer = in.nextInt();
                if (answer == 1) {
                    logIn();
                } else if (answer == 2) {
                    signUp();
                } else if (answer == 3) {
                    break;
                }
            }
            else if(this.loggedInUserType == UserType.CUSTOMER){
                System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
                System.out.println("Logged in as " + this.username);
                System.out.println("1 - View my fares");
                System.out.println("2 - Buy fares");
                System.out.println("3 - Use fares");
                System.out.println("4 - Log out");
                Scanner in = new Scanner(System.in);
                int answer = in.nextInt();
                if (answer == 1) {
                    viewFares();
                } else if (answer == 2) {
                    buyFare();
                } else if (answer == 3) {
                    useTicket();
                } else if (answer == 4) {
                    this.loggedInUserType = null;
                }
            } else if (this.loggedInUserType == UserType.DIRECTOR) {
                System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
                System.out.println("Logged in as " + this.username);
                System.out.println("1 - Manage vehicles");
                System.out.println("2 - Manage depots");
                System.out.println("3 - Manage lines");
                System.out.println("4 - Log out");
                Scanner in = new Scanner(System.in);
                int answer = in.nextInt();
                if (answer == 1) {
                    directorVehicles();
                } else if (answer == 2) {
                    directorDepots();
                } else if (answer == 3) {
                    directorLines();
                } else if (answer == 4) {
                    this.loggedInUserType = null;
                }
            } else if (this.loggedInUserType == UserType.DISPATCHER) {
                System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
                System.out.println("Logged in as " + this.username);
                System.out.println("1 - Manage vehicles");
                System.out.println("2 - Manage lines");
                System.out.println("3 - Manage stations");
                System.out.println("4 - Manage programs");
                System.out.println("5 - Log out");
                Scanner in = new Scanner(System.in);
                int answer = in.nextInt();
                if (answer == 1) {
                    dispatcherVehicles();
                } else if (answer == 2) {
                    dispatcherLines();
                } else if (answer == 3) {
                    dispatcherStations();
                } else if (answer == 4) {
                    dispatcherPrograms();
                } else if (answer == 5) {
                    this.loggedInUserType = null;
                }
            } else if (this.loggedInUserType == UserType.MAINTENANCE) {
                System.out.println("\n\n\n\n\n\nPublic Transport Management Software v0.2");
                System.out.println("Logged in as " + this.username);
                System.out.println("1 - Manage vehicles");
                System.out.println("2 - Log out");
                Scanner in = new Scanner(System.in);
                int answer = in.nextInt();
                if (answer == 1) {
                    maintenanceVehicles();
                } else if (answer == 2) {
                    this.loggedInUserType = null;
                }

            }
        }
    }
}
