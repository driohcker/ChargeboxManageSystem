package Init;

import DatabaseConnection.*;
import DealingManager.*;
import PowerBankManager.*;
import UserManager.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Init {
    public void initDatabase() throws SQLException {
        // 初始化数据库连接
        Connection connection = DatabaseConnection.getConnection();

        // 创建数据库
        String sqlstr = "CREATE DATABASE IF NOT EXISTS PowerBankManagement";
        PreparedStatement pstmt = connection.prepareStatement(sqlstr);
        pstmt.executeUpdate();

        // 使用数据库
        sqlstr = "USE PowerBankManagement";
        pstmt = connection.prepareStatement(sqlstr);
        pstmt.executeUpdate();

        // 创建数据库类型表
        sqlstr = "CREATE TABLE IF NOT EXISTS PowerBankType (" +
                "    type_id INT AUTO_INCREMENT PRIMARY KEY," +
                "    type_name VARCHAR(50) NOT NULL," +
                "    capacity INT NOT NULL," +
                "    price_per_hour DECIMAL(10, 2) NOT NULL," +
                "    price_per_day DECIMAL(10, 2) NOT NULL" +
                ");";
        pstmt = connection.prepareStatement(sqlstr);
        pstmt.executeUpdate();

        // 插入充电宝类型数据
        sqlstr = "INSERT INTO PowerBankType (type_name, capacity, price_per_hour, price_per_day)" +
                "VALUES " +
                "('Standard', 10000, 1.50, 15.00)," +
                "('Premium', 20000, 2.50, 25.00)," +
                "('Ultra', 30000, 3.50, 35.00)" +
                "ON DUPLICATE KEY UPDATE type_name=VALUES(type_name);";
        pstmt = connection.prepareStatement(sqlstr);
        pstmt.executeUpdate();

        // 创建充电宝表
        sqlstr = "CREATE TABLE IF NOT EXISTS PowerBank (" +
                "    powerbank_id INT AUTO_INCREMENT PRIMARY KEY," +
                "    type_id INT," +
                "    status ENUM('available', 'rented', 'maintenance') DEFAULT 'available'," +
                "    FOREIGN KEY (type_id) REFERENCES PowerBankType(type_id)" +
                ");";
        pstmt = connection.prepareStatement(sqlstr);
        pstmt.executeUpdate();

        //插入充电宝数据
        sqlstr = "INSERT INTO PowerBank (type_id, status)" +
                "VALUES " +
                "(1, 'available')," +
                "(1, 'available')," +
                "(2, 'available')," +
                "(3, 'available');";
        pstmt = connection.prepareStatement(sqlstr);
        pstmt.executeUpdate();

        //创建用户表
        sqlstr = "CREATE TABLE User (" +
                "    user_id INT AUTO_INCREMENT PRIMARY KEY," +
                "    username VARCHAR(50) NOT NULL," +
                "    email VARCHAR(100) NOT NULL," +
                "    phone VARCHAR(15) NOT NULL," +
                "    password VARCHAR(100) NOT NULL," +
                "    balance DECIMAL(10, 2) DEFAULT 0.00" +
                ");";
        pstmt = connection.prepareStatement(sqlstr);
        pstmt.executeUpdate();

        //插入用户数据
        
    }

}
