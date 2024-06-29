package ReturnManager;

import DatabaseConnection.DatabaseConnection;

import java.sql.*;

public class ReturnManager {
//    归还表
//    int           return_id       归还事务id

//    int           rental_id       租用事务id
//    Timestamp     return_time     归还时间


    //增加归还数据
    //输入租借id和归还时间
    //归还时间默认为现在
    public void addReturn(int rental_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "INSERT INTO Returns (rental_id, return_time) VALUES (?, NOW())";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, rental_id);
        pstmt.executeUpdate();
    }

    //更新归还时间为当前时间
    public void updateReturn(int return_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "UPDATE Returns SET return_time=CURRENT_TIMESTAMP WHERE return_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, return_id);
        pstmt.executeUpdate();
    }

    //删除归还数据
    public void deleteReturn(int return_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "DELETE FROM Returns WHERE return_id";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, return_id);
        pstmt.executeUpdate();
    }

    //获取归还数据
    public ResultSet getReturn(int return_id) throws SQLException, ClassNotFoundException {
        Connection conn = DatabaseConnection.getConnection();
        String sql = "SELECT * FROM Returns WHERE return_id=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, return_id);
        return pstmt.executeQuery();
    }

}
