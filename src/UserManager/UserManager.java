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

//    int       user_id     用户序号
//    String    username    用户名
//    String    password    用户密码
//    String    email       用户邮箱
//    String    phone       用户电话
//    float     balance     用户余额

    public void addUser(String username, String password, String email, String phone, float balance) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO User (username, password, email, phone, balanece) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.setString(3, email);
        pstmt.setString(4, phone);
        pstmt.setFloat(5, balance);
        pstmt.executeUpdate();
    }

    public void updateUser(int user_id, String username, String password, String email, String phone, float balance) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE User SET username=?, password=?, email=?, phone=? WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.setString(3, email);
        pstmt.setString(4, phone);
        pstmt.setFloat(5, balance);
        pstmt.setInt(6, user_id);
        pstmt.executeUpdate();
    }

    public void deleteUser(int user_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM User WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        pstmt.executeUpdate();
    }

    public ResultSet getUser(int user_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM User WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        return pstmt.executeQuery();
    }
}
