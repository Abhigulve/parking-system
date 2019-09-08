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
            try {
                int slotNumber = floor.unParkVehicle(Integer.parseInt(details[1]));
                System.out.printf("Slot number %d is free \n", slotNumber);
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
    registration_numbers_for_cars_with_colour {
        @Override
        public void executeCommand(String[] details) {
            floor.getVehicleNumbersByColor(details[1]).forEach(vehicleNumber->{
                System.out.print(vehicleNumber + ", ");
            });
            System.out.println();
        }
    },

    slot_numbers_for_cars_with_colour {
        @Override
        public void executeCommand(String[] details) {
            floor.getSlotNumbersByColor(details[1]).forEach(num->System.out.print(num + ","));
            System.out.println();
        }
    },
    slot_number_for_registration_number {
        @Override
        public void executeCommand(String[] details) {
            try {
                System.out.println(floor.getSlotNumberByVehicleNumber(details[1]));
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
    ParkingFloor floor = ParkingFloor.getParkingFloor(1);

    void executeCommand(String[] details);
}
