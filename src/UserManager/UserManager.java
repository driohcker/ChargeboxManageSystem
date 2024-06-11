package UserManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DatabaseConnection.DatabaseConnection;

/**
 * 此模块实现对用户的增删查改操作
 */

public class UserManager {

    public void addUser(String userName, String password, String email, String phoneNumber) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Users (UserName, Password, Email, PhoneNumber) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userName);
        pstmt.setString(2, password);
        pstmt.setString(3, email);
        pstmt.setString(4, phoneNumber);
        pstmt.executeUpdate();
    }

    public void updateUser(int userID, String userName, String password, String email, String phoneNumber) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE Users SET UserName=?, Password=?, Email=?, PhoneNumber=? WHERE UserID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userName);
        pstmt.setString(2, password);
        pstmt.setString(3, email);
        pstmt.setString(4, phoneNumber);
        pstmt.setInt(5, userID);
        pstmt.executeUpdate();
    }

    public void deleteUser(int userID) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Users WHERE UserID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userID);
        pstmt.executeUpdate();
    }

    public ResultSet getUser(int userID) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Users WHERE UserID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, userID);
        return pstmt.executeQuery();
    }
}
