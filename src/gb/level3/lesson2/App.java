package gb.level3.lesson2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
    private static final String DB_DRIVER = "org.sqlite.JDBC";
    private static final String DB_CONNECTION = "jdbc:sqlite:goods.db";
    private static final String DB_USER = "user";
    private static final String DB_PASSWORD = "password";
    private static final String CREATE_TABLE_PRODUCTS_SQL =
              "CREATE TABLE IF NOT EXISTS PRODUCTS(\n"
            + "ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n"
            + "PROD_ID INTEGER NOT NULL,\n"
            + "PROD_TITLE TEXT NOT NULL,\n"
            + "PROD_COST INTEGER NOT NULL\n"
            + ")";

    public static void main(String[] args) throws SQLException{
        createTableSQL(CREATE_TABLE_PRODUCTS_SQL);
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
            System.out.println(DB_DRIVER + " driver Registered!");
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC driver " + DB_DRIVER + "not found");
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER,DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            System.out.println(e.getMessage());
        }

        if (dbConnection != null) {
            System.out.println("Connection status \"OK\". Take control...");
        } else {
            System.out.println("Failed to make connection");
        }
        return dbConnection;
    }

    private static void createTableSQL(String sql) throws SQLException {
        Statement statement = null;
        Connection dbConnection = getDBConnection();
        try {
            statement = dbConnection.createStatement();
            System.out.println("\nTry to execute the SQL query: \n" + sql);
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println("Something wrong with SQL expression...");
            e.printStackTrace();
        } finally {
            if (statement != null) statement.close();
            if (dbConnection != null) dbConnection.close();
        }
    }
}