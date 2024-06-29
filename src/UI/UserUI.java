package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserUI {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public UserUI() {
        frame = new JFrame("用户信息");
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createUserPanel(), "user");

        frame.add(mainPanel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // 初始化显示用户信息界面
        cardLayout.show(mainPanel, "user");
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel userLabel = new JLabel("用户名");
        JLabel passwordLabel = new JLabel("密码");
        JTextField userField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton updateButton = new JButton("更新信息");
        JButton logoutButton = new JButton("登出");

        // 模拟获取当前用户信息
        String currentUser = "User1"; // 假设当前用户是User1

        userField.setText(currentUser); // 将当前用户名显示在文本框中
        userField.setEditable(false); // 用户名不可编辑

        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(updateButton);
        panel.add(logoutButton);

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText(); // 获取当前用户名
                String newPassword = new String(passwordField.getPassword()); // 获取新密码

                // 这里可以添加更新用户信息的逻辑，例如更新密码
                // 示例中只是简单地打印出来
                System.out.println("用户 " + username + " 的密码已更新为: " + newPassword);
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 这里可以添加登出逻辑，例如清除当前用户信息等
                System.out.println("用户 " + currentUser + " 已登出");
                frame.dispose(); // 关闭当前窗口
                new LoginUI().createLoginUI(); // 返回登录界面
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        new UserUI();
    }
}
