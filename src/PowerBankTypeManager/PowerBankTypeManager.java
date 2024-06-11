package PowerBankTypeManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DatabaseConnection.DatabaseConnection;

/**
 * 此模块实现对充电宝类型的管理
 */

public class PowerBankTypeManager {

    public void addPowerBankType(String typeName, String description) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO PowerBankTypes (TypeName, Description) VALUES (?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, typeName);
        pstmt.setString(2, description);
        pstmt.executeUpdate();
    }

    public void updatePowerBankType(int typeID, String typeName, String description) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE PowerBankTypes SET TypeName=?, Description=? WHERE TypeID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, typeName);
        pstmt.setString(2, description);
        pstmt.setInt(3, typeID);
        pstmt.executeUpdate();
    }

    public void deletePowerBankType(int typeID) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM PowerBankTypes WHERE TypeID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, typeID);
        pstmt.executeUpdate();
    }

    public ResultSet getPowerBankType(int typeID) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM PowerBankTypes WHERE TypeID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, typeID);
        return pstmt.executeQuery();
    }
}
