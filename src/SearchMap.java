import java.io.File;

public class SearchMap {
    public static void main(String[] args) {
        FlightMap flightMap = new FlightMap();

        File input = new File(args[0]);
        File output = new File(args[1]);

        try {
            flightMap.readInput(input);
            flightMap.parseFlightInfo();
            flightMap.writeOutput(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}