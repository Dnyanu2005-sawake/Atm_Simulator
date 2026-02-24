package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Transactions extends JFrame implements ActionListener {

    JButton deposit, withdraw, balance, miniStatement, pinChange, exit;

    public Transactions() {

        setTitle("ATM Transactions");
        setLayout(null);

        JLabel heading = new JLabel("Select Your Transaction");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(110, 30, 300, 30);
        add(heading);

        deposit = new JButton("DEPOSIT");
        deposit.setBounds(150, 90, 200, 35);
        deposit.addActionListener(this);
        add(deposit);

        withdraw = new JButton("WITHDRAW");
        withdraw.setBounds(150, 140, 200, 35);
        withdraw.addActionListener(this);
        add(withdraw);

        balance = new JButton("BALANCE ENQUIRY");
        balance.setBounds(150, 190, 200, 35);
        balance.addActionListener(this);
        add(balance);

        miniStatement = new JButton("MINI STATEMENT");
        miniStatement.setBounds(150, 240, 200, 35);
        miniStatement.addActionListener(this);
        add(miniStatement);

        pinChange = new JButton("CHANGE PIN");
        pinChange.setBounds(150, 290, 200, 35);
        pinChange.addActionListener(this);
        add(pinChange);

        exit = new JButton("EXIT");
        exit.setBounds(150, 340, 200, 35);
        exit.addActionListener(this);
        add(exit);

        setSize(500, 450);
        setLocation(400, 200);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == deposit) {
            setVisible(false);
            new Deposit();
        }

        else if (ae.getSource() == withdraw) {
            setVisible(false);
            new Withdraw();
        }

        else if (ae.getSource() == balance) {
            setVisible(false);
            new BalanceEnquery();
        }

        else if (ae.getSource() == miniStatement) {
            setVisible(false);
            new MiniStatement();
        }

        else if (ae.getSource() == pinChange) {
            setVisible(false);
            new PinChange();
        }

        else if (ae.getSource() == exit) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new Transactions();
    }
}
