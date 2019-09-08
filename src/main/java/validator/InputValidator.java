package validator;

/**
 * @author Abhijeet Gulve
 */
public class InputValidator {
    public static boolean isValidInput(String input) {
        if (input.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isValidSlotNumber(int number) {
        if (number <= 0) {
            return false;
        }
        return true;
    }

}
