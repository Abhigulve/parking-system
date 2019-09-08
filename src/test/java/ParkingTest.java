import model.ParkingFloor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Abhijeet Gulve
 */
public class ParkingTest {
    ParkingFloor parkingFloor;

    @Before
    public void setUp() {
        parkingFloor = new ParkingFloor(1);
    }

    @Test
    public void parkVehicleInvalidTest() {
        Assert.assertFalse(parkingFloor.createParkingSLot(-1));
    }

    @Test
    public void parkVehicleValidTest() {
        Assert.assertTrue(parkingFloor.createParkingSLot(6));
    }



}
