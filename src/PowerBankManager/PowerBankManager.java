package PowerBankManager;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import DatabaseConnection.DatabaseConnection;

public class PowerBankManager {
//    充电宝表
//    int       powerbank_id    充电宝id

//    int       type_id         充电表类型id
//    String    status          充电表租赁状态
//    int       capacity_left   充电宝电量


    //增加充电宝数据
    public void addPowerBank(int type_id, int capacity_left, Status status) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO PowerBank (type_id, capacity_left, status) VALUES (?, ?, ?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, type_id);
        pstmt.setInt(2, capacity_left);
        pstmt.setString(3, status.getStatus());
        pstmt.executeUpdate();
    }

    //更新充电宝全部数据
    //输入充电宝id，充电宝类型id，充电宝电量，充电宝状态
    public void updatePowerBank(int powerbank_id, int type_id, int capacity_left, Status status) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE PowerBank SET type_id=?, capacity_left=?, status=? WHERE powerbank_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, type_id);
        pstmt.setInt(2, capacity_left);
        pstmt.setString(3, status.getStatus());
        pstmt.setInt(4, powerbank_id);
        pstmt.executeUpdate();
    }

    //更新充电宝电量数据
    public void updatePowerBankCapacity(int powerbank_id, int capacity_left) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE PowerBank SET capacity_left=? WHERE powerbank_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, capacity_left);
        pstmt.setInt(2, powerbank_id);
        pstmt.executeUpdate();
    }

    //更新充电宝状态数据
    public void updatePowerBankStatus(int powerbank_id, Status status) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE PowerBank SET status=? WHERE powerbank_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, status.getStatus());
        pstmt.setInt(2, powerbank_id);
        pstmt.executeUpdate();
    }

    //删除充电宝数据
    public void deletePowerBank(int powerbank_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM PowerBank WHERE powerbank_id";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, powerbank_id);
        pstmt.executeUpdate();
    }

    //获取充电宝数据
    public ResultSet getPowerBank(int powerbank_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM PowerBank WHERE powerbank_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, powerbank_id);
        return pstmt.executeQuery();
    }

    public String getPowerbankData(int powerbank_id, String dataName) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT " + dataName + " FROM PowerBank WHERE powerbank_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, powerbank_id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            return rs.getString(1); // assuming the column data is of type String
        } else {
            return null; // or throw an exception if the powerbank_id is not found
        }
    }

}
