package exception;

/**
 * @author Abhijeet Gulve
 */
public class InvalidSlotNumberException extends RuntimeException {
    public InvalidSlotNumberException(String message) {
        super(message);
    }
}
