package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup2 extends JFrame implements ActionListener {

    long applicationNumber;

    JComboBox<String> religion, category, income, education, occupation;
    JTextField panText, aadharText;
    JRadioButton seniorYes, seniorNo, existingYes, existingNo;
    ButtonGroup seniorGroup, existingGroup;
    JButton next;

    Signup2(long applicationNumber) {

        this.applicationNumber = applicationNumber;

        setTitle("Page 2 : Additional Details");
        setLayout(null);

        JLabel heading = new JLabel("Page 2 : Additional Details");
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setBounds(140, 20, 350, 30);
        add(heading);

        JLabel appNo = new JLabel("Application No: " + applicationNumber);
        appNo.setBounds(180, 50, 300, 25);
        add(appNo);

        // Religion
        JLabel rel = new JLabel("Religion:");
        rel.setBounds(60, 90, 150, 25);
        add(rel);

        String relValues[] = {"Hindu", "Muslim", "Sikh", "Christian", "Other"};
        religion = new JComboBox<>(relValues);
        religion.setBounds(250, 90, 200, 25);
        add(religion);

        // Category
        JLabel cat = new JLabel("Category:");
        cat.setBounds(60, 130, 150, 25);
        add(cat);

        String catValues[] = {"General", "OBC", "SC", "ST", "Other"};
        category = new JComboBox<>(catValues);
        category.setBounds(250, 130, 200, 25);
        add(category);

        // Income
        JLabel inc = new JLabel("Income:");
        inc.setBounds(60, 170, 150, 25);
        add(inc);

        String incomeValues[] = {"Null", "<1,50,000", "<2,50,000", "<5,00,000", "Above 5,00,000"};
        income = new JComboBox<>(incomeValues);
        income.setBounds(250, 170, 200, 25);
        add(income);

        // Education
        JLabel edu = new JLabel("Educational Qualification:");
        edu.setBounds(60, 210, 200, 25);
        add(edu);

        String eduValues[] = {"Non-Graduate", "Graduate", "Post-Graduate", "Doctorate", "Other"};
        education = new JComboBox<>(eduValues);
        education.setBounds(250, 210, 200, 25);
        add(education);

        // Occupation
        JLabel occ = new JLabel("Occupation:");
        occ.setBounds(60, 250, 150, 25);
        add(occ);

        String occValues[] = {"Salaried", "Self-Employed", "Business", "Student", "Retired", "Other"};
        occupation = new JComboBox<>(occValues);
        occupation.setBounds(250, 250, 200, 25);
        add(occupation);

        // PAN
        JLabel pan = new JLabel("PAN Number:");
        pan.setBounds(60, 290, 150, 25);
        add(pan);

        panText = new JTextField();
        panText.setBounds(250, 290, 200, 25);
        add(panText);

        // Aadhar
        JLabel aadhar = new JLabel("Aadhar Number:");
        aadhar.setBounds(60, 330, 150, 25);
        add(aadhar);

        aadharText = new JTextField();
        aadharText.setBounds(250, 330, 200, 25);
        add(aadharText);

        // Senior Citizen
        JLabel senior = new JLabel("Senior Citizen:");
        senior.setBounds(60, 370, 150, 25);
        add(senior);

        seniorYes = new JRadioButton("Yes");
        seniorYes.setBounds(250, 370, 60, 25);
        seniorYes.setBackground(Color.WHITE);

        seniorNo = new JRadioButton("No");
        seniorNo.setBounds(320, 370, 60, 25);
        seniorNo.setBackground(Color.WHITE);

        seniorGroup = new ButtonGroup();
        seniorGroup.add(seniorYes);
        seniorGroup.add(seniorNo);

        add(seniorYes);
        add(seniorNo);

        // Existing Account
        JLabel existing = new JLabel("Existing Account:");
        existing.setBounds(60, 410, 150, 25);
        add(existing);

        existingYes = new JRadioButton("Yes");
        existingYes.setBounds(250, 410, 60, 25);
        existingYes.setBackground(Color.WHITE);

        existingNo = new JRadioButton("No");
        existingNo.setBounds(320, 410, 60, 25);
        existingNo.setBackground(Color.WHITE);

        existingGroup = new ButtonGroup();
        existingGroup.add(existingYes);
        existingGroup.add(existingNo);

        add(existingYes);
        add(existingNo);

        next = new JButton("NEXT");
        next.setBounds(220, 460, 140, 35);
        next.setBackground(Color.BLACK);
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        add(next);

        setSize(600, 560);
        setLocation(400, 80);
        getContentPane().setBackground(Color.WHITE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == next) {

            try {

                String rel = (String) religion.getSelectedItem();
                String cat = (String) category.getSelectedItem();
                String inc = (String) income.getSelectedItem();
                String edu = (String) education.getSelectedItem();
                String occ = (String) occupation.getSelectedItem();
                String pan = panText.getText();
                String aadhar = aadharText.getText();

                String senior = seniorYes.isSelected() ? "Yes" : "No";
                String existing = existingYes.isSelected() ? "Yes" : "No";

                DBConnection db = new DBConnection();

                String query = "INSERT INTO signup2 VALUES(?,?,?,?,?,?,?,?,?,?)";

                PreparedStatement pst = db.con.prepareStatement(query);

                pst.setLong(1, applicationNumber);
                pst.setString(2, rel);
                pst.setString(3, cat);
                pst.setString(4, inc);
                pst.setString(5, edu);
                pst.setString(6, occ);
                pst.setString(7, pan);
                pst.setString(8, aadhar);
                pst.setString(9, senior);
                pst.setString(10, existing);

                pst.executeUpdate();

                JOptionPane.showMessageDialog(null, "Page 2 Data Saved Successfully");

                setVisible(false);
                new Signup3(applicationNumber); // next page

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}