package DealingManager;
import DatabaseConnection.DatabaseConnection;

import java.sql.*;

public class RentalManager {

//    int           rental_id       租用事务序号
//    int           user_id         用户序号
//    int           powerbank_id    充电宝序号
//    Timestamp     rental_start    租用开始时间
//    Timestamp     rental_end      租用结束时间

    public void addRental(int user_id, int powerbank_id, Timestamp rental_start) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Rental (user_id, powerbank_id, rental_start, rental_end) VALUES (?, ?, ?, NOW())";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        pstmt.setInt(2,powerbank_id);
        pstmt.setTimestamp(3, rental_start);
        //pstmt.setTimestamp(3, rental_end);
        pstmt.executeUpdate();
    }
    public void updateRental(int rental_id, int user_id, int powerbank_id, Timestamp rental_start, Timestamp rental_end) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE Rental SET user_id=?, powerbank_id=?, rental_start=?, rental_end WHERE rental_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, user_id);
        pstmt.setInt(2,powerbank_id);
        pstmt.setTimestamp(3, rental_start);
        pstmt.setTimestamp(4, rental_end);
        pstmt.executeUpdate();
    }
    public void deleteRental(int rental_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Rental WHERE rental_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, rental_id);
        pstmt.executeUpdate();
    }
    public ResultSet getRental(int rental_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Rental WHERE rental_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, rental_id);
        return pstmt.executeQuery();
    }
}
