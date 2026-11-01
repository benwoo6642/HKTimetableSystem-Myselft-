package hkts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class CoursePanel extends JPanel {

    // ====================================
    // Shared Data
    // ====================================

    private HKTSData data;

    // ====================================
    // Input Fields
    // ====================================

    private JTextField idField;
    private JTextField nameField;
    private JTextField creditField;
    private JComboBox<String> typeComboBox;
    private JTextField searchField;

    // ====================================
    // Buttons
    // ====================================

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton searchButton;

    // ====================================
    // Table
    // ====================================

    private JTable courseTable;
    private DefaultTableModel tableModel;

    // ====================================
    // Constructor
    // ====================================

    public CoursePanel(HKTSData data) {

        this.data = data;

        setLayout(new BorderLayout(10, 10));

        setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // ====================================
        // Page Title
        // ====================================

        JLabel title = new JLabel("Course Management");

        title.setFont(
            new Font("Arial", Font.BOLD, 24)
        );

        // ====================================
        // Input Form
        // ====================================

        JPanel formPanel = new JPanel(
            new GridLayout(4, 2, 10, 10)
        );

        idField = new JTextField();
        nameField = new JTextField();
        creditField = new JTextField();

        String[] courseTypes = {
            "Lecture",
            "Practical"
        };

        typeComboBox = new JComboBox<>(courseTypes);

        formPanel.add(new JLabel("Course ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Course Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Course Credit:"));
        formPanel.add(creditField);

        formPanel.add(new JLabel("Course Type:"));
        formPanel.add(typeComboBox);

        // ====================================
        // CRUD Buttons
        // ====================================

        JPanel buttonPanel = new JPanel(
            new FlowLayout(FlowLayout.LEFT)
        );

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        clearButton = new JButton("Clear");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        // ====================================
        // Search Panel
        // ====================================

        JPanel searchPanel = new JPanel(
            new FlowLayout(FlowLayout.LEFT)
        );

        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        searchPanel.add(new JLabel("Search Course ID:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // ====================================
        // Top Section
        // ====================================

        JPanel topPanel = new JPanel();

        topPanel.setLayout(
            new BoxLayout(topPanel, BoxLayout.Y_AXIS)
        );

        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        topPanel.add(title);
        topPanel.add(Box.createVerticalStrut(20));

        topPanel.add(formPanel);
        topPanel.add(Box.createVerticalStrut(15));

        topPanel.add(buttonPanel);
        topPanel.add(Box.createVerticalStrut(10));

        topPanel.add(searchPanel);

        // ====================================
        // Course Table
        // ====================================

        String[] columnNames = {
            "Course ID",
            "Course Name",
            "Course Credit",
            "Course Type"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        courseTable = new JTable(tableModel);

        courseTable.setRowHeight(25);

        courseTable.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(courseTable);

        // ====================================
        // Add Components
        // ====================================

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // ====================================
        // Display Course Records
        // ====================================

        refreshTable();

        // ====================================
        // Select Course from Table
        // ====================================

        courseTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int selectedRow = courseTable.getSelectedRow();

                if (selectedRow != -1) {

                    int modelRow =
                        courseTable.convertRowIndexToModel(selectedRow);

                    idField.setText(
                        tableModel.getValueAt(modelRow, 0).toString()
                    );

                    nameField.setText(
                        tableModel.getValueAt(modelRow, 1).toString()
                    );

                    creditField.setText(
                        tableModel.getValueAt(modelRow, 2).toString()
                    );

                    typeComboBox.setSelectedItem(
                        tableModel.getValueAt(modelRow, 3).toString()
                    );
                }
            }
        });

        // ====================================
        // CRUD ActionListeners
        // ====================================

        addButton.addActionListener(e -> {
            addCourse();
        });

        updateButton.addActionListener(e -> {
            updateCourse();
        });

        deleteButton.addActionListener(e -> {
            deleteCourse();
        });

        searchButton.addActionListener(e -> {
            searchCourse();
        });

        clearButton.addActionListener(e -> {
            clearFields();
        });

    } // End Constructor

    // ====================================
    // Refresh Course Table
    // ====================================

    public void refreshTable() {

        tableModel.setRowCount(0);

        for (Course course : data.getCourses()) {

            Object[] row = {

                course.getCourseID(),
                course.getCourseName(),
                course.getCourseCredit(),
                course.getCourseType()

            };

            tableModel.addRow(row);
        }
    }

    // ====================================
    // Clear Input Fields
    // ====================================

    private void clearFields() {

        courseTable.clearSelection();

        idField.setText("");
        nameField.setText("");
        creditField.setText("");

        typeComboBox.setSelectedIndex(0);

        searchField.setText("");

        idField.requestFocusInWindow();
    }

    // ====================================
    // Add Course
    // ====================================

    private void addCourse() {

        String ID = idField.getText().trim();
        String Name = nameField.getText().trim();
        String creditInput = creditField.getText().trim();

        String Type =
            typeComboBox.getSelectedItem().toString();

        // Validate Course ID
        if (ID.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Course ID cannot be empty."
            );

            return;
        }

        // Check Duplicate Course ID
        for (Course course : data.getCourses()) {

            if (course.getCourseID().equalsIgnoreCase(ID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Course ID already exists."
                );

                return;
            }
        }

        // Validate Course Name
        if (Name.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Course Name cannot be empty."
            );

            return;
        }

        // Validate Course Credit
        int Credit;

        try {

            Credit = Integer.parseInt(creditInput);

            if (Credit <= 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Course Credit must be greater than 0."
                );

                return;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Course Credit. Please enter a number."
            );

            return;
        }

        // Validate Course Type
        if (!Type.equalsIgnoreCase("Lecture") &&
            !Type.equalsIgnoreCase("Practical")) {

            JOptionPane.showMessageDialog(
                this,
                "Course Type must be Lecture or Practical."
            );

            return;
        }

        // Create Course Object
        Course course = new Course(
            ID,
            Name,
            Credit,
            Type
        );

        // Add Course to ArrayList
        data.getCourses().add(course);

        // Update JTable
        refreshTable();

        // Clear Input Fields
        clearFields();

        JOptionPane.showMessageDialog(
            this,
            "Course registered successfully!"
        );
    }

    // ====================================
    // Search Course
    // ====================================

    private void searchCourse() {

        String searchID = searchField.getText().trim();

        if (searchID.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter Course ID."
            );

            return;
        }

        boolean found = false;

        for (int i = 0; i < courseTable.getRowCount(); i++) {

            String courseID =
                courseTable.getValueAt(i, 0).toString();

            if (courseID.equalsIgnoreCase(searchID)) {

                courseTable.setRowSelectionInterval(i, i);

                courseTable.scrollRectToVisible(
                    courseTable.getCellRect(i, 0, true)
                );

                found = true;

                break;
            }
        }

        if (!found) {

            courseTable.clearSelection();

            JOptionPane.showMessageDialog(
                this,
                "Course not found."
            );
        }
    }

    // ====================================
    // Update Course
    // ====================================

    private void updateCourse() {

        int selectedRow = courseTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a Course to update."
            );

            return;
        }

        int modelRow =
            courseTable.convertRowIndexToModel(selectedRow);

        String selectedID =
            tableModel.getValueAt(modelRow, 0).toString();

        String Name = nameField.getText().trim();
        String creditInput = creditField.getText().trim();

        String Type =
            typeComboBox.getSelectedItem().toString();

        // Validate Course Name
        if (Name.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Course Name cannot be empty."
            );

            return;
        }

        // Validate Course Credit
        int Credit;

        try {

            Credit = Integer.parseInt(creditInput);

            if (Credit <= 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Course Credit must be greater than 0."
                );

                return;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Course Credit. Please enter a number."
            );

            return;
        }

        // Validate Course Type
        if (!Type.equalsIgnoreCase("Lecture") &&
            !Type.equalsIgnoreCase("Practical")) {

            JOptionPane.showMessageDialog(
                this,
                "Course Type must be Lecture or Practical."
            );

            return;
        }

        // Find Course
        for (int i = 0; i < data.getCourses().size(); i++) {

            Course course = data.getCourses().get(i);

            if (course.getCourseID().equalsIgnoreCase(selectedID)) {

                // Create Updated Course Object
                Course updatedCourse = new Course(
                    selectedID,
                    Name,
                    Credit,
                    Type
                );

                // Replace Existing Course
                data.getCourses().set(i, updatedCourse);

                // Refresh JTable
                refreshTable();

                // Clear Fields
                clearFields();

                JOptionPane.showMessageDialog(
                    this,
                    "Course updated successfully!"
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
            this,
            "Course record not found."
        );
    }

    // ====================================
    // Delete Course
    // ====================================

    private void deleteCourse() {

        int selectedRow = courseTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a Course to delete."
            );

            return;
        }

        int modelRow =
            courseTable.convertRowIndexToModel(selectedRow);

        String selectedID =
            tableModel.getValueAt(modelRow, 0).toString();

        // Prevent deleting a Course used by Class Sessions
        for (ClassSession session : data.getClassSessions()) {

            if (session.getCourseID().equalsIgnoreCase(selectedID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Cannot delete Course " + selectedID
                    + " because it is assigned to a Class Session."
                );

                return;
            }
        }

        // Confirm Delete
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete Course " + selectedID + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Find and Delete Course
        for (int i = 0; i < data.getCourses().size(); i++) {

            Course course = data.getCourses().get(i);

            if (course.getCourseID().equalsIgnoreCase(selectedID)) {

                data.getCourses().remove(i);

                refreshTable();

                clearFields();

                JOptionPane.showMessageDialog(
                    this,
                    "Course deleted successfully!"
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
            this,
            "Course record not found."
        );
    }

} // End CoursePanel Class