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

//    int       type_id         类型序号
//    String    type_name       类型名
//    int       capacity        电量
//    float     price_per_hour  价格（每小时租用价格）
//    float     price_per_day   价格（每天租用价格）

    public void addPowerBankType(String type_name, int capacity, float price_per_hour,float price_per_day) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO PowerBankType (type_name,capacity,price_per_hour,price_per_day) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, type_name);
        pstmt.setInt(2, capacity);
        pstmt.setFloat(3, price_per_hour);
        pstmt.setFloat(4, price_per_day);
        pstmt.executeUpdate();
    }

    public void updatePowerBankType(int type_id, String type_name, int capacity, float price_per_hour, float price_per_day) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE PowerBankType SET type_name=?, capacity=?, price_per_hour=? ,price_per_day=? WHERE TypeID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, type_name);
        pstmt.setInt(2, capacity);
        pstmt.setFloat(3, price_per_hour);
        pstmt.setFloat(4,price_per_day);
        pstmt.setInt(5,type_id);
        pstmt.executeUpdate();
    }

    public void deletePowerBankType(int type_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM PowerBankType WHERE TypeID=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, type_id);
        pstmt.executeUpdate();
    }

    public ResultSet getPowerBankType(int type_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM PowerBankType WHERE Type_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, type_id);
        return pstmt.executeQuery();
    }
}
