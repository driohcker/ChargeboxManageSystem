package UI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RentalUI {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public JPanel createRentalUI(CardLayout cardLayout, JPanel mainPanel) {
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;

        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel titleLabel = new JLabel("出租信息");
        JLabel infoLabel = new JLabel("这里是出租信息页面");
        JButton backButton = new JButton("返回");

        panel.add(titleLabel);
        panel.add(new JLabel()); // 空白占位
        panel.add(infoLabel);
        panel.add(new JLabel()); // 空白占位
        panel.add(new JLabel()); // 空白占位
        panel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "login");
            }
        });

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("出租信息页面");
                CardLayout cardLayout = new CardLayout();
                JPanel mainPanel = new JPanel(cardLayout);

                RentalUI rentalUI = new RentalUI();
                mainPanel.add(rentalUI.createRentalUI(cardLayout, mainPanel), "rental");

                frame.add(mainPanel);
                frame.setSize(400, 300);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
