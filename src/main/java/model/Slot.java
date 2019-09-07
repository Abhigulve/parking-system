package model;

import java.util.Date;

/**
 * @author Abhijeet Gulve
 */
public class Slot {
    private String id;
    private Location slotLocation;
    private SlotSize slotSize;
    private boolean isEmpty;
    private Date inTime;
    private Date outTime;

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
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

    public Location getSlotLocation() {
        return slotLocation;
    }

    public void setSlotLocation(Location slotLocation) {
        this.slotLocation = slotLocation;
    }

    public SlotSize getSlotSize() {
        return slotSize;
    }

    public void setSlotSize(SlotSize slotSize) {
        this.slotSize = slotSize;
    }
}
