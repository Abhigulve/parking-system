package model;

import exception.NoEmptySlotAvailable;

import java.util.List;

/**
 * @author Abhijeet Gulve
 */
public class ParkingFloor {
    private int floorNumber;
    private List<Slot> slots;


    public Slot getNextEmptySlotOnFloor(final int vehicleSize) throws NoEmptySlotAvailable {
        for (Slot slot : slots) {
            if (slot.isEmpty() && (slot.getSlotSize().equals(SlotSize.getSLot(vehicleSize)))) {
                return slot;
            }
        }
        throw new NoEmptySlotAvailable("For floorNumber " + floorNumber + " No Empty Slot available");
    }

    public boolean MakeEmptyParkingSlot(int a) {

        return true;
    }

}