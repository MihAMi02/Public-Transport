package model.data;

public class Ticket {
    private double value;
    private String type;
    private String id;

    public Ticket(double value, String type, String id) {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
