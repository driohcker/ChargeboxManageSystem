package PowerBankManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DatabaseConnection.DatabaseConnection;

public class PowerBankManager {

//    int       powerbank_id    充电宝序号
//    int       type_id         充电表类型序号
//    String    status          充电表租赁状态

    public void addPowerBank(int powerbank_id, int type_id, Status status) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO PowerBank (powerbank_id, type_id, status) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, powerbank_id);
        pstmt.setInt(2, type_id);
        pstmt.setString(3, status.getStatus());
        pstmt.executeUpdate();
    }

    public void updatePowerBank(int powerbank_id, int type_id, Status status) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE PowerBank SET type_id=?, status=? WHERE PowerBankID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, type_id);
        pstmt.setString(2, status.getStatus());
        pstmt.setInt(3, powerbank_id);
        pstmt.executeUpdate();
    }

    public void deletePowerBank(int powerbank_id) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM PowerBank WHERE powerbank_id";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, powerbank_id);
        pstmt.executeUpdate();
    }

    public ResultSet getPowerBank(int powerbank_id) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM PowerBank WHERE powerbank_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, powerbank_id);
        return pstmt.executeQuery();
    }
}
