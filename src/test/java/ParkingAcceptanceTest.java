import commands.Command;
import model.Parking;
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
        System.getProperty("user.dir");
        File file = new File(System.getProperty("user.dir") + "/../functional_spec/fixtures/file_input.txt");
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
        Parking.clearAll();
    }
}
