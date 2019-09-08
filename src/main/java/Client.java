import commnads.Command;
import model.ParkingFloor;
import validator.InputValidator;

import java.util.Scanner;

/**
 * @author Abhijeet Gulve
 */
public class Client {
    public static void main(String[] args) {

        System.out.println("Commands to execute");
        for (Command command : Command.values()) {
            System.out.println(command.ordinal() + 1 + " " + command);
        }
        String inputLine = "";
        Scanner scanner = new Scanner(System.in);
        ParkingFloor floor = new ParkingFloor(1);
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
}
