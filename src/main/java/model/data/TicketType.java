package model.data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;


@Entity
@Table(indexes = {@Index(columnList = "value")})
public class TicketType {

    @Id
    private String type;

    private float value;

    public TicketType() {

    }

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
