package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Deposit extends JFrame implements ActionListener {

    JTextField amountField;
    JButton deposit, back;

    Deposit() {

        setLayout(null);

        // 🖼 ATM Background Image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("atm/atm.png"));
        Image i2 = i1.getImage().getScaledInstance(900, 700, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0, 0, 900, 700);
        add(image);

        // 💬 Text on Screen
        JLabel text = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System", Font.BOLD, 16));
        text.setBounds(220, 210, 400, 30);
        image.add(text);

        // 💰 Amount Field
        amountField = new JTextField();
        amountField.setBounds(220, 210, 320, 25);
        image.add(amountField);

        // 🔥 Transparent Deposit Button (Image ke button ke upar)
        deposit = new JButton();
        deposit.setBounds(380, 370, 150, 40); // position adjust kar sakti ho
        deposit.setOpaque(false);
        deposit.setContentAreaFilled(false);
        deposit.setBorderPainted(false);
        deposit.addActionListener(this);
        image.add(deposit);

        // 🔥 Transparent Back Button
        back = new JButton();
        back.setBounds(380, 420, 150, 40); // adjust if needed
        back.setOpaque(false);
        back.setContentAreaFilled(false);
        back.setBorderPainted(false);
        back.addActionListener(this);
        image.add(back);

        setSize(900, 700);
        setLocation(300, 0);
        setUndecorated(true);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == deposit) {
            try {

                int amount = Integer.parseInt(amountField.getText());

                DBConnection c = new DBConnection();

                // ⚠ Replace '1234' with real logged-in PIN later
                String query = "UPDATE account SET balance = balance + ? WHERE pin = '1234'";

                PreparedStatement ps = c.con.prepareStatement(query);
                ps.setInt(1, amount);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");

                setVisible(false);
                new Transactions();

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid Amount");
            }
        }

        else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions();
        }
    }

    public static void main(String[] args) {
        new Deposit();
    }
}