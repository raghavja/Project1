import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSearchMap {
    /**
     * Test the constructor
     */
    @Test
    public void testConstructor() {
        SearchMap map = new SearchMap();
        AssertTrue(map.destinations != null);
        AssertTrue(map.flightInfo != null);
        AssertTrue(map.result != null);
    }

    /**
     * Test input read
     */
    @Test
    public void testReadInput() {
        SearchMap map = new SearchMap();
        File input = new File("testInput.txt");

        map.readInput(input);

        AssertTrue(map.destinations.size() == 3);
        AssertTrue(map.flightInfo.get("A").size() == 2);
    }

    /**
     * Test output write
     */
    @Test
    public void testWriteOutput() {
        SearchMap map = new SearchMap();
        FileWriter output = new FileWriter("testOutput.txt");

        map.writeOutput(output);
    }

    /**
     * Test main method
     */
    @Test
    public void testMain() {
        SearchMap searchMap = new SearchMap();
        FlightMap flightMap = new FlightMap();

        File input = new File("input.txt");

        searchMap.readInput(input);

        flightMap.parseFlightInfo(searchMap.destinations, searchMap.origin, searchMap.result, searchMap.flightInfo);

        Iterator<String> it = searchMap.result.keySet().iterator();
        while (it.hasNext()) {
            String dest = it.next();
            ArrayList<Pair> list = searchMap.result.get(dest);

            AssertTrue(list.contains(dest));
            AssertTrue(list.contains(searchMap.origin.city));
        }
    }

}