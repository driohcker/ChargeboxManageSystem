import javax.swing.*;
import java.awt.*;
import UI.LoginUI;
public class Main {
    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;
    private static LoginUI loginUI = new LoginUI();


    public static void main(String[] args) {

        frame = new JFrame("充电宝租赁管理系统");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 添加登录和注册界面
        mainPanel.add(loginUI.createLoginUI(), "login");
        //mainPanel.add(createRegisterPanel(), "register");

        // 添加主管理界面
        //mainPanel.add(createManagementPanel(), "management");

        frame.add(mainPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //manager1.addPowerBank(1, 92, Status.AVAILABLE);
        //System.out.println(manager1.getPowerbankData(13,"capacity_left"));


        // 显示登录界面
        cardLayout.show(mainPanel, "login");
    }
}
