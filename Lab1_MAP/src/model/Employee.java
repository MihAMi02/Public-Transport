package model;

public class Employee {

    private int id;

    private int cnp;

    private String fullname;

    private String role;

    private String workplace;

    private int salary;

    public Employee(int id, int cnp, String fullname, String role, String workplace, int salary) {
        this.id = id;
        this.cnp = cnp;
        this.fullname = fullname;
        this.role = role;
        this.workplace = workplace;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCnp() {
        return cnp;
    }

    public void setCnp(int cnp) {
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
                "id=" + id +
                ", cnp=" + cnp +
                ", fullname='" + fullname + '\'' +
                ", role='" + role + '\'' +
                ", workplace='" + workplace + '\'' +
                ", salary=" + salary +
                '}';
    }
}


