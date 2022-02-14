import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;

public class CRUD {
    private final Connection conn = ConnectionDB.getInstance();
    private Statement statement;
    private ResultSet resultSet;
    private int count = 0;
    private final Scanner scanner = new Scanner(System.in);

    public CRUD() {
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("Select max(id) as id from country;");
            count = resultSet.getInt("id");
        } catch (SQLException e) {
            System.out.println("Что то пошло не так при инициализации класса CRUD");
            e.printStackTrace();
        }
    }

    public void create() {
        try {
            System.out.println("Введите название страны:");
            String name = scanner.nextLine().trim();
            System.out.println("Введите континет на котором находится страна:");
            String conti = scanner.nextLine().toUpperCase(Locale.ROOT).trim();
            Continent continent = Continent.valueOf(conti);
            System.out.println("Введите площадь страны:");
            long square = scanner.nextLong();
            System.out.println("Введите население страны:");
            int population = scanner.nextInt();
            count++;
            int id = count;
            statement = conn.createStatement();
            statement.execute("INSERT INTO 'country'('id', 'name', 'square', 'population', 'continent') " +
                    "VALUES('" + id + "', '" + name + "','" + square + "','" + population + "','" + continent + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Введите команду: ");
    }

    public void read() {
        System.out.println("введите ID страны");
        int find = scanner.nextInt();
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("Select * from country where id = " + find + ";");
            String name = resultSet.getString("name");
            long square = resultSet.getLong("square");
            int population = resultSet.getInt("population");
            String continent = resultSet.getString("continent");
            System.out.println("Страна " + name + "\n" +
                    "площадь " + square + "кв.км\n" +
                    "население " + population + "чел.\n" +
                    "континет " + continent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Введите команду: ");

    }

    public void update() {
        Country tempCountry = null;
        System.out.println("введите ID страны");
        int find = scanner.nextInt();
        scanner.nextLine();
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("Select * from country where id = " + find + ";");
            String name = resultSet.getString("name");
            long square = resultSet.getLong("square");
            int population = resultSet.getInt("population");
            String continent = resultSet.getString("continent");
            Continent tempContinent = Continent.valueOf(continent);
            tempCountry = new Country(name, square, population, tempContinent);
        } catch (SQLException e) {
            System.out.println("exception");
        }

        System.out.println("Введите название страны:");
        String name = scanner.nextLine().trim();
        if (!name.equals("")) {
            tempCountry.setName(name);
        }
        System.out.println("Введите континет на котором находится страна:");
        String conti = scanner.nextLine().toUpperCase(Locale.ROOT).trim();
        if (!conti.equals("")) {
            try {
                Continent tempContinent = Continent.valueOf(conti);
                tempCountry.setContinent(tempContinent);
            } catch (IllegalArgumentException e) {
                System.out.println("нет такого континета, изменения не внесены");
            }
        }
        System.out.println("Введите площадь страны:");
        try {
            String strLong = scanner.nextLine();
            long square = Long.parseLong(strLong);
            if (square < 0) {
                tempCountry.setSquare(0);
                System.out.println("Установленно значение по умолчанию равное 0");
            } else {
                tempCountry.setSquare(square);
            }
        } catch (NumberFormatException e) {
            System.out.println("Значение не изменено");
        }

        System.out.println("Введите население страны:");
        try {
            String strInt = scanner.nextLine();
            int population = Integer.parseInt(strInt);
            if (population < 0) {
                tempCountry.setPopulation(0);
                System.out.println("Установленно значение по умолчанию равное 0");
            } else {
                tempCountry.setPopulation(population);
            }
        } catch (NumberFormatException e) {
            System.out.println("Значение не изменено");
        }
        try {
            statement = conn.createStatement();
            statement.execute("UPDATE country " +
                    "SET name = '" + tempCountry.getName() + "', " +
                    "square = '" + tempCountry.getSquare() + "', " +
                    "population = '" + tempCountry.getPopulation() + "', " +
                    "continent = '" + tempCountry.getContinent() + "'" +
                    "WHERE id = " + find + ";");
        } catch (SQLException e) {
            System.out.println("Ошибка записи в БД");
        }
        System.out.println("Введите команду: ");
    }

    public void delete() {
        System.out.println("введите ID страны");
        int find = scanner.nextInt();
        try {
            statement = conn.createStatement();
            statement.execute("delete from country where id = " + find + ";");
            System.out.println("Введите команду: ");

        } catch (SQLException e) {
            System.out.println("Что то пошло не так в методе delete");
            e.printStackTrace();
        }
    }

    public void showAll() {
        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery("Select * from country;");
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                long square = resultSet.getLong("square");
                int population = resultSet.getInt("population");
                String continent = resultSet.getString("continent");
                System.out.println("Страна " + name + "\n" +
                        "площадь " + square + "кв.км\n" +
                        "население " + population + "чел.\n" +
                        "континет " + continent);
                System.out.println();
            }
            System.out.println("Введите команду: ");
        } catch (SQLException e) {
            System.out.println("что то пошло не так при выполнении метода showAllFromDB");
        }
    }


}
