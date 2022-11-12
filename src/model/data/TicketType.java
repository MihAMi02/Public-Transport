package model.data;

public class TicketType {
    private String type;
    private float value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public TicketType(String type, float value) {
        this.type = type;
        this.value = value;
    }
}
