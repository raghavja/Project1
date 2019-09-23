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

        File input = new File("input.txt");

        searchMap.readInput(input);

        flightMap.parseFlightInfo(searchMap.destinations, searchMap.origin, searchMap.result, searchMap.flightInfo);

        Iterator<String> it = searchMap.result.keySet().iterator();
        while (it.hasNext()) {
            String dest = it.next();
            ArrayList<Pair> list = result.get(dest);

            AssertTrue(list.contains(dest));
            AssertTrue(list.contains(searchMap.origin.city));
        }
    }
}