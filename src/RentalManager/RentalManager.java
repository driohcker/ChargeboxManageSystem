package RentalManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DatabaseConnection.DatabaseConnection;

/**
 * 此模块实现对事务的操作
 */

public class RentalManager {

    public void rentPowerBank(int userID, int powerBankID) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false); // 开始事务

        try {
            // 更新充电宝状态为不可用
            String updatePowerBankStatus = "UPDATE PowerBanks SET Status='Not Available' WHERE PowerBankID=?";
            PreparedStatement pstmt1 = conn.prepareStatement(updatePowerBankStatus);
            pstmt1.setInt(1, powerBankID);
            pstmt1.executeUpdate();

            // 创建租赁记录
            String createRental = "INSERT INTO Rentals (UserID, PowerBankID, StartTime, Status) VALUES (?, ?, GETDATE(), 'Active')";
            PreparedStatement pstmt2 = conn.prepareStatement(createRental);
            pstmt2.setInt(1, userID);
            pstmt2.setInt(2, powerBankID);
            pstmt2.executeUpdate();

            conn.commit(); // 提交事务
        } catch (SQLException e) {
            conn.rollback(); // 回滚事务
            throw e;
        } finally {
            conn.setAutoCommit(true); // 恢复自动提交
        }
    }

    public void returnPowerBank(int rentalID) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        conn.setAutoCommit(false); // 开始事务

        try {
            // 获取租赁记录
            String getRental = "SELECT PowerBankID FROM Rentals WHERE RentalID=?";
            PreparedStatement pstmt1 = conn.prepareStatement(getRental);
            pstmt1.setInt(1, rentalID);
            ResultSet rs = pstmt1.executeQuery();
            int powerBankID = 0;
            if (rs.next()) {
                powerBankID = rs.getInt("PowerBankID");
            }

            // 更新充电宝状态为可用
            String updatePowerBankStatus = "UPDATE PowerBanks SET Status='Available' WHERE PowerBankID=?";
            PreparedStatement pstmt2 = conn.prepareStatement(updatePowerBankStatus);
            pstmt2.setInt(1, powerBankID);
            pstmt2.executeUpdate();

            // 更新租赁记录为已完成
            String updateRentalStatus = "UPDATE Rentals SET EndTime=GETDATE(), Status='Completed' WHERE RentalID=?";
            PreparedStatement pstmt3 = conn.prepareStatement(updateRentalStatus);
            pstmt3.setInt(1, rentalID);
            pstmt3.executeUpdate();

            conn.commit(); // 提交事务
        } catch (SQLException e) {
            conn.rollback(); // 回滚事务
            throw e;
        } finally {
            conn.setAutoCommit(true); // 恢复自动提交
        }
    }

    public void settlePayment(int rentalID, double amount) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Payments (RentalID, Amount, PaymentTime) VALUES (?, ?, GETDATE())";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, rentalID);
        pstmt.setDouble(2, amount);
        pstmt.executeUpdate();
    }
}
