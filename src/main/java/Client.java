import commnads.Command;
import validator.InputValidator;

import java.util.Scanner;

/**
 * @author Abhijeet Gulve
 */
public class Client {
    public static void main(String[] args) {
        printCommands();
        readCommandAndExecute();
    }

    private static void readCommandAndExecute() {
        String inputLine;
        Scanner scanner = new Scanner(System.in);
        do {
            inputLine = scanner.nextLine();
            if (InputValidator.isValidInput(inputLine)) {
                String[] input = inputLine.split(" ");
                Command cmd;
                try {
                    cmd = Command.valueOf(input[0]);
                    cmd.executeCommand(input);
                } catch (Exception e) {
                    System.out.println("Please provide valid command....");
                }
            }
        } while (!inputLine.equals("exit"));
    }

    private static void printCommands() {
        System.out.println("***************************************************************************************");
        System.out.println("**********************  WELCOME TO PARKING SYSTEM APPLICATION  ************************");
        System.out.println("***************************************************************************************");
        System.out.println("*******************************  SAMPLE INPUT COMMANDS  *******************************");
        System.out.println("1. create_parking_lot {capacity}");
        System.out.println("2. park {VehicleNUmber} {VehicleColor}");
        System.out.println("3. leave {SlotNumber}");
        System.out.println("4. status");
        System.out.println("5. park {VehicleNUmber} {VehicleColor}");
        System.out.println("6. registration_numbers_for_cars_with_colour {VehicleColor}");
        System.out.println("7. slot_numbers_for_cars_with_colour {VehicleColor}");
        System.out.println("8. slot_number_for_registration_number {VehicleNUmber}");
    }
}
