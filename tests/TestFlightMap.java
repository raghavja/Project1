import org.junit.Test;
import static org.junit.Assert.*;

public class TestFlightMap {

    @Test
    public void testConstructor() {
        FlightMap map = new FlightMap();
        AssertTrue(map.destinations != null);
        AssertTrue(map.flightInfo != null);
        AssertTrue(map.result != null);
    }

    @Test
    public void testParse() {
       
    }

}