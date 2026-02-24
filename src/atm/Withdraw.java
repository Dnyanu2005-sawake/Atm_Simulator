package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Withdraw extends JFrame implements ActionListener {

    JTextField amountField;
    JButton withdraw, back;

    public Withdraw() {

        setTitle("Withdraw");
        setLayout(null);

        JLabel label = new JLabel("Enter Amount to Withdraw");
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setBounds(130, 50, 300, 30);
        add(label);

        amountField = new JTextField();
        amountField.setBounds(150, 100, 200, 30);
        add(amountField);

        withdraw = new JButton("WITHDRAW");
        withdraw.setBounds(150, 150, 200, 30);
        withdraw.addActionListener(this);
        add(withdraw);

        back = new JButton("BACK");
        back.setBounds(150, 200, 200, 30);
        back.addActionListener(this);
        add(back);

        setSize(500, 350);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == withdraw) {
            try {
                int amount = Integer.parseInt(amountField.getText());

                if (amount <= 0) {
                    JOptionPane.showMessageDialog(null, "Enter valid amount");
                    return;
                }

                DBConnection c = new DBConnection();

                // 1️⃣ Get current balance
                PreparedStatement ps =
                        c.con.prepareStatement(
                                "SELECT balance FROM account WHERE pin='1234'"
                        );
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    int balance = rs.getInt("balance");

                    if (balance < amount) {
                        JOptionPane.showMessageDialog(null, "Insufficient Balance");
                    } else {

                        // 2️⃣ Update balance
                        PreparedStatement ps2 =
                                c.con.prepareStatement(
                                        "UPDATE account SET balance = balance - ? WHERE pin='1234'"
                                );
                        ps2.setInt(1, amount);
                        ps2.executeUpdate();

                        // 3️⃣ Insert transaction
                        PreparedStatement ps3 =
                                c.con.prepareStatement(
                                        "INSERT INTO transactions(pin, type, amount) VALUES (?, ?, ?)"
                                );
                        ps3.setString(1, "1234");
                        ps3.setString(2, "Withdraw");
                        ps3.setInt(3, amount);
                        ps3.executeUpdate();

                        JOptionPane.showMessageDialog(null, "Withdraw Successful");

                        setVisible(false);
                        new Transactions();
                    }
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error occurred");
                e.printStackTrace();
            }
        }

        else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions();
        }
    }
}
