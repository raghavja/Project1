import org.apache.commons.io.FileUtils;
import org.junit.rules.TemporaryFolder;
import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestSearchMap {
    /**
     * Creates a temprary folder to store test files
     */
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

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
        File createdFile= folder.newFile("testInput.txt");
        FileUtils.writeStringToFile(createdFile, "X\n");
        FileUtils.writeStringToFile(createdFile, "A B 200\n");
        FileUtils.writeStringToFile(createdFile, "A C 100\n");
        FileUtils.writeStringToFile(createdFile, "B X 120\n");

        map.readInput(createdFile);

        AssertTrue(map.destinations.size() == 3);
        AssertTrue(map.flightInfo.get("A").size() == 2);
    }

    /**
     * Test output write
     */
    @Test
    public void testWriteOutput() {
        SearchMap map = new SearchMap();
        File createdFile= folder.newFile("testOutput.txt");

        map.writeOutput(createdFile);


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