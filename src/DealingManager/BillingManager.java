package DealingManager;

import DatabaseConnection.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.*;

public class BillingManager {

//    int           billing_id      费用结算事务序号
//    int           rental_id       租用事务序号
//    BigDecimal    amount
//    Timestamp     billing_time    费用结算事务结算时间

    public void addBilling(int rental_id, BigDecimal amount, Timestamp billing_time) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Billing (rental_id, amount, billing_time) VALUES (?, ?, NOW())";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, rental_id);
        pstmt.setBigDecimal(2, amount);
        //pstmt.setTimestamp(3, billing_time);
        pstmt.executeUpdate();
    }
    public void updateBilling(int billing_id,int rental_id,BigDecimal amount, Timestamp billing_time) throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE Rental SET rental_id=?, amount=?, billing_time=? WHERE billing_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, rental_id);
        pstmt.setBigDecimal(2,amount);
        pstmt.setTimestamp(3, billing_time);
        pstmt.setInt(4, billing_id);
        pstmt.executeUpdate();
    }
    public void deleteBilling(int billing_id) throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Billing WHERE billing_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, billing_id);
        pstmt.executeUpdate();
    }
    public ResultSet getBilling(int billing_id) throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Billing WHERE billing_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, billing_id);
        return pstmt.executeQuery();
    }
}
