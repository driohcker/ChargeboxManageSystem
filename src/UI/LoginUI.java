package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI {
    public CardLayout cardLayout;
    public JPanel mainPanel;

    private static RegisterUI registerUI = new RegisterUI();
    private static MainUI mainUI = new MainUI();

    public JPanel createLoginUI(CardLayout cardLayout, JPanel mainPanel){

        //本页面元素
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel userLabel = new JLabel("用户名");
        JLabel passwordLabel = new JLabel("密码");
        JTextField userField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("登录");
        JButton registerButton = new JButton("注册");


        //本页面可跳转的页面
        mainPanel.add(registerUI.createRegisterUI(), "register");
        mainPanel.add(mainUI.createMainUI(),"main");


        //添加本页面元素
        panel.add(userLabel);
        panel.add(userField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());
                cardLayout.show(mainPanel,"main");
            }
        });

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel,"register");
            }
        });

        return panel;
    }


}
