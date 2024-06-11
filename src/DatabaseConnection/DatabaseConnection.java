package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 此模块实现对数据库的连接
 */

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=YourDatabase;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "yourusername";
    private static final String PASS = "yourpassword";
    private static Connection connection = null;

    private DatabaseConnection() {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        return connection;
    }
}