package old;

import java.sql.*;

public class ConnectionDB {
    private static Connection conn;
    private ResultSet resSet;
    private static final String BDUrl = "jdbc:sqlite:./test.db3";


    private ConnectionDB() {
    }

    public static Connection getInstance() {
        if (conn == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection(BDUrl);
            } catch (ClassNotFoundException e) {
                System.out.println("Не найден драйвер подключения к БД");
            } catch (SQLException e) {
                System.out.println("Ошибка подключения к БД");
            }
        }
        return conn;
    }

}
