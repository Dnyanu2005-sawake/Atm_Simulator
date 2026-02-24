package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class BalanceEnquery extends JFrame implements ActionListener {

    JButton back;

    BalanceEnquery() {

        setTitle("Balance Enquiry");
        setLayout(null);

        JLabel heading = new JLabel("BALANCE ENQUIRY");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(130, 40, 300, 30);
        add(heading);

        JLabel balanceLabel = new JLabel("Your Current Balance is:");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        balanceLabel.setBounds(120, 120, 250, 25);
        add(balanceLabel);

        JLabel amount = new JLabel();
        amount.setFont(new Font("Arial", Font.BOLD, 22));
        amount.setBounds(210, 160, 200, 30);
        add(amount);

        // 🔗 DATABASE CONNECTION
        try {
            DBConnection c = new DBConnection();
            Statement s = c.con.createStatement();

            ResultSet rs =
                    s.executeQuery("SELECT balance FROM account WHERE pin='1234'");

            if (rs.next()) {
                int bal = rs.getInt("balance");
                amount.setText("₹ " + bal);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        back = new JButton("BACK");
        back.setBounds(190, 230, 120, 30);
        back.addActionListener(this);
        add(back);

        setSize(500, 350);
        setLocation(400, 200);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == back) {
            setVisible(false);
            new Transactions();
        }
    }
}
