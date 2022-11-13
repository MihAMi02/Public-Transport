package model.data;

import java.util.ArrayList;
import java.util.List;

public class Station
{
    private int stationId;
    private String name;
    private String address;
    private List<String> linesCalling;

    public Station(int stationId, String name, String address)
    {
        this.stationId = stationId;
        this.name = name;
        this.address = address;
        this.linesCalling = new ArrayList<>();
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

    public List<String> getLinesCalling() {
        return linesCalling;
    }

    public void setLinesCalling(List<String> linesCalling) {
        this.linesCalling = linesCalling;
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

    public void addLine(String line){
        this.linesCalling.add(line);
    }

    public void delLine(String line){
        this.linesCalling.remove(line);
    }
}
