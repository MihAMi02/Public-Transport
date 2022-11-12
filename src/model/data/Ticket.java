package model.data;

public class Ticket {
    private double value;
    private String type;
    private int id;

    public Ticket(double value, String type, int id) {
        this.value = value;
        this.type = type;
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}
