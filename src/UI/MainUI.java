package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainUI {
    public JPanel createMainUI(){
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JButton rentPowerBankButton = new JButton("租用充电宝");
        JButton returnPowerBankButton = new JButton("归还充电宝");
        JButton calculateFeeButton = new JButton("结算费用");
        JButton orderButton = new JButton("订单");
        JButton userInfoButton = new JButton("用户信息");

        panel.add(rentPowerBankButton);
        panel.add(returnPowerBankButton);
        panel.add(calculateFeeButton);
        panel.add(orderButton);
        panel.add(userInfoButton);

        rentPowerBankButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        returnPowerBankButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        calculateFeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        userInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        return panel;
    }
}
