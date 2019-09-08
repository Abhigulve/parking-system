import commnads.Command;
import model.ParkingFloor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Abhijeet Gulve
 */
public class ParkingAcceptanceTest {

    List<String> inputFile = new ArrayList<>();

    @Before
    public void setUp() {
        File file = new File("/home/gulve/Downloads/parking-lot-1.4.2/parking_lot/functional_spec/fixtures/file_input.txt");
        BufferedReader br;
        String st;
        try {
            br = new BufferedReader(new FileReader(file));
            while ((st = br.readLine()) != null) {
                inputFile.add(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void acceptanceTest() {
        inputFile.forEach(line->{
            String[] input = line.split(" ");
            Command cmd = Command.valueOf(input[0]);
            cmd.executeCommand(input);
        });
    }

    @After
    public void clearAll() {
        ParkingFloor.clearAll();
    }
}
