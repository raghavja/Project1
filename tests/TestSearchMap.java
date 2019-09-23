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
        assertTrue(map.destinations != null);
        assertTrue(map.flightInfo != null);
        assertTrue(map.result != null);
    }

    /**
     * Test input read
     */
    @Test
    public void testReadInput() {
        SearchMap map = new SearchMap();
        File input = new File("testInput.txt");

        try {
            map.readInput(input);
        } catch (Exception e) {
            return;
        }

        assertTrue(map.destinations.size() == 3);
        assertTrue(map.flightInfo.get("A").size() == 2);
    }

    /**
     * Test output write
     */
    @Test
    public void testWriteOutput() {
        SearchMap map = new SearchMap();
        File output = new File("testOutput.txt");

        try {
            map.writeOutput(output);
        } catch (Exception e) {
            return;
        }
    }

    /**
     * Test main method
     */
    @Test
    public void testMain() {
        SearchMap searchMap = new SearchMap();
        FlightMap flightMap = new FlightMap();

        File input = new File("testInput.txt");

        try {
            searchMap.readInput(input);
        } catch (Exception e) {
            return;
        }

        flightMap.parseFlightInfo(searchMap.destinations, searchMap.origin, searchMap.result, searchMap.flightInfo);

        Iterator<String> it = searchMap.result.keySet().iterator();
        while (it.hasNext()) {
            String dest = it.next();
            ArrayList<Pair> list = searchMap.result.get(dest);

            assertTrue(list.contains(dest));
            assertTrue(list.contains(searchMap.origin.city));
        }
    }

}