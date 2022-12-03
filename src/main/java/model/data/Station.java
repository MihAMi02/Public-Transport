package model.data;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity

public class Station
{

    @Id
    private int stationId;
    private String name;
    private String address;
    @OneToMany
    @Getter
    @Setter
    private List<Line> linesCalling;

    public Station(int stationId, String name, String address)
    {
        this.stationId = stationId;
        this.name = name;
        this.address = address;
        this.linesCalling = new ArrayList<>();
    }

    public Station() {

    }

    @Override
    public String toString() {
        return "Station{" +
                "stationId=" + stationId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", linesCalling=" + linesCalling +
                '}';
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void addLine(Line line){
        this.linesCalling.add(line);
    }

    public void delLine(Line line){
        this.linesCalling.remove(line);
    }

    public Line getLine(String lineNumber) {
        for(Line line: this.linesCalling){
            if(Objects.equals(line.getLineNumber(), lineNumber)){
                return line;
            }
        }
        return null;
    }
}
