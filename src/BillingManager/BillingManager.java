package BillingManager;

import DatabaseConnection.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;

public class BillingManager {
//    订单表
//    int           billing_id      订单id

//    int           user_id         用户id
//    int           powerbank_id    充电宝id
//    String        powerbank_type  充电宝类型名
//    Timestamp     rental_start    租借时间
//    Timestamp     return_time     归还时间
//    float         price           总价格


    //增加订单数据
    //输入用户id，充电宝id，充电宝类型名，租借时间，归还时间，总价格
    //归还时间默认为现在
    public void addBilling(int user_id, int powerbank_id, String powerbank_type, Timestamp rental_start, float price) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Billing (user_id, powerbank_id, powerbank_type, rental_start, return_time, float price) VALUES (?, ?, ?, ?, NOW(), ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        pstmt.setInt(2, powerbank_id);
        pstmt.setString(3, powerbank_type);
        pstmt.setTimestamp(4, rental_start);
        pstmt.setFloat(5, price);
        pstmt.executeUpdate();
    }

    //更新订单全部数据
    public void updateBilling(int billing_id,int user_id, int powerbank_id, String powerbank_type, Timestamp rental_start, Timestamp return_time, float price) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE Rental SET user_id=?, powerbank_id=?, powerbank_type=?, rental_start=?, return_time=?, float price=? WHERE billing_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        pstmt.setInt(2, powerbank_id);
        pstmt.setString(3, powerbank_type);
        pstmt.setTimestamp(4, rental_start);
        pstmt.setTimestamp(5, return_time);
        pstmt.setFloat(6, price);
        pstmt.setInt(7, billing_id);
        pstmt.executeUpdate();
    }


    //删除数据
    public void deleteBilling(int billing_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Billing WHERE billing_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, billing_id);
        pstmt.executeUpdate();
    }

    //获取订单数据
    public ResultSet getBilling(int billing_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Billing WHERE billing_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, billing_id);
        return pstmt.executeQuery();
    }
}
