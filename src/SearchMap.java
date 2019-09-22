import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class SearchMap {
    static Pair origin;
    static HashSet<String> destinations;
    static HashMap<String, ArrayList<Pair>> flightInfo;
    static HashMap<String, ArrayList<Pair>> result;

    public SearchMap() {
        destinations = new HashSet<String>();
        flightInfo = new HashMap<String, ArrayList<Pair>>();
        result = new HashMap<String, ArrayList<Pair>>();
    }
    
    public static void readInput(File input) throws FileNotFoundException {
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

    private static void readInputHelper(String line) {
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

    public static void writeOutput(File output) throws IOException {
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

    public static void main(String[] args) {
        SearchMap searchMap = new SearchMap();
        FlightMap flightMap = new FlightMap();

        File input = new File(args[0]);
        File output = new File(args[1]);

        try {
            searchMap.readInput(input);
            flightMap.parseFlightInfo(searchMap.destinations, searchMap.origin, searchMap.result, searchMap.flightInfo);
            searchMap.writeOutput(output);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}