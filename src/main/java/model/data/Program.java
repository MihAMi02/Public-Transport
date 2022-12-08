package model.data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
public class Program {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    @ManyToOne
    private Vehicle v;
    @ManyToOne
    private Line line;
    private String shift;
    private LocalDate date;
    public Program(int id, Vehicle v, Line line, String shift, LocalDate date) {
        this.id = id;
        this.v = v;
        this.line = line;
        this.shift=shift;
        this.date=date;
    }

    public Program() {

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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


