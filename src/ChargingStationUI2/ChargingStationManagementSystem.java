package ChargingStationUI2;
import DatabaseConnection.DatabaseConnection;
import PowerBankManager.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import DatabaseConnection.*;
import static PowerBankManager.Status.AVAILABLE;

public class ChargingStationManagementSystem {
    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;

    private static Map<String, String> users = new HashMap<>(); // 用户名-密码映射
    private static String currentUser;
    private static ChargingStationManager manager = new ChargingStationManager(); // 全局的manager



    public static void main(String[] args) throws ClassNotFoundException {
        frame = new JFrame("充电宝租赁管理系统");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // 添加登录和注册界面
        mainPanel.add(createLoginPanel(), "login");
        mainPanel.add(createRegisterPanel(), "register");

        // 添加主管理界面
        mainPanel.add(createManagementPanel(), "management");

        frame.add(mainPanel);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // 示例数据
        manager.addPowerBankType("普通充电宝", 1.0);
        manager.addPowerBankType("快速充电宝", 1.5);
        manager.addPowerBank("PB001", "普通充电宝");
        manager.addPowerBank("PB002", "快速充电宝");
        manager.registerUser("user1", "pass1");

        // 显示登录界面
        cardLayout.show(mainPanel, "login");
    }

    private static JPanel createLoginPanel() throws ClassNotFoundException {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel userLabel = new JLabel("用户名");
        JLabel passwordLabel = new JLabel("密码");
        JTextField userField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");



        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);


        try {
            PowerBankManager manager = new PowerBankManager();
            manager.addPowerBank(2, Status.AVAILABLE);
            ResultSet rs = manager.getPowerBank(1);

            // 展开结果集数据库
            while (rs.next()) {
                // 通过字段检索
                int powerbank_id  = rs.getInt("powerbank_id");
                int type_id = rs.getInt("type_id");
                String status = rs.getString("status");

                // 输出数据
                System.out.print("powerbank_id: " + powerbank_id);
                System.out.print(", type_id: " + type_id);
                System.out.println(", status: " + status);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());
                if (users.containsKey(username) && users.get(username).equals(password)) {
                    currentUser = username;
                    cardLayout.show(mainPanel, "management");
                } else {
                    JOptionPane.showMessageDialog(frame, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "register");
            }
        });

        return panel;
    }

    private static JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel userLabel = new JLabel("用户名");
        JLabel passwordLabel = new JLabel("密码");
        JTextField userField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton registerButton = new JButton("注册");
        JButton backButton = new JButton("返回");

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(registerButton);
        panel.add(backButton);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());
                if (!users.containsKey(username)) {
                    users.put(username, password);
                    JOptionPane.showMessageDialog(frame, "注册成功，请登录", "成功", JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(mainPanel, "login");
                } else {
                    JOptionPane.showMessageDialog(frame, "用户名已存在", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "login");
            }
        });

        return panel;
    }

    private static JPanel createManagementPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JButton managePowerBanksButton = new JButton("管理充电宝");
        JButton manageUsersButton = new JButton("管理用户");
        JButton rentPowerBankButton = new JButton("租用充电宝");
        JButton returnPowerBankButton = new JButton("归还充电宝");
        JButton calculateFeeButton = new JButton("结算费用");
        JButton logoutButton = new JButton("登出");

        panel.add(managePowerBanksButton);
        panel.add(manageUsersButton);
        panel.add(rentPowerBankButton);
        panel.add(returnPowerBankButton);
        panel.add(calculateFeeButton);
        panel.add(logoutButton);

        managePowerBanksButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 打开管理充电宝界面
            }
        });

        manageUsersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 打开管理用户界面
            }
        });

        rentPowerBankButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String powerBankId = JOptionPane.showInputDialog(frame, "请输入充电宝ID");
                if (manager.rentPowerBank(currentUser, powerBankId)) {
                    JOptionPane.showMessageDialog(frame, "租用成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "租用失败", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        returnPowerBankButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String powerBankId = JOptionPane.showInputDialog(frame, "请输入充电宝ID");
                if (manager.returnPowerBank(currentUser, powerBankId)) {
                    JOptionPane.showMessageDialog(frame, "归还成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(frame, "归还失败", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        calculateFeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double fee = manager.calculateFee(currentUser);
                JOptionPane.showMessageDialog(frame, "您的总费用是: " + fee, "费用结算", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentUser = null;
                cardLayout.show(mainPanel, "login");
            }
        });

        return panel;
    }
}
