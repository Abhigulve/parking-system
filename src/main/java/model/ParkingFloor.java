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
    private static ParkingFloor parkingLot;
    private int floorNumber;
    private List<Slot> slots;


    private ParkingFloor(int floorNumber) {
        this.floorNumber = floorNumber;
        this.slots = new ArrayList<>();
    }

    public static ParkingFloor getParkingFloor(int floorNumber) {
        if (parkingLot == null)
            parkingLot = new ParkingFloor(floorNumber);
        return parkingLot;
    }

    public static void clearAll() {
        parkingLot = null;
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
        if (inputValidator(InputValidator.isValidSlotNumber(numberOfSlots)) || slots.size() > 0)
            return false;

        for (int i = 1; i <= numberOfSlots; i++) {
            slots.add(new Slot(UUID.randomUUID().toString(), i));
        }
        System.out.printf("Created a parking lot with %s slots %n", numberOfSlots);
        return true;
    }

    private boolean inputValidator(boolean validSlotNumber) {
        if (!validSlotNumber) {
            return true;
        }
        return false;
    }

    public boolean parkVehicle(Vehicle vehicle) throws NoEmptySlotAvailable {
        Slot nextEmptySlotOnFloor = getNextEmptySlotOnFloor();
        nextEmptySlotOnFloor.placeVehicle(vehicle);
        System.out.printf("Allocated slot number: %d \n", nextEmptySlotOnFloor.getSlotNumber());
        return true;
    }

    public int unParkVehicle(int slotNumber) {
        if (slotNumber <= 0) {
            throw new InvalidSlotNumberException(String.format("%d is invalid slot number,slotNumber > 1", slotNumber));
        }
        Slot slot = slots.get(slotNumber - 1);
        if (slot != null) {
            slot.removeVehicle();
        } else {
            throw new InvalidSlotNumberException(String.format("%d is invalid slot number", slotNumber));
        }
        return slotNumber;
    }

    public void printStatus() {
        System.out.println("Slot No.  Registration No   Color");
        slots.forEach(slot->{
            if (!slots.isEmpty()) {
                Vehicle parkVehicle = slot.getParkVehicle();
                if (parkVehicle != null)
                    System.out.printf("%d          %s    %s\n", slot.getSlotNumber(), parkVehicle.getVehicleNumber(), parkVehicle.getVehicleColor());
            }
        });
    }

    public List<String> getVehicleNumbersByColor(String color) {
        List<String> vehicleNumbers = new ArrayList<>();
        slots.forEach(slot->{
            if (slot.isEmpty() && slot.getParkVehicle().getVehicleColor().equalsIgnoreCase(color)) {
                vehicleNumbers.add(slot.getParkVehicle().getVehicleNumber());
            }
        });
        if (vehicleNumbers.isEmpty()) {
            throw new VehicleNotFoundException(String.format("Vehicle not found for color %s", color));
        }
        return vehicleNumbers;
    }

    public List<Integer> getSlotNumbersByColor(String color) {
        List<Integer> slotNumbers = this.slots.stream()
                .filter(slot->slot.isEmpty() && slot.getParkVehicle().getVehicleColor().equalsIgnoreCase(color)).map(Slot::getSlotNumber)
                .collect(Collectors.toList());
        if (slotNumbers.isEmpty()) {
            throw new VehicleNotFoundException(String.format("Vehicle not found for color %s", color));
        }
        return slotNumbers;
    }

    public Integer getSlotNumberByVehicleNumber(String vehicleNumber) {
        Optional<Integer> slotOptional = slots.stream()
                .filter(slot->slot.getParkVehicle().getVehicleNumber().equalsIgnoreCase(vehicleNumber)).map(Slot::getSlotNumber)
                .findAny();
        return slotOptional.orElseThrow(()->new VehicleNotFoundException(String.format("Provided vehicle number %s is not present", vehicleNumber)));
    }

}