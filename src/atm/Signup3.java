package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Random;

public class Signup3 extends JFrame implements ActionListener {

    long applicationNumber;

    JRadioButton saving, fixed, current, recurring;
    ButtonGroup accountGroup;

    JTextField cardText;
    JPasswordField pinText;

    JCheckBox atm, internet, mobile, email, cheque, statement;

    JButton submit, cancel;

    // ✅ Constructor
    Signup3(long applicationNumber) {

        this.applicationNumber = applicationNumber;

        setTitle("Page 3 : Account Details");
        setLayout(null);

        JLabel heading = new JLabel("Page 3 : Account Details");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(160, 20, 350, 30);
        add(heading);

        JLabel appNo = new JLabel("Application No: " + applicationNumber);
        appNo.setBounds(200, 50, 300, 25);
        add(appNo);

        // Account Type
        JLabel accType = new JLabel("Account Type:");
        accType.setBounds(60, 90, 150, 25);
        add(accType);

        saving = new JRadioButton("Saving Account");
        saving.setBounds(250, 90, 150, 25);

        fixed = new JRadioButton("Fixed Deposit");
        fixed.setBounds(250, 120, 150, 25);

        current = new JRadioButton("Current Account");
        current.setBounds(250, 150, 150, 25);

        recurring = new JRadioButton("Recurring Deposit");
        recurring.setBounds(250, 180, 180, 25);

        saving.setBackground(Color.WHITE);
        fixed.setBackground(Color.WHITE);
        current.setBackground(Color.WHITE);
        recurring.setBackground(Color.WHITE);

        accountGroup = new ButtonGroup();
        accountGroup.add(saving);
        accountGroup.add(fixed);
        accountGroup.add(current);
        accountGroup.add(recurring);

        add(saving);
        add(fixed);
        add(current);
        add(recurring);

        // 🔥 Auto Generate Card & PIN
        Random random = new Random();
        long cardNumber = Math.abs((random.nextLong() % 90000000L) + 5040936000000000L);
        long pinNumber = Math.abs((random.nextLong() % 9000L) + 1000L);

        // Card
        JLabel card = new JLabel("Card Number:");
        card.setBounds(60, 240, 150, 25);
        add(card);

        cardText = new JTextField("" + cardNumber);
        cardText.setBounds(250, 240, 200, 25);
        cardText.setEditable(false);
        add(cardText);

        // PIN
        JLabel pin = new JLabel("PIN:");
        pin.setBounds(60, 290, 150, 25);
        add(pin);

        pinText = new JPasswordField("" + pinNumber);
        pinText.setBounds(250, 290, 200, 25);
        pinText.setEditable(false);
        add(pinText);

        // Services
        JLabel service = new JLabel("Services Required:");
        service.setBounds(60, 340, 150, 25);
        add(service);

        atm = new JCheckBox("ATM Card");
        atm.setBounds(250, 340, 150, 25);

        internet = new JCheckBox("Internet Banking");
        internet.setBounds(250, 370, 180, 25);

        mobile = new JCheckBox("Mobile Banking");
        mobile.setBounds(250, 400, 150, 25);

        email = new JCheckBox("Email Alerts");
        email.setBounds(250, 430, 150, 25);

        cheque = new JCheckBox("Cheque Book");
        cheque.setBounds(430, 340, 150, 25);

        statement = new JCheckBox("E-Statement");
        statement.setBounds(430, 370, 150, 25);

        atm.setBackground(Color.WHITE);
        internet.setBackground(Color.WHITE);
        mobile.setBackground(Color.WHITE);
        email.setBackground(Color.WHITE);
        cheque.setBackground(Color.WHITE);
        statement.setBackground(Color.WHITE);

        add(atm);
        add(internet);
        add(mobile);
        add(email);
        add(cheque);
        add(statement);

        // Buttons
        submit = new JButton("SUBMIT");
        submit.setBounds(200, 480, 120, 35);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("CANCEL");
        cancel.setBounds(340, 480, 120, 35);
        cancel.addActionListener(this);
        add(cancel);

        setSize(650, 600);
        setLocation(350, 80);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == submit) {

            try {

                String accountType = null;

                if (saving.isSelected()) accountType = "Saving";
                else if (fixed.isSelected()) accountType = "Fixed";
                else if (current.isSelected()) accountType = "Current";
                else if (recurring.isSelected()) accountType = "Recurring";

                String services = "";

                if (atm.isSelected()) services += "ATM Card ";
                if (internet.isSelected()) services += "Internet Banking ";
                if (mobile.isSelected()) services += "Mobile Banking ";
                if (email.isSelected()) services += "Email Alerts ";
                if (cheque.isSelected()) services += "Cheque Book ";
                if (statement.isSelected()) services += "E-Statement ";

                DBConnection db = new DBConnection();

                String query = "INSERT INTO signup3 VALUES(?,?,?,?,?)";

                PreparedStatement pst = db.con.prepareStatement(query);

                pst.setLong(1, applicationNumber);
                pst.setString(2, accountType);
                pst.setString(3, cardText.getText());
                pst.setString(4, new String(pinText.getPassword()));
                pst.setString(5, services);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null,
                        "Account Created Successfully!\nCard: " + cardText.getText()
                                + "\nPIN: " + new String(pinText.getPassword()));

                setVisible(false);
                new Login();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == cancel) {
            setVisible(false);
            new Login();
        }
    }

    // ✅ MAIN METHOD ADDED (Now File Runnable)
    public static void main(String[] args) {
        new Signup3(1001);   // Test application number
    }
}