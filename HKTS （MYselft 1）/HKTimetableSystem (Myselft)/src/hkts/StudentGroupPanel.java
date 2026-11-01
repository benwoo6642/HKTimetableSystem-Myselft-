package hkts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StudentGroupPanel extends JPanel {

    // ====================================
    // Shared Data
    // ====================================

    private HKTSData data;

    // ====================================
    // Input Fields
    // ====================================

    private JTextField idField;
    private JTextField nameField;
    private JTextField intakeTimeField;
    private JTextField departmentField;
    private JTextField sizeField;
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

    private JTable groupTable;
    private DefaultTableModel tableModel;

    // ====================================
    // Constructor
    // ====================================

    public StudentGroupPanel(HKTSData data) {

        this.data = data;

        setLayout(new BorderLayout(10, 10));

        setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // ====================================
        // Page Title
        // ====================================

        JLabel title = new JLabel("Student Group Management");

        title.setFont(new Font("Arial", Font.BOLD, 24));

        // ====================================
        // Input Form
        // ====================================

        JPanel formPanel = new JPanel(
            new GridLayout(5, 2, 10, 10)
        );

        idField = new JTextField();
        nameField = new JTextField();
        intakeTimeField = new JTextField();
        departmentField = new JTextField();
        sizeField = new JTextField();

        formPanel.add(new JLabel("Student Group ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Student Group Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Intake Time:"));
        formPanel.add(intakeTimeField);

        formPanel.add(new JLabel("Department:"));
        formPanel.add(departmentField);

        formPanel.add(new JLabel("Student Group Size:"));
        formPanel.add(sizeField);

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

        searchPanel.add(new JLabel("Search Student Group ID:"));
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
        // Student Group Table
        // ====================================

        String[] columnNames = {
            "Student Group ID",
            "Student Group Name",
            "Intake Time",
            "Department",
            "Student Group Size"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        groupTable = new JTable(tableModel);

        groupTable.setRowHeight(25);

        groupTable.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(groupTable);

        // ====================================
        // Add Components
        // ====================================

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // ====================================
        // Display Student Group Records
        // ====================================

        refreshTable();

        // ====================================
        // Select Student Group from Table
        // ====================================

        groupTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int selectedRow = groupTable.getSelectedRow();

                if (selectedRow != -1) {

                    int modelRow =
                        groupTable.convertRowIndexToModel(selectedRow);

                    idField.setText(
                        tableModel.getValueAt(modelRow, 0).toString()
                    );

                    nameField.setText(
                        tableModel.getValueAt(modelRow, 1).toString()
                    );

                    intakeTimeField.setText(
                        tableModel.getValueAt(modelRow, 2).toString()
                    );

                    departmentField.setText(
                        tableModel.getValueAt(modelRow, 3).toString()
                    );

                    sizeField.setText(
                        tableModel.getValueAt(modelRow, 4).toString()
                    );
                }
            }
        });

        // ====================================
        // CRUD ActionListeners
        // ====================================

        addButton.addActionListener(e -> {
            addStudentGroup();
        });

        updateButton.addActionListener(e -> {
            updateStudentGroup();
        });

        deleteButton.addActionListener(e -> {
            deleteStudentGroup();
        });

        searchButton.addActionListener(e -> {
            searchStudentGroup();
        });

        clearButton.addActionListener(e -> {
            clearFields();
        });

    } // End Constructor

    // ====================================
    // Refresh Student Group Table
    // ====================================

    public void refreshTable() {

        tableModel.setRowCount(0);

        for (StudentGroup group : data.getStudentGroups()) {

            Object[] row = {
                group.getStudentGroupID(),
                group.getStudentGroupName(),
                group.getStudentGroupIntakeTime(),
                group.getStudentGroupDepartment(),
                group.getStudentGroupSize()
            };

            tableModel.addRow(row);
        }
    }

    // ====================================
    // Clear Input Fields
    // ====================================

    private void clearFields() {

        groupTable.clearSelection();

        idField.setText("");
        nameField.setText("");
        intakeTimeField.setText("");
        departmentField.setText("");
        sizeField.setText("");
        searchField.setText("");

        idField.requestFocusInWindow();
    }

    // ====================================
    // Add Student Group
    // ====================================

    private void addStudentGroup() {

        String ID = idField.getText().trim();
        String Name = nameField.getText().trim();
        String IntakeTime = intakeTimeField.getText().trim();
        String Department = departmentField.getText().trim();
        String sizeInput = sizeField.getText().trim();

        // Validate Student Group ID
        if (ID.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Student Group ID cannot be empty."
            );

            return;
        }

        // Check Duplicate Student Group ID
        for (StudentGroup group : data.getStudentGroups()) {

            if (group.getStudentGroupID().equalsIgnoreCase(ID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Student Group ID already exists."
                );

                return;
            }
        }

        // Validate Student Group Name
        if (Name.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Student Group Name cannot be empty."
            );

            return;
        }

        // Validate Intake Time
        if (IntakeTime.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Intake Time cannot be empty."
            );

            return;
        }

        // Validate Department
        if (Department.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Department cannot be empty."
            );

            return;
        }

        // Validate Student Group Size
        int Size;

        try {

            Size = Integer.parseInt(sizeInput);

            if (Size <= 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Student Group Size must be greater than 0."
                );

                return;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Student Group Size. Please enter a number."
            );

            return;
        }

        // Create Student Group Object
        StudentGroup group = new StudentGroup(
            ID,
            Name,
            IntakeTime,
            Department,
            Size
        );

        // Add Student Group to ArrayList
        data.getStudentGroups().add(group);

        // Refresh JTable
        refreshTable();

        // Clear Fields
        clearFields();

        JOptionPane.showMessageDialog(
            this,
            "Student Group registered successfully!"
        );
    }

    // ====================================
    // Search Student Group
    // ====================================

    private void searchStudentGroup() {

        String searchID = searchField.getText().trim();

        if (searchID.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter Student Group ID."
            );

            return;
        }

        boolean found = false;

        for (int i = 0; i < groupTable.getRowCount(); i++) {

            String groupID =
                groupTable.getValueAt(i, 0).toString();

            if (groupID.equalsIgnoreCase(searchID)) {

                groupTable.setRowSelectionInterval(i, i);

                groupTable.scrollRectToVisible(
                    groupTable.getCellRect(i, 0, true)
                );

                found = true;

                break;
            }
        }

        if (!found) {

            groupTable.clearSelection();

            JOptionPane.showMessageDialog(
                this,
                "Student Group not found."
            );
        }
    }

    // ====================================
    // Update Student Group
    // ====================================

    private void updateStudentGroup() {

        int selectedRow = groupTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a Student Group to update."
            );

            return;
        }

        int modelRow =
            groupTable.convertRowIndexToModel(selectedRow);

        String selectedID =
            tableModel.getValueAt(modelRow, 0).toString();

        String Name = nameField.getText().trim();
        String IntakeTime = intakeTimeField.getText().trim();
        String Department = departmentField.getText().trim();
        String sizeInput = sizeField.getText().trim();

        // Validate Student Group Name
        if (Name.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Student Group Name cannot be empty."
            );

            return;
        }

        // Validate Intake Time
        if (IntakeTime.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Intake Time cannot be empty."
            );

            return;
        }

        // Validate Department
        if (Department.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Department cannot be empty."
            );

            return;
        }

        // Validate Student Group Size
        int Size;

        try {

            Size = Integer.parseInt(sizeInput);

            if (Size <= 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Student Group Size must be greater than 0."
                );

                return;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Student Group Size. Please enter a number."
            );

            return;
        }

        // Find and Update Student Group
        for (int i = 0; i < data.getStudentGroups().size(); i++) {

            StudentGroup group = data.getStudentGroups().get(i);

            if (group.getStudentGroupID().equalsIgnoreCase(selectedID)) {

                StudentGroup updatedGroup = new StudentGroup(
                    selectedID,
                    Name,
                    IntakeTime,
                    Department,
                    Size
                );

                data.getStudentGroups().set(i, updatedGroup);

                refreshTable();

                clearFields();

                JOptionPane.showMessageDialog(
                    this,
                    "Student Group updated successfully!"
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
            this,
            "Student Group record not found."
        );
    }

    // ====================================
    // Delete Student Group
    // ====================================

    private void deleteStudentGroup() {

        int selectedRow = groupTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a Student Group to delete."
            );

            return;
        }

        int modelRow =
            groupTable.convertRowIndexToModel(selectedRow);

        String selectedID =
            tableModel.getValueAt(modelRow, 0).toString();

        // Prevent deleting a Group used by Class Sessions
        for (ClassSession session : data.getClassSessions()) {

            if (session.getStudentGroupID().equalsIgnoreCase(selectedID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Cannot delete Student Group " + selectedID
                    + " because it is assigned to a Class Session."
                );

                return;
            }
        }

        // Confirm Delete
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete Student Group "
                + selectedID + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Find and Delete Student Group
        for (int i = 0; i < data.getStudentGroups().size(); i++) {

            StudentGroup group = data.getStudentGroups().get(i);

            if (group.getStudentGroupID().equalsIgnoreCase(selectedID)) {

                data.getStudentGroups().remove(i);

                refreshTable();

                clearFields();

                JOptionPane.showMessageDialog(
                    this,
                    "Student Group deleted successfully!"
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
            this,
            "Student Group record not found."
        );
    }

} // End StudentGroupPanel Class