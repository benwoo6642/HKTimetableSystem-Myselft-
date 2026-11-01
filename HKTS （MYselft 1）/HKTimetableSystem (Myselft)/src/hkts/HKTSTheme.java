package hkts;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class HKTSTheme {

    // =====================================
    // Theme Colors
    // =====================================

    public static final Color BACKGROUND =
            new Color(255, 240, 245);

    public static final Color PRIMARY =
            new Color(235, 105, 150);

    public static final Color PRIMARY_DARK =
            new Color(210, 75, 125);

    public static final Color LIGHT_PINK =
            new Color(255, 225, 235);

    public static final Color TEXT =
            new Color(70, 70, 70);

    public static final Color BORDER =
            new Color(175, 175, 175);

    public static final Color WHITE =
            Color.WHITE;

    public static final Color DELETE =
            new Color(225, 100, 110);

    // =====================================
    // Rounded Border
    // =====================================

    public static class RoundedBorder extends AbstractBorder {

        private Color color;
        private int radius;

        public RoundedBorder(Color color, int radius) {
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

            Graphics2D g2 = (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setStroke(new BasicStroke(2f));
            g2.setColor(color);

            g2.draw(new RoundRectangle2D.Float(
                    x + 1,
                    y + 1,
                    width - 3,
                    height - 3,
                    radius,
                    radius
            ));

            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(3, 3, 3, 3);
        }
    }

    // =====================================
    // Input Styling
    // =====================================

    public static void styleInput(JTextField field) {

        field.setFont(new Font("Arial", Font.PLAIN, 14));
        field.setForeground(TEXT);
        field.setBackground(WHITE);

        field.setPreferredSize(new Dimension(200, 40));

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        new RoundedBorder(BORDER, 14),
                        new EmptyBorder(7, 12, 7, 12)
                )
        );

        field.addFocusListener(new FocusAdapter() {

            @Override
            public void focusGained(FocusEvent e) {
                field.setBorder(
                        BorderFactory.createCompoundBorder(
                                new RoundedBorder(PRIMARY, 14),
                                new EmptyBorder(7, 12, 7, 12)
                        )
                );
            }

            @Override
            public void focusLost(FocusEvent e) {
                field.setBorder(
                        BorderFactory.createCompoundBorder(
                                new RoundedBorder(BORDER, 14),
                                new EmptyBorder(7, 12, 7, 12)
                        )
                );
            }
        });
    }

    // =====================================
    // ComboBox Styling
    // =====================================

    public static void styleComboBox(JComboBox<?> comboBox) {

        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        comboBox.setForeground(TEXT);
        comboBox.setBackground(WHITE);

        comboBox.setPreferredSize(new Dimension(200, 40));

        comboBox.setBorder(
                new RoundedBorder(BORDER, 14)
        );

        comboBox.setRenderer(new DefaultListCellRenderer() {

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

                label.setFont(new Font("Arial", Font.PLAIN, 13));

                label.setBorder(new EmptyBorder(6, 10, 6, 10));

                if (isSelected) {
                    label.setBackground(LIGHT_PINK);
                    label.setForeground(PRIMARY_DARK);
                } else {
                    label.setBackground(WHITE);
                    label.setForeground(TEXT);
                }

                return label;
            }
        });
    }

    // =====================================
    // Button Styling
    // =====================================

    public static void styleButton(JButton button) {

        styleButton(button, PRIMARY);
    }

    public static void styleButton(
            JButton button,
            Color buttonColor) {

        button.setFont(new Font("Arial", Font.BOLD, 13));
        button.setForeground(WHITE);
        button.setBackground(buttonColor);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(true);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

     // Calculate width based on button text
        int textWidth = button.getFontMetrics(
            button.getFont()
        ).stringWidth(button.getText());

        button.setPreferredSize(
            new Dimension(Math.max(110, textWidth + 45), 45)
        );

        button.setBorder(new EmptyBorder(8, 15, 8, 15));

        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(buttonColor.darker());
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(buttonColor);
            }
        });
    }

    // =====================================
    // Table Styling
    // =====================================

    public static void styleTable(JTable table) {

        table.setFont(new Font("Arial", Font.PLAIN, 13));

        table.setRowHeight(35);

        table.setForeground(TEXT);
        table.setBackground(WHITE);

        table.setSelectionBackground(LIGHT_PINK);
        table.setSelectionForeground(TEXT);

        table.setGridColor(new Color(235, 225, 230));

        table.setShowVerticalLines(false);

        table.setFillsViewportHeight(true);

        JTableHeader header = table.getTableHeader();

        header.setFont(new Font("Arial", Font.BOLD, 13));

        header.setBackground(LIGHT_PINK);
        header.setForeground(PRIMARY_DARK);

        header.setPreferredSize(new Dimension(0, 40));

        DefaultTableCellRenderer renderer =
                new DefaultTableCellRenderer() {

                    @Override
                    public Component getTableCellRendererComponent(
                            JTable table,
                            Object value,
                            boolean isSelected,
                            boolean hasFocus,
                            int row,
                            int column) {

                        Component component =
                                super.getTableCellRendererComponent(
                                        table,
                                        value,
                                        isSelected,
                                        hasFocus,
                                        row,
                                        column
                                );

                        if (!isSelected) {

                            if (row % 2 == 0) {
                                component.setBackground(WHITE);
                            } else {
                                component.setBackground(
                                        new Color(255, 248, 251)
                                );
                            }

                            component.setForeground(TEXT);
                        }

                        if (component instanceof JLabel) {

                            ((JLabel) component).setBorder(
                                    new EmptyBorder(0, 10, 0, 10)
                            );
                        }

                        return component;
                    }
                };

        table.setDefaultRenderer(Object.class, renderer);
    }

    // =====================================
    // Label Styling
    // =====================================

    public static void styleLabel(JLabel label) {

        label.setFont(new Font("Arial", Font.BOLD, 13));
        label.setForeground(TEXT);
    }

    // =====================================
    // Page Title
    // =====================================

    public static JLabel createPageTitle(String title) {

        JLabel label = new JLabel(title);

        label.setFont(new Font("Arial", Font.BOLD, 25));

        label.setForeground(PRIMARY_DARK);

        return label;
    }

    // =====================================
    // White Content Card
    // =====================================

    public static JPanel createCard() {

        JPanel card = new JPanel() {

            @Override
            protected void paintComponent(Graphics g) {

                super.paintComponent(g);

                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                g2.setColor(WHITE);

                g2.fillRoundRect(
                        0,
                        0,
                        getWidth(),
                        getHeight(),
                        20,
                        20
                );

                g2.dispose();
            }
        };

        card.setOpaque(false);

        card.setLayout(new BorderLayout());

        card.setBorder(new EmptyBorder(20, 20, 20, 20));

        return card;
    }

    // =====================================
    // Apply Theme to Entire Panel
    // =====================================

    public static void applyTheme(Container container) {

        if (container instanceof JPanel) {

            JPanel panel = (JPanel) container;

            panel.setBackground(BACKGROUND);
        }

        for (Component component : container.getComponents()) {

            if (component instanceof JTextField) {

                styleInput((JTextField) component);

            } else if (component instanceof JComboBox) {

                styleComboBox((JComboBox<?>) component);

            } else if (component instanceof JButton) {

                JButton button = (JButton) component;

                String text = button.getText();

                if (text != null &&
                        text.toLowerCase().contains("delete")) {

                    styleButton(button, DELETE);

                } else {

                    styleButton(button);
                }

            } else if (component instanceof JTable) {

                styleTable((JTable) component);

            } else if (component instanceof JLabel) {

                styleLabel((JLabel) component);
            }

            if (component instanceof Container) {

                applyTheme((Container) component);
            }
        }
    }
}