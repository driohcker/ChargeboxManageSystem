import PowerBankTypeManager.PowerBankTypeManager;
import DealingManager.Rental_Dealing_Manager;
import UserManager.UserManager;
import PowerBankManager.PowerBankManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            UserManager userManager = new UserManager();
            userManager.addUser("JohnDoe", "password123", "johndoe@example.com", "1234567890");

            PowerBankTypeManager powerBankTypeManager = new PowerBankTypeManager();
            powerBankTypeManager.addPowerBankType("Type A", "Description for Type A");

            PowerBankManager powerBankManager = new PowerBankManager();
            powerBankManager.addPowerBank(1, "Location1", "Available", 100);

            Rental_Dealing_Manager rentalDealingManager = new Rental_Dealing_Manager();
            rentalDealingManager.rentPowerBank(1, 1);
            rentalDealingManager.returnPowerBank(1);
            rentalDealingManager.settlePayment(1, 10.00);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
