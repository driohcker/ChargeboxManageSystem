package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 此模块实现对数据库的连接
 */

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=充电宝租赁系统;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASS = "Admin123";
    private static Connection connection = null;

    public DatabaseConnection() throws SQLException, ClassNotFoundException {
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
        }
        return connection;
    }

}
