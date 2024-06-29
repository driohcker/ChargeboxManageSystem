import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;

import UI.*;

public class Main {
    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;

    private static LoginUI loginUI = new LoginUI();
    private static RegisterUI registerUI = new RegisterUI();
    private static MainUI mainUI = new MainUI();
    private static RentalUI rentalUI = new RentalUI();
    private static ReturnUI returnUI = new ReturnUI();
    private static UserUI userUI = new UserUI();
    private static BillingUI billingUI = new BillingUI();
    private static BillingInfoUI billingInfoUI = new BillingInfoUI();

    public static void main(String[] args) {

        frame = new JFrame("充电宝租赁管理系统");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 添加登录和注册界面
        mainPanel.add(loginUI.createLoginUI(cardLayout,mainPanel), "login");
        mainPanel.add(registerUI.createRegisterUI(), "register");
        mainPanel.add(mainUI.createMainUI(),"main");
        // 添加主管理界面
        //mainPanel.add(createManagementPanel(), "management");

        frame.add(mainPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);


        // 显示登录界面
        cardLayout.show(mainPanel, "login");
        //cardLayout.show(mainPanel,"register");


    }
}
