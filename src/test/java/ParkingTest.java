import exception.InvalidVehicleNumber;
import exception.NoEmptySlotAvailable;
import model.Parking;
import model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import statergy.DefaultStrategy;

/**
 * @author Abhijeet Gulve
 */
public class ParkingTest {
    private Parking parking;

    @Before
    public void setUp() {
        parking = Parking.getParkingFloor(1);
        parking.createParkingSLot(2);
    }

    @Test
    public void parkVehicleInvalidTest() {
        Assert.assertFalse(parking.createParkingSLot(-1));
    }

    @Test
    public void parkVehicleInvalidValidTest() {
        Assert.assertFalse(parking.createParkingSLot(2));
    }


    @Test(expected = NoEmptySlotAvailable.class)
    public void parkVehicleNoSlotAvailableTest() throws NoEmptySlotAvailable {
        parking.parkVehicle(new Vehicle("MH-17-5663"));
        parking.parkVehicle(new Vehicle("MH-13-5123"));
        parking.parkVehicle(new Vehicle("MH-14-2342"));
    }

    @Test
    public void unParkVehicleFromParkingTest() throws NoEmptySlotAvailable, InvalidVehicleNumber {
        Assert.assertEquals(30, parking.unPark("MH-17-5663", 4, new DefaultStrategy()));
    }

    @Test(expected = InvalidVehicleNumber.class)
    public void parkVehicleSlotAvailableTest() throws InvalidVehicleNumber {
        parking.unPark("MH-13-512er3", 4, new DefaultStrategy());
    }
}
