package atm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JTextField cardTextField;
    JPasswordField pinTextField;
    JButton login, clear, signup;

    Login() {

        setTitle("ATM Simulator System");
        setLayout(null);
        setSize(500, 350);
        setLocation(400, 200);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Welcome to ATM");
        heading.setFont(new Font("Arial", Font.BOLD, 26));
        heading.setBounds(140, 30, 300, 30);
        add(heading);

        JLabel card = new JLabel("Card Number:");
        card.setFont(new Font("Arial", Font.BOLD, 14));
        card.setBounds(70, 100, 120, 25);
        add(card);

        cardTextField = new JTextField();
        cardTextField.setBounds(200, 100, 200, 25);
        add(cardTextField);

        JLabel pin = new JLabel("PIN:");
        pin.setFont(new Font("Arial", Font.BOLD, 14));
        pin.setBounds(70, 140, 120, 25);
        add(pin);

        pinTextField = new JPasswordField();
        pinTextField.setBounds(200, 140, 200, 25);
        add(pinTextField);

        login = new JButton("SIGN IN");
        login.setBounds(70, 210, 100, 30);
        login.addActionListener(this);
        add(login);

        clear = new JButton("CLEAR");
        clear.setBounds(200, 210, 100, 30);
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("SIGN UP");
        signup.setBounds(330, 210, 100, 30);
        signup.addActionListener(this);
        add(signup);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == clear) {

            cardTextField.setText("");
            pinTextField.setText("");

        }
        else if (ae.getSource() == login) {

            String cardno = cardTextField.getText();
            String pin = new String(pinTextField.getPassword());

            if (cardno.equals("") || pin.equals("")) {
                JOptionPane.showMessageDialog(null, "Please enter Card Number and PIN");
            } else {
                JOptionPane.showMessageDialog(null, "Login Successful (Demo)");

                // 👉 NEXT PAGE: Transactions
                setVisible(false);
                new Transactions();
            }

        }
        else if (ae.getSource() == signup) {

            // 👉 SIGN UP PAGE
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}
