package hkts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ClassroomPanel extends JPanel {

    // ====================================
    // Shared Data
    // ====================================

    private HKTSData data;

    // ====================================
    // Input Fields
    // ====================================

    private JTextField idField;
    private JTextField nameField;
    private JTextField capacityField;
    private JTextField locationField;
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

    private JTable classroomTable;
    private DefaultTableModel tableModel;

    // ====================================
    // Constructor
    // ====================================

    public ClassroomPanel(HKTSData data) {

        this.data = data;

        setLayout(new BorderLayout(10, 10));

        setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // ====================================
        // Page Title
        // ====================================

        JLabel title = new JLabel("Classroom Management");

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
        capacityField = new JTextField();
        locationField = new JTextField();

        formPanel.add(new JLabel("Classroom ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Classroom Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Classroom Capacity:"));
        formPanel.add(capacityField);

        formPanel.add(new JLabel("Classroom Location:"));
        formPanel.add(locationField);

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

        searchPanel.add(new JLabel("Search Classroom ID:"));
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
        // Classroom Table
        // ====================================

        String[] columnNames = {
            "Classroom ID",
            "Classroom Name",
            "Classroom Capacity",
            "Classroom Location"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        classroomTable = new JTable(tableModel);

        classroomTable.setRowHeight(25);

        classroomTable.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(classroomTable);

        // ====================================
        // Add Components
        // ====================================

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // ====================================
        // Display Classroom Records
        // ====================================

        refreshTable();

        // ====================================
        // Select Classroom from Table
        // ====================================

        classroomTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int selectedRow = classroomTable.getSelectedRow();

                if (selectedRow != -1) {

                    int modelRow =
                        classroomTable.convertRowIndexToModel(selectedRow);

                    idField.setText(
                        tableModel.getValueAt(modelRow, 0).toString()
                    );

                    nameField.setText(
                        tableModel.getValueAt(modelRow, 1).toString()
                    );

                    capacityField.setText(
                        tableModel.getValueAt(modelRow, 2).toString()
                    );

                    locationField.setText(
                        tableModel.getValueAt(modelRow, 3).toString()
                    );
                }
            }
        });

        // ====================================
        // CRUD ActionListeners
        // ====================================

        addButton.addActionListener(e -> {
            addClassroom();
        });

        updateButton.addActionListener(e -> {
            updateClassroom();
        });

        deleteButton.addActionListener(e -> {
            deleteClassroom();
        });

        searchButton.addActionListener(e -> {
            searchClassroom();
        });

        clearButton.addActionListener(e -> {
            clearFields();
        });

    } // End Constructor

    // ====================================
    // Refresh Classroom Table
    // ====================================

    public void refreshTable() {

        tableModel.setRowCount(0);

        for (Classroom classroom : data.getClassrooms()) {

            Object[] row = {

                classroom.getClassroomID(),
                classroom.getClassroomName(),
                classroom.getClassroomCapacity(),
                classroom.getClassroomLocation()

            };

            tableModel.addRow(row);
        }
    }

    // ====================================
    // Clear Input Fields
    // ====================================

    private void clearFields() {

        classroomTable.clearSelection();

        idField.setText("");
        nameField.setText("");
        capacityField.setText("");
        locationField.setText("");
        searchField.setText("");

        idField.requestFocusInWindow();
    }

    // ====================================
    // Add Classroom
    // ====================================

    private void addClassroom() {

        String ID = idField.getText().trim();
        String Name = nameField.getText().trim();
        String capacityInput = capacityField.getText().trim();
        String Location = locationField.getText().trim();

        // Validate Classroom ID
        if (ID.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Classroom ID cannot be empty."
            );

            return;
        }

        // Check Duplicate Classroom ID
        for (Classroom classroom : data.getClassrooms()) {

            if (classroom.getClassroomID().equalsIgnoreCase(ID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Classroom ID already exists."
                );

                return;
            }
        }

        // Prevent duplicate room ID across Classrooms and Laboratories
        for (Laboratory laboratory : data.getLaboratories()) {

            if (laboratory.getLabID().equalsIgnoreCase(ID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Room ID already exists in Laboratory records."
                );

                return;
            }
        }

        // Validate Classroom Name
        if (Name.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Classroom Name cannot be empty."
            );

            return;
        }

        // Validate Classroom Capacity
        int Capacity;

        try {

            Capacity = Integer.parseInt(capacityInput);

            if (Capacity <= 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Classroom Capacity must be greater than 0."
                );

                return;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Classroom Capacity. Please enter a number."
            );

            return;
        }

        // Validate Classroom Location
        if (Location.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Classroom Location cannot be empty."
            );

            return;
        }

        // Create Classroom Object
        Classroom classroom = new Classroom(
            ID,
            Name,
            Capacity,
            Location
        );

        // Add Classroom to ArrayList
        data.getClassrooms().add(classroom);

        // Refresh JTable
        refreshTable();

        // Clear Fields
        clearFields();

        JOptionPane.showMessageDialog(
            this,
            "Classroom registered successfully!"
        );
    }

    // ====================================
    // Search Classroom
    // ====================================

    private void searchClassroom() {

        String searchID = searchField.getText().trim();

        if (searchID.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter Classroom ID."
            );

            return;
        }

        boolean found = false;

        for (int i = 0; i < classroomTable.getRowCount(); i++) {

            String classroomID =
                classroomTable.getValueAt(i, 0).toString();

            if (classroomID.equalsIgnoreCase(searchID)) {

                classroomTable.setRowSelectionInterval(i, i);

                classroomTable.scrollRectToVisible(
                    classroomTable.getCellRect(i, 0, true)
                );

                found = true;

                break;
            }
        }

        if (!found) {

            classroomTable.clearSelection();

            JOptionPane.showMessageDialog(
                this,
                "Classroom not found."
            );
        }
    }

    // ====================================
    // Update Classroom
    // ====================================

    private void updateClassroom() {

        int selectedRow = classroomTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a Classroom to update."
            );

            return;
        }

        int modelRow =
            classroomTable.convertRowIndexToModel(selectedRow);

        String selectedID =
            tableModel.getValueAt(modelRow, 0).toString();

        String Name = nameField.getText().trim();
        String capacityInput = capacityField.getText().trim();
        String Location = locationField.getText().trim();

        // Validate Classroom Name
        if (Name.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Classroom Name cannot be empty."
            );

            return;
        }

        // Validate Classroom Capacity
        int Capacity;

        try {

            Capacity = Integer.parseInt(capacityInput);

            if (Capacity <= 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Classroom Capacity must be greater than 0."
                );

                return;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Classroom Capacity. Please enter a number."
            );

            return;
        }

        // Validate Classroom Location
        if (Location.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Classroom Location cannot be empty."
            );

            return;
        }

        // Prevent reducing capacity below assigned Student Group size
        for (ClassSession session : data.getClassSessions()) {

            if (session.getRoomID().equalsIgnoreCase(selectedID)) {

                for (StudentGroup group : data.getStudentGroups()) {

                    if (group.getStudentGroupID().equalsIgnoreCase(
                            session.getStudentGroupID())) {

                        if (Capacity < group.getStudentGroupSize()) {

                            JOptionPane.showMessageDialog(
                                this,
                                "Cannot update Classroom Capacity.\n"
                                + "Student Group " + group.getStudentGroupID()
                                + " has " + group.getStudentGroupSize()
                                + " students."
                            );

                            return;
                        }
                    }
                }
            }
        }

        // Find and Update Classroom
        for (int i = 0; i < data.getClassrooms().size(); i++) {

            Classroom classroom = data.getClassrooms().get(i);

            if (classroom.getClassroomID().equalsIgnoreCase(selectedID)) {

                Classroom updatedClassroom = new Classroom(
                    selectedID,
                    Name,
                    Capacity,
                    Location
                );

                data.getClassrooms().set(i, updatedClassroom);

                refreshTable();

                clearFields();

                JOptionPane.showMessageDialog(
                    this,
                    "Classroom updated successfully!"
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
            this,
            "Classroom record not found."
        );
    }

    // ====================================
    // Delete Classroom
    // ====================================

    private void deleteClassroom() {

        int selectedRow = classroomTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a Classroom to delete."
            );

            return;
        }

        int modelRow =
            classroomTable.convertRowIndexToModel(selectedRow);

        String selectedID =
            tableModel.getValueAt(modelRow, 0).toString();

        // Prevent deleting a Classroom used by Class Sessions
        for (ClassSession session : data.getClassSessions()) {

            if (session.getRoomID().equalsIgnoreCase(selectedID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Cannot delete Classroom " + selectedID
                    + " because it is assigned to a Class Session."
                );

                return;
            }
        }

        // Confirm Delete
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete Classroom "
                + selectedID + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Find and Delete Classroom
        for (int i = 0; i < data.getClassrooms().size(); i++) {

            Classroom classroom = data.getClassrooms().get(i);

            if (classroom.getClassroomID().equalsIgnoreCase(selectedID)) {

                data.getClassrooms().remove(i);

                refreshTable();

                clearFields();

                JOptionPane.showMessageDialog(
                    this,
                    "Classroom deleted successfully!"
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
            this,
            "Classroom record not found."
        );
    }

} // End ClassroomPanel Class