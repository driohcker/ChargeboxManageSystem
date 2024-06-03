import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class test {

    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=充电宝租赁管理系统;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa";
    private static final String PASS = "Admin123";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // 注册 JDBC 驱动
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println("实例化Statement对象...");
            stmt = conn.createStatement();
            String sql = "SELECT UserID, UserName, Email FROM Users";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int id  = rs.getInt("UserID");
                String name = rs.getString("UserName");
                String email = rs.getString("Email");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", Name: " + name);
                System.out.println(", Email: " + email);
            }

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch(Exception se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if(stmt!=null) stmt.close();
            } catch(Exception se2) {}
            try {
                if(conn!=null) conn.close();
            } catch(Exception se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
