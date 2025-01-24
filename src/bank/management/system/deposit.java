package bank.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class deposit extends JFrame implements ActionListener {
    String pin;
    TextField textField;
    JButton b1, b2;

    deposit(String pin) {
        this.pin = pin;

        // Set the background image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/atm2.png"));
        Image i2 = i1.getImage().getScaledInstance(1550, 830, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l3 = new JLabel(i3);
        l3.setBounds(0, 0, 1550, 830);
        add(l3);

        // Add label for deposit instruction
        JLabel label1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(460, 180, 400, 35);
        l3.add(label1);

        // TextField to enter the deposit amount
        textField = new TextField();
        textField.setBackground(new Color(65, 125, 128));
        textField.setForeground(Color.WHITE);
        textField.setBounds(460, 230, 320, 25);
        textField.setFont(new Font("Raleway", Font.BOLD, 22));
        l3.add(textField);

        // Deposit and Back buttons
        b1 = new JButton("DEPOSIT");
        b1.setBounds(700, 362, 150, 35);
        b1.setBackground(new Color(65, 125, 128));
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        l3.add(b1);

        b2 = new JButton("BACK");
        b2.setBounds(700, 406, 150, 35);
        b2.setBackground(new Color(65, 125, 128));
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        l3.add(b2);

        // JFrame properties
        setLayout(null);
        setSize(1550, 1080);
        setLocation(0, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if the Deposit button was clicked
            if (e.getSource() == b1) {
                String amountStr = textField.getText();

                // Validate if amount is entered and is a valid number
                if (amountStr.equals("") || !isNumeric(amountStr)) {
                    JOptionPane.showMessageDialog(null, "Please Enter a Valid Amount to Deposit");
                    return;
                }

                // Parse the deposit amount to integer
                int amount = Integer.parseInt(amountStr);
                Date date = new Date();

                // Database connection
                Con c = new Con();
                c.statement.executeUpdate("INSERT INTO bank VALUES('" + pin + "', '" + date + "', 'Deposit', '" + amount + "')");

                // Success message
                JOptionPane.showMessageDialog(null, "Rs. " + amount + " Deposited Successfully");

                // Close current window and open the main class window
                setVisible(false);
                new main_Class(pin);
            }

            // Check if the Back button was clicked
            else if (e.getSource() == b2) {
                setVisible(false);
                new main_Class(pin);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Helper method to check if the entered amount is a valid number
    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Main method to test the deposit class
    public static void main(String[] args) {
        new deposit("");
    }
}
