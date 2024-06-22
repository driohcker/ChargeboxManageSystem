import PowerBankTypeManager.PowerBankTypeManager;
import DealingManager.Rental_Dealing_Manager;
import UserManager.UserManager;
import PowerBankManager.PowerBankManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            UserManager userManager = new UserManager();
            // 假设第五个参数是用户的余额
            userManager.addUser("JohnDoe", "password123", "johndoe@example.com", "1234567890", 100.0f);

            PowerBankTypeManager powerBankTypeManager = new PowerBankTypeManager();
            // 提供所有必要的参数
            powerBankTypeManager.addPowerBankType("Type A", 10000, 2.0f, 10.0f);

            // ... 其他代码 ...

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
