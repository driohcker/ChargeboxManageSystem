package BillingManager;

import DatabaseConnection.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;

public class BillingManager {
//    订单表
//    int           billing_id      订单id

//    int           user_id         用户id
//    int           powerbank_id    充电宝id
//    String        type_name       充电宝类型名
//    Timestamp     rental_start    租借时间
//    Timestamp     return_time     归还时间
//    float         total_price     总价格


    //增加订单数据
    //输入用户id，充电宝id，充电宝类型名，租借时间，归还时间，总价格
    //归还时间默认为现在
    public void addBilling(int user_id, int powerbank_id, String type_name, Timestamp rental_start, float total_price) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Billing (user_id, powerbank_id, powerbank_type, rental_start, return_time, float total_price) VALUES (?, ?, ?, ?, NOW(), ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        pstmt.setInt(2, powerbank_id);
        pstmt.setString(3, type_name);
        pstmt.setTimestamp(4, rental_start);
        pstmt.setFloat(5, total_price);
        pstmt.executeUpdate();
    }

    //更新订单全部数据
    public void updateBilling(int billing_id,int user_id, int powerbank_id, String type_name, Timestamp rental_start, Timestamp return_time, float total_price) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE Rental SET user_id=?, powerbank_id=?, powerbank_type=?, rental_start=?, return_time=?, float taotal_price=? WHERE billing_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        pstmt.setInt(2, powerbank_id);
        pstmt.setString(3, type_name);
        pstmt.setTimestamp(4, rental_start);
        pstmt.setTimestamp(5, return_time);
        pstmt.setFloat(6, total_price);
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

    //输入用户id和数据列名查找该数据
    public String getBillingData(int billing_id, String dataName) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT ? FROM Billing WHERE user_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1,dataName);
        pstmt.setInt(2, billing_id);
        return String.valueOf(pstmt.executeUpdate());
    }

    public static void main(String[] args) {
        //new BillingManager().getBillingData(1,);
    }
}
