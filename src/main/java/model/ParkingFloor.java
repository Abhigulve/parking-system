package model;

import exception.InvalidVehicleNumber;
import exception.NoEmptySlotAvailable;
import statergy.ParkingCostStrategy;

public interface ParkingFloor {

    boolean createParkingSLot(int numberOfSlots);

    boolean parkVehicle(Vehicle vehicle) throws NoEmptySlotAvailable;

    int unPark(String vehicleNumber, int numberOfHours, ParkingCostStrategy parkingCostStrategy) throws InvalidVehicleNumber;
}
