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
    Pair origin;
    HashSet<String> destinations;
    HashMap<String, ArrayList<Pair>> flightInfo;
    HashMap<String, ArrayList<Pair>> result;

    public FlightMap() {
        destinations = new HashSet<String>();
        flightInfo = new HashMap<String, ArrayList<Pair>>();
        result = new HashMap<String, ArrayList<Pair>>();
    }

    public void readInput(File input) throws FileNotFoundException {
        Scanner scanner = new Scanner(input);
        String originCity = scanner.nextLine();
        origin = new Pair(originCity, 0);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            readInputHelper(line);
        }
        scanner.close();
        destinations.remove(origin.city);
    }

    private void readInputHelper(String line) {
        String[] lineData = line.split(" ");

        String start = lineData[0];
        String dest = lineData[1];
        int cost = Integer.parseInt(lineData[2]);

        ArrayList<Pair> list;
        if (flightInfo.get(start) == null) {
            list = new ArrayList<Pair>();
        } else {
            list = flightInfo.get(start);
        }

        Pair pair = new Pair(dest, cost);
        list.add(pair);
        flightInfo.put(start, list);

        destinations.add(dest);
        destinations.add(start);
    }

    public void parseFlightInfo() {
        for (String dest : destinations) {
            ArrayList<Pair> solution = new ArrayList<Pair>();
            ArrayList<String> visited = new ArrayList<String>();

            if (parseHelper(origin.city, dest, solution, visited)) {
                solution.add(solution.size(), origin);
                result.put(dest, solution);
            }
        }
    }

    private Boolean parseHelper(String start, String dest, ArrayList<Pair> solution, ArrayList<String> visited) {
        if (start.equals(dest)) {
            return true;
        }

        ArrayList<Pair> neighbors = flightInfo.get(start);
        visited.add(start);

        if (neighbors == null) {
            return false;
        }

        for (Pair neighbor : neighbors) {
            if (!visited.contains(neighbor.city) && parseHelper(neighbor.city, dest, solution, visited)) {
                solution.add(neighbor);
                return true;
            }
        }

        return false;
    }

    public void writeOutput(File output) throws IOException {
        FileWriter writer = new FileWriter(output);
        Iterator<String> it = result.keySet().iterator();

        writer.write(String.format("%s %20s %15s\r\n", "Destination", "Flight Route From", "Total Cost"));

        while (it.hasNext()) {
            String dest = it.next();
            ArrayList<Pair> list = result.get(dest);
            int cost = 0;
            String path = "";

            for (int i = list.size() - 1; i >= 0; i--) {
                Pair curr = list.get(i);
                cost += curr.cost;
                if (i == 0) {
                    path += curr.city;
                } else {
                    path += curr.city + ",";
                }
            }

            writer.write(String.format("%s %25s %20d\r\n", dest, path, cost));
        }

        writer.close();
    }
}