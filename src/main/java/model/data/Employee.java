package model.data;


import Verkehrbestrieb_exceptions.IncorectEmployeeRoleException;
import Verkehrbestrieb_exceptions.IncorrectCNPException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
//@Table(name = "employees")
public class Employee
{


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int EmployeeID;

    private String cnp;
    private String fullname;
    private String role;
    private String workplace;
    private int salary;

    @Getter
    @Setter
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(referencedColumnName = "vin", name = "vehicle_vin")
    private String vehicle;


    public Employee(int id, String cnp, String fullname, String role, String workplace, int salary) throws IncorrectCNPException{
        this.EmployeeID=id;
        this.cnp = cnp;
        this.vaildateCNP();
        this.fullname = fullname;
        this.role = role;
        this.workplace = workplace;
        this.salary = salary;
        this.vehicle = null;
    }

    public Employee(String cnp, String fullname, String role, String workplace, int salary) throws IncorrectCNPException
    {
        this.cnp = cnp;
        this.vaildateCNP();
        this.fullname = fullname;
        this.role = role;
        this.workplace = workplace;
        this.salary = salary;
        this.vehicle = null;
    }

    public Employee() {

    }

    public void validateRole() throws IncorectEmployeeRoleException{
        boolean correctRole = false;
        if(Objects.equals(this.role, "Director")){
            correctRole = true;
        } else if (Objects.equals(this.role, "Driver")) {
            correctRole = true;
        } else if (Objects.equals(this.role, "Mechanic")) {
            correctRole = true;
        } else if (Objects.equals(this.role, "Cleaning Personnel")) {
            correctRole = true;
        }
        if(!correctRole){
            throw new IncorectEmployeeRoleException(this.cnp +" Has invalid role");
        }
    }

    public void vaildateCNP() throws IncorrectCNPException{
        if (cnp.length() != 13) {
            throw new IncorrectCNPException(this.cnp + ": CNP doesn't the correct amount of characters");
        }

        long cod = Long.parseLong(cnp);

//        System.out.print(cod + ": ");

//        System.out.print("Sex = " + cod / 1000000000000L +" + ");

        //see if cnp is of sex/century 9 which is unassigned
        if(cod / 1000000000000L == 9) {
            throw new IncorrectCNPException(this.cnp + ": CNP contains wrong sex/century");
        }

        int year = 0;

        //see if year is closer than 18 years ago
        if(cod / 1000000000000L == 5 || cod / 1000000000000L == 6){
            long temp = cod / 10000000000L;
            temp %= 100L;
//            System.out.print("Year = " + temp + " + ");
            year = LocalDate.now().getYear() / 100;
            year -= 18;
            if(temp > year) {
                throw new IncorrectCNPException(this.cnp + ": CNP is of person too young to work");
            }
        }

        //if year is closer than 18 years ago check month/day
        if(year != 0){
            long month = cod / 100000000L;
            month %= 100L;
//            System.out.print("Month = " + month + " + ");
//            System.out.print("Current Month = " + LocalDate.now().getMonth().getValue() + " + ");
            if(month > LocalDate.now().getMonth().getValue()){
                throw new IncorrectCNPException(this.cnp + ": CNP is of person too young to work");
            } else if (month == LocalDate.now().getMonth().getValue()) {
                long day = cod / 1000000L;
                day %= 100L;
//                System.out.print("Day = " + day + " + ");
//                System.out.print("Current Day = " + LocalDate.now().getDayOfMonth() + " + ");
                if(day > LocalDate.now().getDayOfMonth()){
                    throw new IncorrectCNPException(this.cnp + ": CNP is of person too young to work");
                }
            }
        }

        //check county
        long county = cod / 10000L;
        county %= 100;
//        System.out.print("County = " + county + " + ");

        if(county > 52){
            throw new IncorrectCNPException(this.cnp + ": CNP invalid: county does not have valid SIRUTA code");
        }

        if(cod % 1000000000000L >= 5){
            if (county == 47 || county == 48){
                throw new IncorrectCNPException(this.cnp + ": CNP invalid: county does not have valid SIRUTA code");
            }
        }

//        System.out.println();

        //check control digit
        long c = cod%10;
        cod /= 10;
        long n1 = cod%10 * 9;
        cod /= 10;
        long n2 = cod%10 * 7;
        cod /= 10;
        long n3 = cod%10 * 2;
        cod /= 10;
        long j1 = cod%10 * 8;
        cod /= 10;
        long j2 = cod%10 * 5;
        cod /= 10;
        long z1 = cod%10 * 3;
        cod /= 10;
        long z2 = cod%10 * 6;
        cod /= 10;
        long l1 = cod%10 * 4;
        cod /= 10;
        long l2 = cod%10 * 1;
        cod /= 10;
        long a1 = cod%10 * 9;
        cod /= 10;
        long a2 = cod%10 * 7;
        cod /= 10;
        long s = cod%10 * 2;

        long result = s+a2+a1+l2+l1+z2+z1+j2+j1+n3+n2+n1;
        result %= 11;

        if(result < 10){
            if(result != c) {
                throw new IncorrectCNPException(this.cnp + ": CNP has invalid control digit; correct one should be " + result);
            }
        } else {
            if(c != 1) {
                throw new IncorrectCNPException(this.cnp + ": CNP has invalid control digit; correct one should be 1");
            }
        }

    }



    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + EmployeeID +
                ", cnp=" + cnp +
                ", fullname='" + fullname + '\'' +
                ", role='" + role + '\'' +
                ", workplace='" + workplace + '\'' +
                ", salary=" + salary +
                '}';
    }
}


