import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * FLightMap class to determine potential flight paths
 */
public class FlightMap {
    /**
     * Method to determine the path between the origin and a destination city
     * @param destinations the list of desintations
     * @param origin the origin city
     * @param result a map with key as the destination and value as the list containing the path
     * @param flightInfo a map with key as a city and value as a list of its neighbors
     */
    public void parseFlightInfo(HashSet<String> destinations, Pair origin,HashMap<String, ArrayList<Pair>> result, HashMap<String, ArrayList<Pair>> flightInfo) {
        for (String dest : destinations) {
            ArrayList<Pair> solution = new ArrayList<Pair>();
            ArrayList<String> visited = new ArrayList<String>();

            if (parseHelper(origin.city, dest, solution, visited, flightInfo)) {
                solution.add(solution.size(), origin);
                result.put(dest, solution);
            }
        }
    }

    /**
     * Helper method to determine the path between the origin and a destination city
     * @param destinations the list of desintations
     * @param origin the origin city
     * @param result a map with key as the destination and value as the list containing the path
     * @param flightInfo a map with key as a city and value as a list of its neighbors
     */
    private Boolean parseHelper(String start, String dest, ArrayList<Pair> solution, ArrayList<String> visited, HashMap<String, ArrayList<Pair>> flightInfo) {
        if (start.equals(dest)) {
            return true;
        }

        ArrayList<Pair> neighbors = flightInfo.get(start);
        visited.add(start);

        if (neighbors == null) {
            return false;
        }

        for (Pair neighbor : neighbors) {
            if (!visited.contains(neighbor.city) && parseHelper(neighbor.city, dest, solution, visited, flightInfo)) {
                solution.add(neighbor);
                return true;
            }
        }
        return false;
    }
}