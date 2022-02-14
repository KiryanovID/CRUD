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
            resultSet = statement.executeQuery("Select * from country where id = "+ find +";");
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
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("введите ID страны");
        int find = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите название страны:");
        String name = scanner.nextLine().trim();
        if (!name.equals("")) {
            try {
                statement.execute("update country set name = '" + name + "' where id = " + find + ";");
            } catch (SQLException e) {
                System.out.println("ошибка в названии");
                e.printStackTrace();
            }
        }
        System.out.println("Введите континет на котором находится страна:");
        String conti = scanner.nextLine().toUpperCase(Locale.ROOT).trim();
        if (!conti.equals("")) {
            try {
                statement.execute("update country set continent = '" + conti + "' where id = " + find + ";");
            } catch (SQLException e) {
                System.out.println("ошибка в континенте");
            }
        }
        System.out.println("Введите площадь страны:");
        long square = scanner.nextLong();
        if (square != 0) {
            try {
                statement.execute("update country set square = " + square + " where id = " + find + ";");
            } catch (SQLException e) {
                System.out.println("ошибка в площади");
            }
        }
        System.out.println("Введите население страны:");
        int population = scanner.nextInt();
        if (population != 0) {
            try {
                statement.execute("update country set population = " + population + " where id = " + find + ";");
            } catch (SQLException e) {
                System.out.println("ошибка в населении");
            }
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
