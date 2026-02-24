package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MiniStatement extends JFrame implements ActionListener {

    JTextArea area;
    JButton back;

    public MiniStatement() {

        setTitle("Mini Statement");
        setLayout(null);

        JLabel heading = new JLabel("MINI STATEMENT");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(140, 30, 300, 30);
        add(heading);

        area = new JTextArea();
        area.setBounds(50, 80, 380, 200);
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(area);

        back = new JButton("BACK");
        back.setBounds(180, 300, 120, 30);
        back.addActionListener(this);
        add(back);

        // 🔗 DATABASE se last 5 transactions
        try {
            DBConnection c = new DBConnection();
            Statement s = c.con.createStatement();

            ResultSet rs = s.executeQuery(
                    "SELECT * FROM transactions WHERE pin='1234' ORDER BY date DESC LIMIT 5"
            );

            area.setText("TYPE\tAMOUNT\tDATE\n");
            area.append("---------------------------------------------\n");

            while (rs.next()) {
                area.append(
                        rs.getString("type") + "\t₹" +
                                rs.getInt("amount") + "\t" +
                                rs.getTimestamp("date") + "\n"
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(480, 400);
        setLocation(400, 200);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Transactions();
    }

    public static void main(String[] args) {
        new MiniStatement();
    }
}
