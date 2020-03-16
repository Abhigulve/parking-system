import exception.InvalidSlotNumberException;
import exception.InvalidVehicleNumber;
import exception.NoEmptySlotAvailable;
import exception.VehicleNotFoundException;
import model.ParkingFloor;
import model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import statergy.DefaultStrategy;

import java.util.Arrays;
import java.util.List;

/**
 * @author Abhijeet Gulve
 */
public class ParkingTest {
    private ParkingFloor parkingFloor;

    @Before
    public void setUp() {
        parkingFloor = ParkingFloor.getParkingFloor(1);
        parkingFloor.createParkingSLot(2);
    }

    @Test
    public void parkVehicleInvalidTest() {
        Assert.assertFalse(parkingFloor.createParkingSLot(-1));
    }

    @Test
    public void parkVehicleInvalidValidTest() {
        Assert.assertFalse(parkingFloor.createParkingSLot(2));
    }


    @Test(expected = NoEmptySlotAvailable.class)
    public void parkVehicleNoSlotAvailableTest() throws NoEmptySlotAvailable {
        parkingFloor.parkVehicle(new Vehicle("MH-17-5663"));
        parkingFloor.parkVehicle(new Vehicle("MH-13-5123"));
        parkingFloor.parkVehicle(new Vehicle("MH-14-2342"));
    }

    @Test
    public void unParkVehicleFromParkingTest() throws NoEmptySlotAvailable, InvalidVehicleNumber {
        Assert.assertEquals(30, parkingFloor.unPark("MH-17-5663", 4, new DefaultStrategy()));
    }

    @Test(expected = InvalidVehicleNumber.class)
    public void parkVehicleSlotAvailableTest() throws InvalidVehicleNumber {
        parkingFloor.unPark("MH-13-512er3", 4, new DefaultStrategy());
    }
}
