import exception.InvalidSlotNumberException;
import exception.NoEmptySlotAvailable;
import exception.VehicleNotFoundException;
import model.ParkingFloor;
import model.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    }

    @Test
    public void parkVehicleInvalidTest() {
        Assert.assertFalse(parkingFloor.createParkingSLot(-1));
    }

    @Test
    public void parkVehicleValidTest() {
        Assert.assertTrue(parkingFloor.createParkingSLot(2));
    }


    @Test(expected = NoEmptySlotAvailable.class)
    public void parkVehicleNoSlotAvailableTest() throws NoEmptySlotAvailable {
        parkingFloor.parkVehicle(new Vehicle("MH-17-5663", "Black"));
        parkingFloor.parkVehicle(new Vehicle("MH-13-5123", "Red"));
        parkingFloor.parkVehicle(new Vehicle("MH-14-2342", "Black"));
    }

    @Test
    public void unParkVehicleFromParkingTest() {
        Assert.assertEquals(1, parkingFloor.unParkVehicle(1));
    }

    @Test
    public void parkVehicleSlotAvailableTest() throws NoEmptySlotAvailable {
        parkingFloor.unParkVehicle(1);
        Assert.assertTrue(parkingFloor.parkVehicle(new Vehicle("MH-17-5663", "Black")));
    }

    @Test(expected = InvalidSlotNumberException.class)
    public void unParkVehicleFromParkingInvalidTest() {
        parkingFloor.unParkVehicle(-2);
    }

    @Test
    public void getRegisterNumbersOfCarsByColorTest() throws NoEmptySlotAvailable {
        parkingFloor.unParkVehicle(1);
        parkingFloor.parkVehicle(new Vehicle("MH-17-5663", "Black"));
        List<String> expected = Arrays.asList("MH-17-5663");
        List<String> actual = parkingFloor.getVehicleNumbersByColor("black");
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = VehicleNotFoundException.class)
    public void getRegisterNumbersOfCarsByColorWithNoVehicleMatchTest() {
        parkingFloor.getVehicleNumbersByColor("Red");
    }

    @Test
    public void getSlotNumberOfCarsByVehicleNumberTest() throws NoEmptySlotAvailable {
        parkingFloor.unParkVehicle(1);
        parkingFloor.parkVehicle(new Vehicle("MH-17-5663", "Black"));
        Assert.assertEquals(java.util.Optional.of(1).get(), parkingFloor.getSlotNumberByVehicleNumber("MH-17-5663"));
    }

}
