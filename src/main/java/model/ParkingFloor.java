package model;

import exception.InvalidSlotNumberException;
import exception.NoEmptySlotAvailable;
import exception.VehicleNotFoundException;
import validator.InputValidator;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Abhijeet Gulve
 */
public class ParkingFloor {
    private int floorNumber;
    private List<Slot> slots;

    public ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
    }

    private Slot getNextEmptySlotOnFloor() throws NoEmptySlotAvailable {
        for (Slot slot : slots) {
            if (!slot.isEmpty()) {
                return slot;
            }
        }
        throw new NoEmptySlotAvailable("For floorNumber " + floorNumber + " No Empty Slot available");
    }

    public boolean createParkingSLot(int numberOfSlots) {
        if (!InputValidator.isValidSlotNumber(numberOfSlots)) {
            return false;
        }
        for (int i = 1; i <= numberOfSlots; i++) {
            slots.add(new Slot(UUID.randomUUID().toString(), i));
        }
        System.out.printf("Created a parking lot with %s slots %n", numberOfSlots);
        return true;
    }

    public boolean parkVehicle(Vehicle vehicle) throws NoEmptySlotAvailable {
        Slot nextEmptySlotOnFloor = getNextEmptySlotOnFloor();
        nextEmptySlotOnFloor.placeVehicle(vehicle);
        System.out.printf("Allocated slot number: %d \n", nextEmptySlotOnFloor.getSlotNumber());
        return true;
    }

    public boolean unParkVehicle(int slotNumber) {
        Slot slot = slots.get(slotNumber - 1);
        if (slot != null) {
            slot.removeVehicle();
            System.out.printf("Slot number %d is free \n", slotNumber);
        } else {
            throw new InvalidSlotNumberException(String.format("%d is invalid slot number", slotNumber));
        }
        return true;
    }


    public void printStatus() {
        System.out.println("Slot No. Registration No Colour ");
        slots.forEach(slot->{
            if (!slots.isEmpty()) {
                Vehicle parkVehicle = slot.getParkVehicle();
                if (parkVehicle != null)
                    System.out.printf("%d       %s      %s\n", slot.getSlotNumber(), parkVehicle.getVehicleNumber(), parkVehicle.getVehicleColor());
            }
        });
    }

    public List<Slot> getVehicleSlotsByColor(String color) {
        List<Slot> slots = this.slots.stream()
                .filter(slot->slot.getParkVehicle().getVehicleColor().equalsIgnoreCase(color))
                .collect(Collectors.toList());
        if (slots.isEmpty()) {
            throw new VehicleNotFoundException(String.format("Vehicle not found for color %s", color));
        }
        return slots;
    }

    public Slot getSlotNumberByVehicleNumber(String vehicleNumber) {
        Optional<Slot> slotOptional = slots.stream()
                .filter(slot->slot.getParkVehicle().getVehicleNumber().equalsIgnoreCase(vehicleNumber))
                .findAny();
        return slotOptional.orElseThrow(()->new VehicleNotFoundException(String.format("Provided vehicle number %d is not present", vehicleNumber)));
    }

}