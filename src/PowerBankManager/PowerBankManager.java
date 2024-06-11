package PowerBankManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DatabaseConnection.DatabaseConnection;

/**
 * 此模块实现对充电宝的增删查的操作
 */

public class PowerBankManager {

    public void addPowerBank(int typeID, String location, String status, int batteryLevel) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO PowerBanks (TypeID, Location, Status, BatteryLevel) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, typeID);
        pstmt.setString(2, location);
        pstmt.setString(3, status);
        pstmt.setInt(4, batteryLevel);
        pstmt.executeUpdate();
    }

    public void updatePowerBank(int powerBankID, int typeID, String location, String status, int batteryLevel) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE PowerBanks SET TypeID=?, Location=?, Status=?, BatteryLevel=? WHERE PowerBankID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, typeID);
        pstmt.setString(2, location);
        pstmt.setString(3, status);
        pstmt.setInt(4, batteryLevel);
        pstmt.setInt(5, powerBankID);
        pstmt.executeUpdate();
    }

    public void deletePowerBank(int powerBankID) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM PowerBanks WHERE PowerBankID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, powerBankID);
        pstmt.executeUpdate();
    }

    public ResultSet getPowerBank(int powerBankID) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM PowerBanks WHERE PowerBankID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, powerBankID);
        return pstmt.executeQuery();
    }
}
