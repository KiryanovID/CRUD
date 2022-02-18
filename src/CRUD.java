import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CRUD {
    private final Scanner scanner = new Scanner(System.in);
    private final MyJDBCTemplate myJDBCTemplate = new MyJDBCTemplate();
    private final JdbcTemplate jdbcTemplate = myJDBCTemplate.getJdbcTemplate();
    private static final String RED = "\u001B[31m";
    private static final String RESET = "\033[0m";
    private static final String GREEN = "\033[0;32m";

    public void create() {
        System.out.println("Введите название страны:");
        String name = scanner.nextLine().trim();
        System.out.println("Введите континет на котором находится страна:");
        String conti = scanner.nextLine().toUpperCase(Locale.ROOT).trim();
        System.out.println("Введите площадь страны:");
        long square = scanner.nextLong();
        System.out.println("Введите население страны:");
        int population = scanner.nextInt();
        Continent continent = Continent.valueOf(conti);
        jdbcTemplate.update("INSERT INTO country(name,square,population,continent) VALUES(?,?,?,?)", name, square, population, continent);

        System.out.println(GREEN + "Запись добавлена");
        System.out.println(RESET + "Введите команду: ");
    }

    public void read() {
        System.out.println("введите ID страны");
        int find = scanner.nextInt();

        Country country = jdbcTemplate.query("SELECT name, square, population, continent FROM country WHERE id = ?;",
                new Object[]{find},
                new CountryMapper()).stream().findAny().orElse(null);
        System.out.println(country);
        System.out.println("Введите команду: ");
    }

    public void update() {

        System.out.println("введите ID страны");
        int find = scanner.nextInt();
        scanner.nextLine();
        Country country = jdbcTemplate.query("SELECT name, square, population, continent FROM country WHERE id = ?;",
                new Object[]{find},
                new CountryMapper()).stream().findAny().orElse(null);
        System.out.println("Введите название страны:");
        String name = scanner.nextLine().trim();
        if (!name.equals("")) {
            country.setName(name);
        }
        System.out.println("Введите континет на котором находится страна:");
        String conti = scanner.nextLine().toUpperCase(Locale.ROOT).trim();
        if (!conti.equals("")) {
            try {
                Continent tempContinent = Continent.valueOf(conti);
                country.setContinent(tempContinent);
            } catch (IllegalArgumentException e) {
                System.out.println("нет такого континета, изменения не внесены");
            }
        }
        System.out.println("Введите площадь страны:");
        try {
            String strLong = scanner.nextLine();
            long square = Long.parseLong(strLong);
            if (square < 0) {
                country.setSquare(0);
                System.out.println("Установленно значение по умолчанию равное 0");
            } else {
                country.setSquare(square);
            }
        } catch (NumberFormatException e) {
            System.out.println("Значение не изменено");
        }

        System.out.println("Введите население страны:");
        try {
            String strInt = scanner.nextLine();
            int population = Integer.parseInt(strInt);
            if (population < 0) {
                country.setPopulation(0);
                System.out.println("Установленно значение по умолчанию равное 0");
            } else {
                country.setPopulation(population);
            }
        } catch (NumberFormatException e) {
            System.out.println("Значение не изменено");
        }
        jdbcTemplate.update("UPDATE country SET name = ?, square = ?, population = ?, continent = ? WHERE id = ?;",
                country.getName(), country.getSquare(), country.getPopulation(), country.getContinent(), find);
        System.out.println("Введите команду: ");
    }

    public void delete() {
        String sql = "delete from country where id = ?;";
        System.out.println("введите ID страны");
        int find = scanner.nextInt();
        jdbcTemplate.update(sql, find);
        System.out.println(RED + "Запись удалена ");
        System.out.println(RESET + "Введите команду: ");
    }

    public void showAll() {
        List<Country> country = jdbcTemplate.query("select * from country", new CountryMapper());
        for (Country x : country) {
            System.out.println(x);
        }
        System.out.println("Введите команду: ");

    }


}
