import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class FlightMap {

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