package model.data;

import java.util.Date;

public class Program {
    private int id;
    private Vehicle v;
    private Line line;
    private String shift;
    private Date date;
    public Program(int id, Vehicle v, Line line, String shift, Date date) {
        this.id = id;
        this.v = v;
        this.line = line;
        this.shift=shift;
        this.date=date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vehicle getV() {
        return v;
    }

    public void setV(Vehicle v) {
        this.v = v;
    }

    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
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


