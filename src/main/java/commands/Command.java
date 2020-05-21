package commands;

import exception.NoEmptySlotAvailable;
import model.Parking;
import model.Vehicle;
import statergy.DefaultStrategy;

public enum Command implements CommandI {
    create_parking_lot {
        @Override
        public void executeCommand(String[] details) {
            floor.createParkingSLot(Integer.parseInt(details[1]));
        }
    },

    park {
        @Override
        public void executeCommand(String[] details) {
            try {
                floor.parkVehicle(new Vehicle(details[1]));
            } catch (NoEmptySlotAvailable noEmptySlotAvailable) {
                System.out.println("Sorry, parking lot is full");
            }
        }
    },
    leave {
        @Override
        public void executeCommand(String[] details) {
            try {
                floor.unPark(details[1], Integer.parseInt(details[2]), new DefaultStrategy());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    },
    status {
        @Override
        public void executeCommand(String[] details) {
            floor.printStatus();

        }
    },

    exit {
        @Override
        public void executeCommand(String[] details) {

        }
    };
    Parking floor = Parking.getParkingFloor(1);
}

/**
 * @author Abhijeet Gulve
 */

interface CommandI {
    void executeCommand(String[] details);
}