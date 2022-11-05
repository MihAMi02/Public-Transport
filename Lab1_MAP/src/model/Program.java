package model;

import java.util.Date;

public class Program {

    private Vehicle v;

    private String line;
    private String shift;
    private Date date;




    public Program(Vehicle v, String line, String shift, Date date) {
        this.v = v;
        this.line = line;
        this.shift=shift;
        this.date=date;
    }

    public Program(String vv, String line) {
    }



    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "VehicletoLine{" +
                "v=" + v +
                ", line='" + line + '\'' +
                ", shift='" + shift + '\'' +
                ", date=" + date +
                '}';
    }

}


