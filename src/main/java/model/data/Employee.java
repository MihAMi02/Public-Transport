package model.data;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
//@Table(name = "employees")
public class Employee
{


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long EmployeeID;

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


    public Employee(int id, String cnp, String fullname, String role, String workplace, int salary) {
        this.EmployeeID=id;
        this.cnp = cnp;
        this.fullname = fullname;
        this.role = role;
        this.workplace = workplace;
        this.salary = salary;
        this.vehicle = null;
    }

    public Employee() {

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


