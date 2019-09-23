/**
 * Pair class to store city and cost
 */
public class Pair {
    public String city; // city name
    public int cost; // cost to city from a destination

    /**
     * Constructor for Pair class
     * @param c the city name
     * @param co the cost to reach the city
     */
    public Pair(String c, int co) {
        city = c;
        cost = co;
    }
}