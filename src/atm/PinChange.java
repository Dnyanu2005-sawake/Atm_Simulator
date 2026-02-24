package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class PinChange extends JFrame implements ActionListener {

    JPasswordField oldPin, newPin, confirmPin;
    JButton change, back;

    public PinChange() {

        setTitle("Change PIN");
        setLayout(null);

        JLabel heading = new JLabel("CHANGE PIN");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(170, 30, 200, 30);
        add(heading);

        JLabel l1 = new JLabel("Old PIN:");
        l1.setBounds(120, 90, 100, 25);
        add(l1);

        oldPin = new JPasswordField();
        oldPin.setBounds(250, 90, 150, 25);
        add(oldPin);

        JLabel l2 = new JLabel("New PIN:");
        l2.setBounds(120, 130, 100, 25);
        add(l2);

        newPin = new JPasswordField();
        newPin.setBounds(250, 130, 150, 25);
        add(newPin);

        JLabel l3 = new JLabel("Confirm PIN:");
        l3.setBounds(120, 170, 100, 25);
        add(l3);

        confirmPin = new JPasswordField();
        confirmPin.setBounds(250, 170, 150, 25);
        add(confirmPin);

        change = new JButton("CHANGE PIN");
        change.setBounds(150, 220, 200, 30);
        change.addActionListener(this);
        add(change);

        back = new JButton("BACK");
        back.setBounds(150, 260, 200, 30);
        back.addActionListener(this);
        add(back);

        setSize(500, 360);
        setLocation(400, 200);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == change) {

            String oldP = oldPin.getText();
            String newP = newPin.getText();
            String confirmP = confirmPin.getText();

            if (oldP.isEmpty() || newP.isEmpty() || confirmP.isEmpty()) {
                JOptionPane.showMessageDialog(null, "All fields are required");
                return;
            }

            if (!newP.equals(confirmP)) {
                JOptionPane.showMessageDialog(null, "New PIN does not match");
                return;
            }

            try {
                DBConnection c = new DBConnection();

                // 1️⃣ Check old PIN
                PreparedStatement ps =
                        c.con.prepareStatement(
                                "SELECT * FROM account WHERE pin=?"
                        );
                ps.setString(1, oldP);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    // 2️⃣ Update PIN
                    PreparedStatement ps2 =
                            c.con.prepareStatement(
                                    "UPDATE account SET pin=? WHERE pin=?"
                            );
                    ps2.setString(1, newP);
                    ps2.setString(2, oldP);
                    ps2.executeUpdate();

                    JOptionPane.showMessageDialog(null, "PIN Changed Successfully");

                    setVisible(false);
                    new Transactions();

                } else {
                    JOptionPane.showMessageDialog(null, "Old PIN is incorrect");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        else if (ae.getSource() == back) {
            setVisible(false);
            new Transactions();
        }
    }
}

