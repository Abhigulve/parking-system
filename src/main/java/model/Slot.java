package model;

import java.time.LocalDateTime;

/**
 * @author Abhijeet Gulve
 */
public class Slot {
    private String id;
    private Integer number;
    private boolean isEmpty;
    private Vehicle parkVehicle;

    public Slot(String id, Integer number) {
        this.id = id;
        this.number = number;
    }

    public Vehicle getParkVehicle() {
        return parkVehicle;
    }

    public void setParkVehicle(Vehicle parkVehicle) {
        this.parkVehicle = parkVehicle;
    }

    public void removeVehicle() {
        parkVehicle = null;
        this.isEmpty = false;
    }

    public void placeVehicle(Vehicle parkVehicle) {
        this.parkVehicle = parkVehicle;
        this.isEmpty = true;
    }

    public Integer getSlotNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
