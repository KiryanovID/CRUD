
import java.util.HashMap;
import java.util.Map;

public class Repository {
    private final Map<Integer, Country> countryMap;

    public Repository() {
        countryMap = new HashMap<>();
        countryMap.put(1, new Country("Russia", 17130000, 144100000, Continent.EUROPE));
        countryMap.put(2, new Country("China", 9597000, 1402000000, Continent.ASIA));
        countryMap.put(3, new Country("USA", 9834000, 329500000, Continent.NORTH_AMERICA));

    }

    public Map<Integer, Country> getCountryMap() {
        return countryMap;
    }

    @Override
    public String toString() {
        return "Repository{" +
                "countryMap=" + countryMap +
                '}';
    }
}
