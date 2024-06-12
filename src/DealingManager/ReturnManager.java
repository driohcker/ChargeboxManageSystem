package DealingManager;

import DatabaseConnection.DatabaseConnection;

import java.sql.*;

public class ReturnManager {

//    int           return_id       归还事务序号
//    int           rental_id       租用事务序号
//    Timestamp     return_time     归还时间

    public void addReturn(int rental_id, Timestamp rental_time) throws SQLException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Return (rental_id, return_time) VALUES (?, NOW())";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, rental_id);
        //pstmt.setTimestamp(2, rental_time);
        pstmt.executeUpdate();
    }
    public void updateReturn(int return_id, int rental_id, Timestamp return_time)throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE Return SET rental_id=?, return_time=? WHERE retrun_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1,rental_id);
        pstmt.setTimestamp(2,return_time);
        pstmt.setInt(3,return_id);
        pstmt.executeUpdate();
    }
    public void deleteReturn(int return_id) throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Return WHERE return_id";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, return_id);
        pstmt.executeUpdate();
    }
    public ResultSet getReturn(int return_id) throws SQLException{
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Return WHERE return_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, return_id);
        return pstmt.executeQuery();
    }

}
