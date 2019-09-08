package exception;

/**
 * @author Abhijeet Gulve
 */
public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String format) {
        super(format);
    }
}
