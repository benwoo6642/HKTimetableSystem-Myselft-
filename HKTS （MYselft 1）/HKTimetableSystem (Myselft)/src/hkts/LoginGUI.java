package hkts;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import javax.swing.plaf.basic.BasicComboPopup;

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.RoundRectangle2D;

public class LoginGUI extends JFrame {

    // ====================================
    // Attributes
    // ====================================

    private HKTSData data;

    private JComboBox<String> roleBox;
    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton showPasswordButton;

    private boolean passwordVisible = false;

    // ====================================
    // Theme Colors
    // ====================================

    private final Color BACKGROUND =
        new Color(255, 240, 245);

    private final Color PRIMARY =
        new Color(235, 105, 150);

    private final Color PRIMARY_DARK =
        new Color(210, 75, 125);

    private final Color TEXT =
        new Color(70, 70, 70);

    private final Color BORDER =
        new Color(175, 175, 175);

    private final Color LIGHT_PINK =
        new Color(255, 225, 235);

    // ====================================
    // Input Settings
    // ====================================

    private final int INPUT_WIDTH = 280;
    private final int INPUT_HEIGHT = 45;

    private final String USERNAME_PLACEHOLDER =
        "Enter your username";

    // ====================================
    // Constructor
    // ====================================

    public LoginGUI(HKTSData data) {

        this.data = data;

        // Window Settings
        setTitle("HK Timetable System - Login");

        setSize(850, 650);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        setResizable(false);

        // ====================================
        // Background Panel
        // ====================================

        JPanel backgroundPanel = new JPanel(
            new GridBagLayout()
        );

        backgroundPanel.setBackground(BACKGROUND);

        // ====================================
        // Login Card
        // ====================================

        JPanel loginCard = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 =
                    (Graphics2D) g.create();

                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(Color.WHITE);

                g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    30,
                    30
                );

                g2.dispose();
            }
        };

        loginCard.setOpaque(false);

        loginCard.setLayout(
            new BoxLayout(loginCard, BoxLayout.Y_AXIS)
        );

        loginCard.setPreferredSize(
            new Dimension(420, 550)
        );

        loginCard.setBorder(
            new EmptyBorder(30, 40, 30, 40)
        );

        // ====================================
        // Title
        // ====================================

        JLabel titleLabel = new JLabel(
            "HK TIMETABLE SYSTEM"
        );

        titleLabel.setFont(
            new Font("Arial", Font.BOLD, 24)
        );

        titleLabel.setForeground(PRIMARY_DARK);

        titleLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        JLabel subtitleLabel = new JLabel(
            "Academic Scheduling Portal"
        );

        subtitleLabel.setFont(
            new Font("Arial", Font.PLAIN, 13)
        );

        subtitleLabel.setForeground(Color.GRAY);

        subtitleLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        JLabel welcomeLabel = new JLabel(
            "Welcome Back!"
        );

        welcomeLabel.setFont(
            new Font("Arial", Font.BOLD, 20)
        );

        welcomeLabel.setForeground(TEXT);

        welcomeLabel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        // ====================================
        // Role Selection
        // ====================================

        JLabel roleLabel =
            createLabel("LOGIN AS");

        String[] roles = {
            "Programme Leader",
            "Lecturer",
            "Student"
        };

        roleBox = new JComboBox<>(roles);

        roleBox.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );

        roleBox.setForeground(TEXT);

        roleBox.setBackground(Color.WHITE);

        roleBox.setFocusable(true);

        roleBox.setBorder(
            BorderFactory.createEmptyBorder()
        );

        // Custom ComboBox UI
        roleBox.setUI(new BasicComboBoxUI() {

            @Override
            protected JButton createArrowButton() {

                JButton arrowButton = new JButton() {

                    @Override
                    protected void paintComponent(Graphics g) {

                        Graphics2D g2 =
                            (Graphics2D) g.create();

                        g2.setRenderingHint(
                            RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON
                        );

                        g2.setColor(PRIMARY_DARK);

                        int centerX = getWidth() / 2;
                        int centerY = getHeight() / 2;

                        Polygon arrow = new Polygon();

                        arrow.addPoint(
                            centerX - 5,
                            centerY - 2
                        );

                        arrow.addPoint(
                            centerX + 5,
                            centerY - 2
                        );

                        arrow.addPoint(
                            centerX,
                            centerY + 4
                        );

                        g2.fillPolygon(arrow);

                        g2.dispose();
                    }
                };

                arrowButton.setPreferredSize(
                    new Dimension(35, 40)
                );

                arrowButton.setContentAreaFilled(false);

                arrowButton.setBorderPainted(false);

                arrowButton.setFocusPainted(false);

                arrowButton.setOpaque(false);

                arrowButton.setCursor(
                    new Cursor(Cursor.HAND_CURSOR)
                );

                return arrowButton;
            }

            @Override
            protected BasicComboPopup createPopup() {

                BasicComboPopup popup =
                    new BasicComboPopup(comboBox);

                popup.setBorder(
                    BorderFactory.createLineBorder(
                        BORDER,
                        1
                    )
                );

                return popup;
            }
        });

        // ====================================
        // Role Dropdown Renderer
        // ====================================

        roleBox.setRenderer(
            new DefaultListCellRenderer() {

                @Override
                public Component getListCellRendererComponent(
                        JList<?> list,
                        Object value,
                        int index,
                        boolean isSelected,
                        boolean cellHasFocus) {

                    JLabel label =
                        (JLabel) super.getListCellRendererComponent(
                            list,
                            value,
                            index,
                            isSelected,
                            cellHasFocus
                        );

                    label.setFont(
                        new Font("Arial", Font.PLAIN, 14)
                    );

                    label.setBorder(
                        new EmptyBorder(10, 15, 10, 10)
                    );

                    if (isSelected) {

                        label.setBackground(LIGHT_PINK);

                        label.setForeground(PRIMARY_DARK);

                    } else {

                        label.setBackground(Color.WHITE);

                        label.setForeground(TEXT);
                    }

                    return label;
                }
            }
        );

        roleBox.setMaximumRowCount(3);

        // Role Panel
        JPanel rolePanel = createInputPanel();

        rolePanel.setLayout(
            new BorderLayout()
        );

        rolePanel.add(
            roleBox,
            BorderLayout.CENTER
        );

        // Focus Effect
        roleBox.addFocusListener(
            new FocusAdapter() {

                @Override
                public void focusGained(FocusEvent e) {

                    rolePanel.setBorder(
                        createInputBorder(PRIMARY)
                    );
                }

                @Override
                public void focusLost(FocusEvent e) {

                    rolePanel.setBorder(
                        createInputBorder(BORDER)
                    );
                }
            }
        );

        // ====================================
        // Username
        // ====================================

        JLabel usernameLabel =
            createLabel("USERNAME");

        usernameField = new JTextField();

        usernameField.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );

        usernameField.setBorder(
            new EmptyBorder(5, 15, 5, 15)
        );

        usernameField.setOpaque(false);

        JPanel usernamePanel = createInputPanel();

        usernamePanel.setLayout(
            new BorderLayout()
        );

        usernamePanel.add(
            usernameField,
            BorderLayout.CENTER
        );

        setupUsernamePlaceholder();

        // Username Focus Effect
        usernameField.addFocusListener(
            new FocusAdapter() {

                @Override
                public void focusGained(FocusEvent e) {

                    usernamePanel.setBorder(
                        createInputBorder(PRIMARY)
                    );
                }

                @Override
                public void focusLost(FocusEvent e) {

                    usernamePanel.setBorder(
                        createInputBorder(BORDER)
                    );
                }
            }
        );

        // ====================================
        // Password
        // ====================================

        JLabel passwordLabel =
            createLabel("PASSWORD");

        passwordField = new JPasswordField();

        passwordField.setFont(
            new Font("Arial", Font.PLAIN, 14)
        );

        passwordField.setBorder(
            new EmptyBorder(5, 15, 5, 5)
        );

        passwordField.setOpaque(false);

        char defaultEchoChar =
            passwordField.getEchoChar();

        // ====================================
        // Show / Hide Button
        // ====================================

        showPasswordButton =
            new JButton("Show");

        showPasswordButton.setFont(
            new Font("Arial", Font.PLAIN, 12)
        );

        showPasswordButton.setForeground(
            PRIMARY_DARK
        );

        showPasswordButton.setBorder(
            new EmptyBorder(0, 5, 0, 12)
        );

        showPasswordButton.setBorderPainted(false);

        showPasswordButton.setContentAreaFilled(false);

        showPasswordButton.setFocusPainted(false);

        showPasswordButton.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        JPanel passwordPanel = createInputPanel();

        passwordPanel.setLayout(
            new BorderLayout()
        );

        passwordPanel.add(
            passwordField,
            BorderLayout.CENTER
        );

        passwordPanel.add(
            showPasswordButton,
            BorderLayout.EAST
        );

        // Password Focus Effect
        passwordField.addFocusListener(
            new FocusAdapter() {

                @Override
                public void focusGained(FocusEvent e) {

                    passwordPanel.setBorder(
                        createInputBorder(PRIMARY)
                    );
                }

                @Override
                public void focusLost(FocusEvent e) {

                    passwordPanel.setBorder(
                        createInputBorder(BORDER)
                    );
                }
            }
        );

        // Show / Hide Action
        showPasswordButton.addActionListener(e -> {

            passwordVisible = !passwordVisible;

            if (passwordVisible) {

                passwordField.setEchoChar((char) 0);

                showPasswordButton.setText("Hide");

            } else {

                passwordField.setEchoChar(
                    defaultEchoChar
                );

                showPasswordButton.setText("Show");
            }
        });

        // ====================================
        // Login Button
        // ====================================

        JButton loginButton = new JButton("LOGIN") {

            @Override
            protected void paintComponent(Graphics g) {

                Graphics2D g2 =
                    (Graphics2D) g.create();

                g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
                );

                if (getModel().isRollover()) {

                    g2.setColor(PRIMARY_DARK);

                } else {

                    g2.setColor(PRIMARY);
                }

                g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    18,
                    18
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        loginButton.setFont(
            new Font("Arial", Font.BOLD, 15)
        );

        loginButton.setForeground(Color.WHITE);

        loginButton.setContentAreaFilled(false);

        loginButton.setBorderPainted(false);

        loginButton.setFocusPainted(false);

        loginButton.setOpaque(false);

        loginButton.setCursor(
            new Cursor(Cursor.HAND_CURSOR)
        );

        loginButton.setPreferredSize(
            new Dimension(INPUT_WIDTH, 45)
        );

        loginButton.setMaximumSize(
            new Dimension(INPUT_WIDTH, 45)
        );

        loginButton.setMinimumSize(
            new Dimension(INPUT_WIDTH, 45)
        );

        loginButton.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        // ====================================
        // Add Components
        // ====================================

        loginCard.add(titleLabel);

        loginCard.add(
            Box.createVerticalStrut(5)
        );

        loginCard.add(subtitleLabel);

        loginCard.add(
            Box.createVerticalStrut(20)
        );

        loginCard.add(welcomeLabel);

        loginCard.add(
            Box.createVerticalStrut(25)
        );

        // Role
        loginCard.add(roleLabel);

        loginCard.add(
            Box.createVerticalStrut(8)
        );

        loginCard.add(rolePanel);

        loginCard.add(
            Box.createVerticalStrut(15)
        );

        // Username
        loginCard.add(usernameLabel);

        loginCard.add(
            Box.createVerticalStrut(8)
        );

        loginCard.add(usernamePanel);

        loginCard.add(
            Box.createVerticalStrut(15)
        );

        // Password
        loginCard.add(passwordLabel);

        loginCard.add(
            Box.createVerticalStrut(8)
        );

        loginCard.add(passwordPanel);

        loginCard.add(
            Box.createVerticalStrut(25)
        );

        // Login Button
        loginCard.add(loginButton);

        backgroundPanel.add(loginCard);

        add(backgroundPanel);

        // ====================================
        // Login Action
        // ====================================

        loginButton.addActionListener(
            e -> login()
        );

        getRootPane().setDefaultButton(
            loginButton
        );

        setVisible(true);
    }

    // ====================================
    // Create Label
    // ====================================

    private JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(
            new Font("Arial", Font.BOLD, 12)
        );

        label.setForeground(TEXT);

        label.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        return label;
    }

    // ====================================
    // Create Input Panel
    // ====================================

    private JPanel createInputPanel() {

        JPanel panel = new JPanel();

        panel.setBackground(Color.WHITE);

        panel.setOpaque(true);

        panel.setPreferredSize(
            new Dimension(INPUT_WIDTH, INPUT_HEIGHT)
        );

        panel.setMaximumSize(
            new Dimension(INPUT_WIDTH, INPUT_HEIGHT)
        );

        panel.setMinimumSize(
            new Dimension(INPUT_WIDTH, INPUT_HEIGHT)
        );

        panel.setAlignmentX(
            Component.CENTER_ALIGNMENT
        );

        panel.setBorder(
            createInputBorder(BORDER)
        );

        return panel;
    }

    // ====================================
    // Create Input Border
    // ====================================

    private javax.swing.border.Border createInputBorder(
            Color color) {

        return new RoundedBorder(
            color,
            16
        );
    }

    // ====================================
    // Rounded Border
    // ====================================

    private static class RoundedBorder
            extends AbstractBorder {

        private final Color color;
        private final int radius;

        public RoundedBorder(
                Color color,
                int radius) {

            this.color = color;
            this.radius = radius;
        }

        @Override
        public void paintBorder(
                Component c,
                Graphics g,
                int x,
                int y,
                int width,
                int height) {

            Graphics2D g2 =
                (Graphics2D) g.create();

            g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setStroke(
                new BasicStroke(2f)
            );

            g2.setColor(color);

            g2.draw(
                new RoundRectangle2D.Float(
                    x + 1,
                    y + 1,
                    width - 3,
                    height - 3,
                    radius,
                    radius
                )
            );

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(
                Component c) {

            return new Insets(3, 3, 3, 3);
        }
    }

    // ====================================
    // Username Placeholder
    // ====================================

    private void setupUsernamePlaceholder() {

        usernameField.setText(
            USERNAME_PLACEHOLDER
        );

        usernameField.setForeground(Color.GRAY);

        usernameField.addFocusListener(
            new FocusAdapter() {

                @Override
                public void focusGained(FocusEvent e) {

                    if (usernameField.getText().equals(
                            USERNAME_PLACEHOLDER)) {

                        usernameField.setText("");

                        usernameField.setForeground(TEXT);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {

                    if (usernameField.getText().isEmpty()) {

                        usernameField.setText(
                            USERNAME_PLACEHOLDER
                        );

                        usernameField.setForeground(
                            Color.GRAY
                        );
                    }
                }
            }
        );
    }

    // ====================================
    // Login Validation
    // ====================================

    private void login() {

        String role =
            roleBox.getSelectedItem().toString();

        String username =
            usernameField.getText().trim();

        String password = new String(
            passwordField.getPassword()
        );

        if (username.isEmpty()
                || username.equals(USERNAME_PLACEHOLDER)
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                this,
                "Username and Password cannot be empty.",
                "Login Error",
                JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        boolean validLogin = false;

        // Programme Leader
        if (role.equals("Programme Leader")) {

            if (username.equals("Ben")
                    && password.equals("12345")) {

                validLogin = true;
            }
        }

        // Lecturer
        else if (role.equals("Lecturer")) {

            if (username.equals("lecturer")
                    && password.equals("12345")) {

                validLogin = true;
            }
        }

        // Student
        else if (role.equals("Student")) {

            if (username.equals("student")
                    && password.equals("12345")) {

                validLogin = true;
            }
        }

        // ====================================
        // Login Result
        // ====================================

        if (validLogin) {

            JOptionPane.showMessageDialog(
                this,
                role + " Login Successful!"
            );

            if (role.equals("Programme Leader")) {

                new DashboardGUI(data);

            } else {

                new ViewTimetableGUI(data, role);
            }

            dispose();

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

    // ====================================
    // Main Method
    // ====================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            HKTSData data = new HKTSData();

            new LoginGUI(data);
        });
    }
}