package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {

    JTextField nameText, fnameText, dobText, emailText;
    JTextField addressText, cityText, pinText, stateText;
    JButton next;

    JRadioButton male, female;
    ButtonGroup genderGroup;

    JRadioButton married, unmarried, other;
    ButtonGroup maritalGroup;

    Signup() {

        setTitle("New Account Application Form");
        setLayout(null);

        JLabel heading = new JLabel("Application Form");
        heading.setFont(new Font("Arial", Font.BOLD, 26));
        heading.setBounds(160, 10, 300, 30);
        add(heading);

        JLabel name = new JLabel("Name:");
        name.setBounds(60, 80, 120, 25);
        add(name);

        nameText = new JTextField();
        nameText.setBounds(200, 80, 250, 25);
        add(nameText);

        JLabel fname = new JLabel("Father's Name:");
        fname.setBounds(60, 120, 120, 25);
        add(fname);

        fnameText = new JTextField();
        fnameText.setBounds(200, 120, 250, 25);
        add(fnameText);

        JLabel dob = new JLabel("Date of Birth:");
        dob.setBounds(60, 160, 120, 25);
        add(dob);

        dobText = new JTextField();
        dobText.setBounds(200, 160, 250, 25);
        add(dobText);

        JLabel gender = new JLabel("Gender:");
        gender.setBounds(60, 200, 120, 25);
        add(gender);

        male = new JRadioButton("Male");
        male.setBounds(200, 200, 80, 25);
        add(male);

        female = new JRadioButton("Female");
        female.setBounds(300, 200, 80, 25);
        add(female);

        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);

        JLabel email = new JLabel("Email:");
        email.setBounds(60, 240, 120, 25);
        add(email);

        emailText = new JTextField();
        emailText.setBounds(200, 240, 250, 25);
        add(emailText);

        JLabel marital = new JLabel("Marital Status:");
        marital.setBounds(60, 280, 120, 25);
        add(marital);

        married = new JRadioButton("Married");
        married.setBounds(200, 280, 90, 25);
        add(married);

        unmarried = new JRadioButton("Unmarried");
        unmarried.setBounds(300, 280, 100, 25);
        add(unmarried);

        other = new JRadioButton("Other");
        other.setBounds(410, 280, 80, 25);
        add(other);

        maritalGroup = new ButtonGroup();
        maritalGroup.add(married);
        maritalGroup.add(unmarried);
        maritalGroup.add(other);

        JLabel address = new JLabel("Address:");
        address.setBounds(60, 320, 120, 25);
        add(address);

        addressText = new JTextField();
        addressText.setBounds(200, 320, 250, 25);
        add(addressText);

        JLabel city = new JLabel("City:");
        city.setBounds(60, 360, 120, 25);
        add(city);

        cityText = new JTextField();
        cityText.setBounds(200, 360, 250, 25);
        add(cityText);

        JLabel pin = new JLabel("PIN Code:");
        pin.setBounds(60, 400, 120, 25);
        add(pin);

        pinText = new JTextField();
        pinText.setBounds(200, 400, 250, 25);
        add(pinText);

        JLabel state = new JLabel("State:");
        state.setBounds(60, 440, 120, 25);
        add(state);

        stateText = new JTextField();
        stateText.setBounds(200, 440, 250, 25);
        add(stateText);

        next = new JButton("NEXT");
        next.setBounds(220, 480, 120, 30);
        next.addActionListener(this);
        add(next);

        setSize(600, 600);
        setLocation(400, 50);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        try {

            String name = nameText.getText();
            String fatherName = fnameText.getText();
            String dob = dobText.getText();
            String email = emailText.getText();
            String address = addressText.getText();
            String city = cityText.getText();
            String pin = pinText.getText();
            String state = stateText.getText();

            String gender = male.isSelected() ? "Male" : "Female";

            String marital = null;
            if (married.isSelected()) marital = "Married";
            else if (unmarried.isSelected()) marital = "Unmarried";
            else marital = "Other";

            DBConnection db = new DBConnection();

            String query = "INSERT INTO signup(name, father_name, dob, gender, email, marital_status, address, city, pin_code, state) VALUES(?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement pst = db.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pst.setString(1, name);
            pst.setString(2, fatherName);
            pst.setString(3, dob);
            pst.setString(4, gender);
            pst.setString(5, email);
            pst.setString(6, marital);
            pst.setString(7, address);
            pst.setString(8, city);
            pst.setString(9, pin);
            pst.setString(10, state);

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                long applicationNumber = rs.getLong(1);

                JOptionPane.showMessageDialog(null,
                        "Signup Successful!\nApplication Number: " + applicationNumber);

                setVisible(false);
                new Signup2(applicationNumber);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}