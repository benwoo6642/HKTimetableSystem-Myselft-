package hkts;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    private JComboBox<String> roleBox;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginGUI() {

        setTitle("HK Timetable System - Login");
        setSize(420, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 15));
        mainPanel.setBorder(
            BorderFactory.createEmptyBorder(30, 30, 30, 30)
        );

        // Role Selection
        JLabel roleLabel = new JLabel("Login As:");

        String[] roles = {
            "Programme Leader",
            "Lecturer",
            "Student"
        };

        roleBox = new JComboBox<>(roles);

        // Username
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();

        // Password
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();

        // Login Button
        JButton loginButton = new JButton("Login");

        // Add Components
        mainPanel.add(new JLabel("HKTS LOGIN"));
        mainPanel.add(new JLabel(""));

        mainPanel.add(roleLabel);
        mainPanel.add(roleBox);

        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);

        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);

        mainPanel.add(new JLabel(""));
        mainPanel.add(loginButton);

        add(mainPanel);

        // Login Action
        loginButton.addActionListener(e -> login());

        // Press Enter to Login
        getRootPane().setDefaultButton(loginButton);

        setVisible(true);
    }

    // Login Validation
    private void login() {

        String role = roleBox.getSelectedItem().toString();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Username and Password cannot be empty.",
                "Login Error",
                JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        boolean validLogin = false;

        // Programme Leader Login
        if (role.equals("Programme Leader")) {

            if (username.equals("Ben")
                    && password.equals("12345")) {

                validLogin = true;
            }
        }

        // Lecturer Login
        else if (role.equals("Lecturer")) {

            if (username.equals("lecturer")
                    && password.equals("12345")) {

                validLogin = true;
            }
        }

        // Student Login
        else if (role.equals("Student")) {

            if (username.equals("student")
                    && password.equals("12345")) {

                validLogin = true;
            }
        }

        // Login Result
        if (validLogin) {

            JOptionPane.showMessageDialog(
                this,
                role + " Login Successful!"
            );

            if (role.equals("Programme Leader")) {

                new DashboardGUI();
                dispose();

            } else {

                JOptionPane.showMessageDialog(
                    this,
                    role + " login demonstration completed."
                );

                // Keep Login Page Open
                usernameField.setText("");
                passwordField.setText("");
            }

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Username or Password.",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE
            );

            passwordField.setText("");
        }
    }

    // Main Method
    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            new LoginGUI();
        });
    }
}