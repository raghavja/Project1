import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestFlightMap {

    /**
     * Function to test the parser in the FlightMap class
     */
    @Test
    public void testParse() {
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