import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChargingStationUI extends JFrame {
    private JTextField txtUserId, txtChargerId, txtTypeId, txtUserName, txtChargerTypeName;
    private JButton btnAddType, btnDeleteType, btnQueryType;
    private JButton btnAddCharger, btnDeleteCharger, btnQueryCharger;
    private JButton btnAddUser, btnDeleteUser, btnQueryUser;
    private JButton btnRent, btnReturn, btnSettle;

    public ChargingStationUI() {
        setTitle("Charging Station Management");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        // Create panels for each section
        JPanel typePanel = createTypePanel();
        JPanel chargerPanel = createChargerPanel();
        JPanel userPanel = createUserPanel();
        JPanel rentReturnPanel = createRentReturnPanel();
        JPanel settlePanel = createSettlePanel();

        // Add panels to the frame
        add(typePanel);
        add(chargerPanel);
        add(userPanel);
        add(rentReturnPanel);
        add(settlePanel);
    }

    private JPanel createTypePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Charger Type Management"));
        panel.setLayout(new GridLayout(4, 2));

        JLabel lblTypeId = new JLabel("Type ID:");
        txtTypeId = new JTextField(10);
        JLabel lblChargerTypeName = new JLabel("Type Name:");
        txtChargerTypeName = new JTextField(10);

        btnAddType = new JButton("Add Type");
        btnDeleteType = new JButton("Delete Type");
        btnQueryType = new JButton("Query Type");

        btnAddType.addActionListener(e -> manageChargerType("add"));
        btnDeleteType.addActionListener(e -> manageChargerType("delete"));
        btnQueryType.addActionListener(e -> manageChargerType("query"));

        panel.add(lblTypeId);
        panel.add(txtTypeId);
        panel.add(lblChargerTypeName);
        panel.add(txtChargerTypeName);
        panel.add(btnAddType);
        panel.add(btnDeleteType);
        panel.add(btnQueryType);

        return panel;
    }

    private JPanel createChargerPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Charger Management"));
        panel.setLayout(new GridLayout(4, 2));

        JLabel lblChargerId = new JLabel("Charger ID:");
        txtChargerId = new JTextField(10);

        btnAddCharger = new JButton("Add Charger");
        btnDeleteCharger = new JButton("Delete Charger");
        btnQueryCharger = new JButton("Query Charger");

        btnAddCharger.addActionListener(e -> manageCharger("add"));
        btnDeleteCharger.addActionListener(e -> manageCharger("delete"));
        btnQueryCharger.addActionListener(e -> manageCharger("query"));

        panel.add(lblChargerId);
        panel.add(txtChargerId);
        panel.add(btnAddCharger);
        panel.add(btnDeleteCharger);
        panel.add(btnQueryCharger);

        return panel;
    }

    private JPanel createUserPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("User Management"));
        panel.setLayout(new GridLayout(4, 2));

        JLabel lblUserId = new JLabel("User ID:");
        txtUserId = new JTextField(10);
        JLabel lblUserName = new JLabel("User Name:");
        txtUserName = new JTextField(10);

        btnAddUser = new JButton("Add User");
        btnDeleteUser = new JButton("Delete User");
        btnQueryUser = new JButton("Query User");

        btnAddUser.addActionListener(e -> manageUser("add"));
        btnDeleteUser.addActionListener(e -> manageUser("delete"));
        btnQueryUser.addActionListener(e -> manageUser("query"));

        panel.add(lblUserId);
        panel.add(txtUserId);
        panel.add(lblUserName);
        panel.add(txtUserName);
        panel.add(btnAddUser);
        panel.add(btnDeleteUser);
        panel.add(btnQueryUser);

        return panel;
    }

    private JPanel createRentReturnPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Rent/Return Management"));
        panel.setLayout(new GridLayout(4, 1));

        btnRent = new JButton("Rent Charger");
        btnReturn = new JButton("Return Charger");

        btnRent.addActionListener(e -> rentCharger());
        btnReturn.addActionListener(e -> returnCharger());

        panel.add(btnRent);
        panel.add(btnReturn);

        return panel;
    }

    private JPanel createSettlePanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder("Payment Settlement"));
        panel.setLayout(new GridLayout(4, 1));

        btnSettle = new JButton("Settle Payment");

        btnSettle.addActionListener(e -> settlePayment());

        panel.add(btnSettle);

        return panel;
    }

    private void manageChargerType(String action) {
        // Implement the logic to interact with the backend for charger type management
        String typeId = txtTypeId.getText();
        String typeName = txtChargerTypeName.getText();
        System.out.println(action + " charger type: ID=" + typeId + ", Name=" + typeName);
        // TODO: Add backend interaction here
    }

    private void manageCharger(String action) {
        // Implement the logic to interact with the backend for charger management
        String chargerId = txtChargerId.getText();
        System.out.println(action + " charger: ID=" + chargerId);
        // TODO: Add backend interaction here
    }

    private void manageUser(String action) {
        // Implement the logic to interact with the backend for user management
        String userId = txtUserId.getText();
        String userName = txtUserName.getText();
        System.out.println(action + " user: ID=" + userId + ", Name=" + userName);
        // TODO: Add backend interaction here
    }

    private void rentCharger() {
        // Implement the logic to interact with the backend for renting a charger
        String userId = txtUserId.getText();
        String chargerId = txtChargerId.getText();
        System.out.println("Rent charger request sent for User ID: " + userId + ", Charger ID: " + chargerId);
        // TODO: Add backend interaction here
    }

    private void returnCharger() {
        // Implement the logic to interact with the backend for returning a charger
        String userId = txtUserId.getText();
        String chargerId = txtChargerId.getText();
        System.out.println("Return charger request sent for User ID: " + userId + ", Charger ID: " + chargerId);
        // TODO: Add backend interaction here
    }

    private void settlePayment() {
        // Implement the logic to interact with the backend for settling payment
        String userId = txtUserId.getText();
        System.out.println("Settle payment request sent for User ID: " + userId);
        // TODO: Add backend interaction here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChargingStationUI().setVisible(true));
    }
}
