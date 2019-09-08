package commnads;

import exception.NoEmptySlotAvailable;
import model.ParkingFloor;
import model.Vehicle;

public enum Command implements CommandI {

    create_parking_lot() {
        @Override
        public void executeCommand(String[] details) {
            floor.createParkingSLot(Integer.parseInt(details[1]));
        }
    },

    park {
        @Override
        public void executeCommand(String[] details) {
            try {
                floor.parkVehicle(new Vehicle(details[1], details[2]));
            } catch (NoEmptySlotAvailable noEmptySlotAvailable) {
                System.out.println("Sorry, parking lot is full");
            }
        }
    },
    leave {
        @Override
        public void executeCommand(String[] details) {
            floor.unParkVehicle(Integer.parseInt(details[1]));
        }
    },
    status {
        @Override
        public void executeCommand(String[] details) {
            floor.printStatus();

        }
    },
    registration_numbers_for_cars_with_colour {
        @Override
        public void executeCommand(String[] details) {
            floor.getVehicleSlotsByColor(details[1]).forEach(slot->{
                System.out.print(slot.getParkVehicle().getVehicleNumber() + ", ");
            });
            System.out.println();
        }
    },

    slot_numbers_for_cars_with_colour {
        @Override
        public void executeCommand(String[] details) {
            floor.getVehicleSlotsByColor(details[1]).forEach(slot->{
                System.out.print(slot.getSlotNumber() + ", ");
            });
            System.out.println();
        }
    },
    slot_number_for_registration_number {
        @Override
        public void executeCommand(String[] details) {
            try {
                System.out.println(floor.getSlotNumberByVehicleNumber(details[1]).getSlotNumber());
            } catch (Exception e) {
                System.out.println("Not Found");
            }
        }
    },
    exit() {
        @Override
        public void executeCommand(String[] details) {

        }
    }
}

/**
 * @author Abhijeet Gulve
 */

interface CommandI {
    ParkingFloor floor = new ParkingFloor(1);

    void executeCommand(String[] details);
}
