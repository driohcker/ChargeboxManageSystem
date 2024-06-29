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
//    充电宝类型表
//    int       type_id         充电宝类型id

//    String    type_name       充电宝类型名
//    int       capacity        容量
//    float     price_per_hour  价格（每小时租用价格）


    //增加充电宝类型方法
    public void addPowerBankType(String powerbank_type, int capacity, float price_per_hour) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO PowerBankType (powerbank_type, capacity, price_per_hour) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, powerbank_type);
        pstmt.setInt(2, capacity);
        pstmt.setFloat(3, price_per_hour);
        pstmt.executeUpdate();
    }

    //更新充电宝类型全部数据
    public void updatePowerBankType(int type_id, String powerbank_type, int capacity, float price_per_hour) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE PowerBankType SET powerbank_type=?, capacity=?, price_per_hour=? WHERE type_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, powerbank_type);
        pstmt.setInt(2, capacity);
        pstmt.setFloat(3, price_per_hour);
        pstmt.setInt(4,type_id);
        pstmt.executeUpdate();
    }

    //删除充电宝类型数据
    public void deletePowerBankType(int type_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM PowerBankType WHERE type_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, type_id);
        pstmt.executeUpdate();
    }

    //获取充电宝类型数据
    public ResultSet getPowerBankType(int type_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM PowerBankType WHERE type_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, type_id);
        return pstmt.executeQuery();
    }
}
