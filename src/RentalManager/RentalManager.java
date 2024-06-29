package RentalManager;
import DatabaseConnection.DatabaseConnection;

import java.sql.*;

public class RentalManager {
//    租借表
//    int           rental_id       租借id

//    int           user_id         用户id
//    int           powerbank_id    充电宝id
//    String        powerbank_type  充电宝类型
//    Timestamp     rental_start    租用开始时间
//    float         price_per_hour  每小时价格


    //添加租借数据,时间为现在
    public void addRental(int user_id, int powerbank_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Rental (user_id, powerbank_id, rental_start, rental_end) VALUES (?, ?, NOW())";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        pstmt.setInt(2,powerbank_id);
        pstmt.executeUpdate();
    }

    //更新租借全部数据
    public void updateRentalAll(int rental_id, int user_id, int powerbank_id, String powerbank_type, Timestamp rental_start, float price_per_hour) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE Rental SET user_id=?, powerbank_id=?, rental_start=?, price_per_hour WHERE rental_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        pstmt.setInt(2,powerbank_id);
        pstmt.setTimestamp(3, rental_start);
        pstmt.setFloat(4, price_per_hour);
        pstmt.setInt(5, rental_id);
        pstmt.executeUpdate();
    }

    //更新租借时间数据
    public void updateRental(int rental_id, Timestamp rental_start) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE Rental SET rental_start=? WHERE rental_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setTimestamp(1, rental_start);
        pstmt.setInt(2, rental_id);
        pstmt.executeUpdate();
    }

    //删除租借数据
    public void deleteRental(int rental_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Rental WHERE rental_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, rental_id);
        pstmt.executeUpdate();
    }

    //获取租借数据,输出为一个结果集
    public ResultSet getRental(int rental_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Rental WHERE rental_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, rental_id);
        return pstmt.executeQuery();
    }


}
