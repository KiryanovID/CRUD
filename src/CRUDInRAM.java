import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class CRUDInRAM {
    private int count = 3;
    private final Repository repository = new Repository();
    private final Map<Integer, Country> countryMap = repository.getCountryMap();
    private final Scanner scanner = new Scanner(System.in);

    public void crate() {
        count++;
        System.out.println("Введите название страны:");
        String name = scanner.nextLine().trim();
        System.out.println("Введите континет на котором находится страна:");
        String conti = scanner.nextLine().toUpperCase(Locale.ROOT).trim();
        System.out.println("Введите площадь страны:");
        long square = scanner.nextLong();
        System.out.println("Введите население страны:");
        int population = scanner.nextInt();
        try {
            Continent continent = Continent.valueOf(conti);
            countryMap.put(count, new Country(name, square, population, continent));
        } catch (Exception e) {
            System.out.println("Такого континента не существует");
        }

    }

    public void read() {
        System.out.println("введите ID страны");
        int find = scanner.nextInt();
        for (Map.Entry<Integer, Country> entry : countryMap.entrySet()) {
            if (entry.getKey() == find) {
                String pop = String.valueOf(entry.getValue().getPopulation());
                String sqr = String.valueOf(entry.getValue().getSquare());
                String cont = String.valueOf(entry.getValue().getContinent());
                System.out.println("Вы искали страну " + entry.getValue().getName() + "\n" +
                        "Ее население = " + pop + "человек" + "\n" +
                        "Площадь страны = " + sqr + "кв.км" + "\n" +
                        "Она находится на континете " + cont + "\n");
                System.out.println("Введите команду: ");
            }
        }
    }

    public void update() {
        System.out.println("введите ID страны");
        int find = scanner.nextInt();
        if (find > count) {
            System.out.println("Введите команду: ");
            return;
        }
        Country country = null;
        Continent continent;
        String newName;
        long newSquare;
        int newPopulation;
        String newContinent;
        for (Map.Entry<Integer, Country> entry : countryMap.entrySet()) {
            if (entry.getKey() == find) {
                country = entry.getValue();
            }
        }

        System.out.println("введите новое название страны");
        if (scanner.nextLine().equals("")) {
            newName = scanner.nextLine().trim();
            country.setName(newName);
        }

        System.out.println("введите континет");
        if (scanner.nextLine().equals("")) {
            newContinent = scanner.nextLine().toUpperCase(Locale.ROOT).trim();
            continent = Continent.valueOf(newContinent);
            country.setContinent(continent);
        }

        System.out.println("введите количество жителей");
        if (scanner.nextLine().equals("")) {
            newPopulation = scanner.nextInt();
            country.setPopulation(newPopulation);
        }

        System.out.println("введите площадь страны");
        if (scanner.nextLine().equals("")) {
            newSquare = scanner.nextLong();
            country.setSquare(newSquare);
        }

        System.out.println("Введите команду: ");
    }

    public void delete() {
        System.out.println("введите ID страны");
        int find = scanner.nextInt();
        for (Map.Entry<Integer, Country> entry : countryMap.entrySet()) {
            if (entry.getKey() == find) {
                countryMap.remove(entry.getKey());
            }
        }
        System.out.println("Введите команду: ");
    }

    public void showAll() {
        for (Map.Entry<Integer, Country> entry : countryMap.entrySet()) {
            System.out.println(entry.getValue().toString());
        }
        System.out.println("Введите команду: ");
    }
}
