package view;

import controller.RegistrationSystem;
import jdk.jshell.spi.ExecutionControl;
import model.data.*;
import repository.interfaces.*;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class View {
    private static View single_instance = null;
    private RegistrationSystem controller;
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
                this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(0).getValue(), this.controller.getTicketTypes().get(0).getType(), nextId));
                this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(0).getValue(), this.controller.getTicketTypes().get(0).getType(), nextId));
                nextId++;
            }
        } else if (value == 2) {
            int nextId = this.controller.getNextTicketID();
            this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(1).getValue(), this.controller.getTicketTypes().get(1).getType(), nextId));
            this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(1).getValue(), this.controller.getTicketTypes().get(1).getType(), nextId));
        } else if (value == 3) {
            int nextId = this.controller.getNextTicketID();
            this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(2).getValue(), this.controller.getTicketTypes().get(2).getType(), nextId));
            this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(2).getValue(), this.controller.getTicketTypes().get(2).getType(), nextId));
        } else if (value == 4) {
            int nextId = this.controller.getNextTicketID();
            this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(3).getValue(), this.controller.getTicketTypes().get(3).getType(), nextId));
            this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(3).getValue(), this.controller.getTicketTypes().get(3).getType(), nextId));
        } else if (value == 5) {
            int nextId = this.controller.getNextTicketID();
            this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(4).getValue(), this.controller.getTicketTypes().get(4).getType(), nextId));
            this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(4).getValue(), this.controller.getTicketTypes().get(4).getType(), nextId));
        } else if (value == 6) {
            int nextId = this.controller.getNextTicketID();
            this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(5).getValue(), this.controller.getTicketTypes().get(5).getType(), nextId));
            this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(5).getValue(), this.controller.getTicketTypes().get(5).getType(), nextId));
        } else if (value == 7) {
            int nextId = this.controller.getNextTicketID();
            this.controller.addTicketToUser(username, new Ticket(this.controller.getTicketTypes().get(6).getValue(), this.controller.getTicketTypes().get(6).getType(), nextId));
            this.controller.addTicket(new Ticket(this.controller.getTicketTypes().get(6).getValue(), this.controller.getTicketTypes().get(6).getType(), nextId));
        }
    }

    public void maintenanceVehicles() {
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

    public void directorVehicles() throws ExecutionControl.NotImplementedException {
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

    public void mainMenu() throws ExecutionControl.NotImplementedException {
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
                System.out.println("3 - Log out");
                Scanner in = new Scanner(System.in);
                int answer = in.nextInt();
                if (answer == 1) {
                    viewFares();
                } else if (answer == 2) {
                    buyFare();
                } else if (answer == 3) {
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
                    throw new ExecutionControl.NotImplementedException("Function not implemented");
                } else if (answer == 3) {
                    throw new ExecutionControl.NotImplementedException("Function not implemented");
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
                    throw new ExecutionControl.NotImplementedException("Function not implemented");
                } else if (answer == 2) {
                    throw new ExecutionControl.NotImplementedException("Function not implemented");
                } else if (answer == 3) {
                    throw new ExecutionControl.NotImplementedException("Function not implemented");
                } else if (answer == 4) {
                    throw new ExecutionControl.NotImplementedException("Function not implemented");
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
