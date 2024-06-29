package ChargingStationManagementSystem;

import PowerBankManager.PowerBankManager;
import PowerBankManager.*;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChargingStationManagementSystem {
    private static JFrame frame;
    private static CardLayout cardLayout;
    private static JPanel mainPanel;

    private static Map<String, String> users = new HashMap<>(); // 用户名-密码映射
    private static String currentUser;
    private static ChargingStationManager manager = new ChargingStationManager(); // 全局的manager
    private static PowerBankManager manager1 = new  PowerBankManager();
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
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



        manager1.addPowerBank(1, 92, Status.AVAILABLE);




        manager.registerUser("user1", "pass1");

        // 显示登录界面
        cardLayout.show(mainPanel, "login");
    }

    private static JPanel createLoginPanel() {
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
                    manager.registerUser(username, password);
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
                showAvailablePowerBanks();
            }
        });

        returnPowerBankButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showUserPowerBanks();
            }
        });

        calculateFeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double fee = manager.calculateFee(currentUser);
                JOptionPane.showMessageDialog(frame, "您的总费用是: " + fee, "费用结算", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        orderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showOrderDetails();
            }
        });

        userInfoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showUserInfo();
            }
        });

        return panel;
    }

    private static void showAvailablePowerBanks() {
        List<PowerBank> availablePowerBanks = manager.getAvailablePowerBanks();

        // 创建表格数据和表头
        Object[][] data = new Object[availablePowerBanks.size()][3]; // 3列：ID、类型、状态

        for (int i = 0; i < availablePowerBanks.size(); i++) {
            PowerBank pb = availablePowerBanks.get(i);
            data[i][0] = pb.getId(); // 第一列：ID
            data[i][1] = pb.getType().getType(); // 第二列：类型
            data[i][2] = pb.isRented() ? "已租借" : "可租借"; // 第三列：状态
        }

        // 表头
        String[] columnNames = {"ID", "类型", "状态"};

        // 显示表格
        showTableDialog(data, columnNames, "可租充电宝");
    }

    private static void showUserPowerBanks() {
        List<PowerBank> userPowerBanks = manager.getUserPowerBanks(currentUser);

        // 创建表格数据和表头
        Object[][] data = new Object[userPowerBanks.size()][2]; // 2列：ID、类型

        for (int i = 0; i < userPowerBanks.size(); i++) {
            PowerBank pb = userPowerBanks.get(i);
            data[i][0] = pb.getId(); // 第一列：ID
            data[i][1] = pb.getType().getType(); // 第二列：类型
        }

        // 表头
        String[] columnNames = {"ID", "类型"};

        // 显示表格
        showTableDialog(data, columnNames, "归还充电宝");
    }

    private static void showOrderDetails() {
        List<PowerBank> userPowerBanks = manager.getUserPowerBanks(currentUser);

        // 创建表格数据和表头
        Object[][] data = new Object[userPowerBanks.size()][4]; // 4列：用户ID、充电宝ID、类型、总价格

        double totalFee = 0;
        for (int i = 0; i < userPowerBanks.size(); i++) {
            PowerBank pb = userPowerBanks.get(i);
            double fee = pb.getType().getRentalPrice();
            totalFee += fee;
            data[i][0] = currentUser; // 第一列：用户ID
            data[i][1] = pb.getId(); // 第二列：充电宝ID
            data[i][2] = pb.getType().getType(); // 第三列：类型
            data[i][3] = fee; // 第四列：总价格
        }

        // 表头
        String[] columnNames = {"用户ID", "充电宝ID", "类型", "总价格"};

        // 显示表格
        showTableDialog(data, columnNames, "订单详情");
    }

    private static void showUserInfo() {
        User user = manager.getUser(currentUser);

        // 用户信息字符串
        StringBuilder userInfo = new StringBuilder();
        userInfo.append("用户名: ").append(user.getUsername()).append("\n");
        userInfo.append("密码: ").append(user.getPassword()).append("\n");

        // 创建文本区域显示用户信息
        JTextArea userInfoArea = new JTextArea(userInfo.toString());
        userInfoArea.setEditable(false);

        // 创建滚动面板
        JScrollPane scrollPane = new JScrollPane(userInfoArea);

        // 创建登出按钮
        JButton logoutButton = new JButton("登出");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentUser = null;
                cardLayout.show(mainPanel, "login");
            }
        });

        // 创建用户信息对话框
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(logoutButton, BorderLayout.SOUTH);
        JOptionPane.showMessageDialog(frame, panel, "用户信息", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showTableDialog(Object[][] data, String[] columnNames, String title) {
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);

        // 添加搜索栏
        JPanel searchPanel = new JPanel(new BorderLayout());
        JTextField searchField = new JTextField();
        searchPanel.add(new JLabel("搜索: "), BorderLayout.WEST);
        searchPanel.add(searchField, BorderLayout.CENTER);
        panel.add(searchPanel, BorderLayout.NORTH);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            public void removeUpdate(DocumentEvent e) {
                search();
            }

            public void changedUpdate(DocumentEvent e) {
                search();
            }

            public void search() {
                String searchText = searchField.getText().toLowerCase();
                TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
                table.setRowSorter(sorter);
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        });

        JButton actionButton = new JButton(title.equals("可租充电宝") ? "租用" : "归还");
        actionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String powerBankId = (String) table.getValueAt(selectedRow, 0);
                    boolean success;
                    if (title.equals("可租充电宝")) {
                        success = manager.rentPowerBank(currentUser, powerBankId);
                    } else {
                        success = manager.returnPowerBank(currentUser, powerBankId);
                    }
                    if (success) {
                        JOptionPane.showMessageDialog(frame, title.equals("可租充电宝") ? "租用成功" : "归还成功", "成功", JOptionPane.INFORMATION_MESSAGE);
                        model.removeRow(selectedRow);
                    } else {
                        JOptionPane.showMessageDialog(frame, title.equals("可租充电宝") ? "租用失败" : "归还失败", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        panel.add(actionButton, BorderLayout.SOUTH);
        JOptionPane.showMessageDialog(frame, panel, title, JOptionPane.INFORMATION_MESSAGE);
    }
}

class PowerBankType {
    private String type;
    private double rentalPrice;

    public PowerBankType(String type, double rentalPrice) {
        this.type = type;
        this.rentalPrice = rentalPrice;
    }

    public String getType() {
        return type;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }
}

class PowerBank {
    private String id;
    private PowerBankType type;
    private boolean isRented;

    public PowerBank(String id, PowerBankType type) {
        this.id = id;
        this.type = type;
        this.isRented = false;
    }

    public String getId() {
        return id;
    }

    public PowerBankType getType() {
        return type;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}

class User {
    private String username;
    private String password;
    private List<PowerBank> rentedPowerBanks;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.rentedPowerBanks = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<PowerBank> getRentedPowerBanks() {
        return rentedPowerBanks;
    }
}

class ChargingStationManager {
    private Map<String, PowerBankType> powerBankTypes;
    private Map<String, PowerBank> powerBanks;
    private Map<String, User> users;

    public ChargingStationManager() {
        powerBankTypes = new HashMap<>();
        powerBanks = new HashMap<>();
        users = new HashMap<>();
    }

    // 添加充电宝类型
    public void addPowerBankType(String type, double rentalPrice) {
        powerBankTypes.put(type, new PowerBankType(type, rentalPrice));
    }

    // 添加充电宝
    public void addPowerBank(String id, String type) {
        if (powerBankTypes.containsKey(type)) {
            powerBanks.put(id, new PowerBank(id, powerBankTypes.get(type)));
        }
    }

    // 注册用户
    public void registerUser(String username, String password) {
        users.put(username, new User(username, password));
    }

    // 获取用户信息
    public User getUser(String username) {
        return users.get(username);
    }

    // 租用充电宝
    public boolean rentPowerBank(String username, String powerBankId) {
        User user = users.get(username);
        PowerBank powerBank = powerBanks.get(powerBankId);

        if (user != null && powerBank != null && !powerBank.isRented()) {
            user.getRentedPowerBanks().add(powerBank);
            powerBank.setRented(true);
            return true;
        }
        return false;
    }

    // 归还充电宝
    public boolean returnPowerBank(String username, String powerBankId) {
        User user = users.get(username);
        PowerBank powerBank = powerBanks.get(powerBankId);

        if (user != null && powerBank != null && powerBank.isRented() && user.getRentedPowerBanks().remove(powerBank)) {
            powerBank.setRented(false);
            return true;
        }
        return false;
    }

    // 计算费用
    public double calculateFee(String username) {
        User user = users.get(username);
        if (user != null) {
            return user.getRentedPowerBanks().stream().mapToDouble(pb -> pb.getType().getRentalPrice()).sum();
        }
        return 0;
    }

    // 获取可租用充电宝列表
    public List<PowerBank> getAvailablePowerBanks() {
        List<PowerBank> availablePowerBanks = new ArrayList<>();
        for (PowerBank pb : powerBanks.values()) {
            if (!pb.isRented()) {
                availablePowerBanks.add(pb);
            }
        }
        return availablePowerBanks;
    }

    // 获取用户租用的充电宝列表
    public List<PowerBank> getUserPowerBanks(String username) {
        User user = users.get(username);
        if (user != null) {
            return new ArrayList<>(user.getRentedPowerBanks());
        }
        return new ArrayList<>();
    }
}
