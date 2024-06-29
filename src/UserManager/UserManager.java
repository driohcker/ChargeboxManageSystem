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
//    用户表
//    int       user_id     用户id

//    String    username    用户名
//    String    password    用户密码
//    String    email       用户邮箱
//    String    phone       用户电话
//    float     balance     用户余额


    //添加用户
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

    //一次性更新所有数据
    public void updateUserAll(int user_id, String username, String password, String email, String phone, float balance) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE User SET username=?, password=?, email=?, phone=?, balance=? WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        pstmt.setString(3, email);
        pstmt.setString(4, phone);
        pstmt.setFloat(5, balance);
        pstmt.setInt(6, user_id);
        pstmt.executeUpdate();
    }

    //更新用户名数据
    public void updateUserUsername(int user_id, String username) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE User SET username=? WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, username);
        pstmt.setInt(2, user_id);
        pstmt.executeUpdate();
    }

    //更新密码数据
    public void updateUserPassword(int user_id, String password) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE User SET password=? WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, password);
        pstmt.setInt(2, user_id);
        pstmt.executeUpdate();
    }

    //更新Email数据
    public void updateUserEmail(int user_id, String email) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE User SET email=? WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, email);
        pstmt.setInt(2, user_id);
        pstmt.executeUpdate();
    }

    //更新Phone数据
    public void updateUserPhone(int user_id, String phone) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE User SET phone=? WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, phone);
        pstmt.setInt(2, user_id);
        pstmt.executeUpdate();
    }

    //更新Balance
    public void updateUserBalance(int user_id, float balance) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE User SET balance=? WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setFloat(1, balance);
        pstmt.setInt(2, user_id);
        pstmt.executeUpdate();
    }

    //删除用户
    public void deleteUser(int user_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM User WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        pstmt.executeUpdate();
    }

    //输入用户id查询所有数据
    public ResultSet getUser(int user_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM User WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        return pstmt.executeQuery();
    }

    //输入用户id和数据列名查找该数据
    public String getUserData(int user_id, String dataName) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT ? FROM User WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,dataName);
        pstmt.setInt(2, user_id);
        return String.valueOf(pstmt.executeUpdate());
    }

}
