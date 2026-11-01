package hkts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LaboratoryPanel extends JPanel {

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
    private JTextField specificationField;
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

    private JTable laboratoryTable;
    private DefaultTableModel tableModel;

    // ====================================
    // Constructor
    // ====================================

    public LaboratoryPanel(HKTSData data) {

        this.data = data;

        setLayout(new BorderLayout(10, 10));

        setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // ====================================
        // Page Title
        // ====================================

        JLabel title = new JLabel("Laboratory Management");

        title.setFont(
            new Font("Arial", Font.BOLD, 24)
        );

        // ====================================
        // Input Form
        // ====================================

        JPanel formPanel = new JPanel(
            new GridLayout(5, 2, 10, 10)
        );

        idField = new JTextField();
        nameField = new JTextField();
        capacityField = new JTextField();
        locationField = new JTextField();
        specificationField = new JTextField();

        formPanel.add(new JLabel("Laboratory ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Laboratory Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Laboratory Capacity:"));
        formPanel.add(capacityField);

        formPanel.add(new JLabel("Laboratory Location:"));
        formPanel.add(locationField);

        formPanel.add(new JLabel("Laboratory Specification:"));
        formPanel.add(specificationField);

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

        searchPanel.add(new JLabel("Search Laboratory ID:"));
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
        // Laboratory Table
        // ====================================

        String[] columnNames = {
            "Laboratory ID",
            "Laboratory Name",
            "Laboratory Capacity",
            "Laboratory Location",
            "Laboratory Specification"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        laboratoryTable = new JTable(tableModel);

        laboratoryTable.setRowHeight(25);

        laboratoryTable.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(laboratoryTable);

        // ====================================
        // Add Components
        // ====================================

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // ====================================
        // Display Laboratory Records
        // ====================================

        refreshTable();

        // ====================================
        // Select Laboratory from Table
        // ====================================

        laboratoryTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int selectedRow = laboratoryTable.getSelectedRow();

                if (selectedRow != -1) {

                    int modelRow =
                        laboratoryTable.convertRowIndexToModel(selectedRow);

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

                    specificationField.setText(
                        tableModel.getValueAt(modelRow, 4).toString()
                    );
                }
            }
        });

        // ====================================
        // CRUD ActionListeners
        // ====================================

        addButton.addActionListener(e -> {
            addLaboratory();
        });

        updateButton.addActionListener(e -> {
            updateLaboratory();
        });

        deleteButton.addActionListener(e -> {
            deleteLaboratory();
        });

        searchButton.addActionListener(e -> {
            searchLaboratory();
        });

        clearButton.addActionListener(e -> {
            clearFields();
        });

    } // End Constructor

    // ====================================
    // Refresh Laboratory Table
    // ====================================

    public void refreshTable() {

        tableModel.setRowCount(0);

        for (Laboratory laboratory : data.getLaboratories()) {

            Object[] row = {

                laboratory.getLabID(),
                laboratory.getLabName(),
                laboratory.getLabCapacity(),
                laboratory.getLabLocation(),
                laboratory.getLabSpecification()

            };

            tableModel.addRow(row);
        }
    }

    // ====================================
    // Clear Input Fields
    // ====================================

    private void clearFields() {

        laboratoryTable.clearSelection();

        idField.setText("");
        nameField.setText("");
        capacityField.setText("");
        locationField.setText("");
        specificationField.setText("");
        searchField.setText("");

        idField.requestFocusInWindow();
    }

    // ====================================
    // Add Laboratory
    // ====================================

    private void addLaboratory() {

        String ID = idField.getText().trim();
        String Name = nameField.getText().trim();
        String capacityInput = capacityField.getText().trim();
        String Location = locationField.getText().trim();
        String Specification = specificationField.getText().trim();

        // Validate Laboratory ID
        if (ID.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Laboratory ID cannot be empty."
            );

            return;
        }

        // Check Duplicate Laboratory ID
        for (Laboratory laboratory : data.getLaboratories()) {

            if (laboratory.getLabID().equalsIgnoreCase(ID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Laboratory ID already exists."
                );

                return;
            }
        }

        // Prevent duplicate room ID across Classrooms and Laboratories
        for (Classroom classroom : data.getClassrooms()) {

            if (classroom.getClassroomID().equalsIgnoreCase(ID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Room ID already exists in Classroom records."
                );

                return;
            }
        }

        // Validate Laboratory Name
        if (Name.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Laboratory Name cannot be empty."
            );

            return;
        }

        // Validate Laboratory Capacity
        int Capacity;

        try {

            Capacity = Integer.parseInt(capacityInput);

            if (Capacity <= 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Laboratory Capacity must be greater than 0."
                );

                return;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Laboratory Capacity. Please enter a number."
            );

            return;
        }

        // Validate Laboratory Location
        if (Location.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Laboratory Location cannot be empty."
            );

            return;
        }

        // Validate Laboratory Specification
        if (Specification.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Laboratory Specification cannot be empty."
            );

            return;
        }

        // Create Laboratory Object
        Laboratory laboratory = new Laboratory(
            ID,
            Name,
            Capacity,
            Location,
            Specification
        );

        // Add Laboratory to ArrayList
        data.getLaboratories().add(laboratory);

        // Refresh JTable
        refreshTable();

        // Clear Fields
        clearFields();

        JOptionPane.showMessageDialog(
            this,
            "Laboratory registered successfully!"
        );
    }

    // ====================================
    // Search Laboratory
    // ====================================

    private void searchLaboratory() {

        String searchID = searchField.getText().trim();

        if (searchID.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter Laboratory ID."
            );

            return;
        }

        boolean found = false;

        for (int i = 0; i < laboratoryTable.getRowCount(); i++) {

            String laboratoryID =
                laboratoryTable.getValueAt(i, 0).toString();

            if (laboratoryID.equalsIgnoreCase(searchID)) {

                laboratoryTable.setRowSelectionInterval(i, i);

                laboratoryTable.scrollRectToVisible(
                    laboratoryTable.getCellRect(i, 0, true)
                );

                found = true;

                break;
            }
        }

        if (!found) {

            laboratoryTable.clearSelection();

            JOptionPane.showMessageDialog(
                this,
                "Laboratory not found."
            );
        }
    }

    // ====================================
    // Update Laboratory
    // ====================================

    private void updateLaboratory() {

        int selectedRow = laboratoryTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a Laboratory to update."
            );

            return;
        }

        int modelRow =
            laboratoryTable.convertRowIndexToModel(selectedRow);

        String selectedID =
            tableModel.getValueAt(modelRow, 0).toString();

        String Name = nameField.getText().trim();
        String capacityInput = capacityField.getText().trim();
        String Location = locationField.getText().trim();
        String Specification = specificationField.getText().trim();

        // Validate Laboratory Name
        if (Name.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Laboratory Name cannot be empty."
            );

            return;
        }

        // Validate Laboratory Capacity
        int Capacity;

        try {

            Capacity = Integer.parseInt(capacityInput);

            if (Capacity <= 0) {

                JOptionPane.showMessageDialog(
                    this,
                    "Laboratory Capacity must be greater than 0."
                );

                return;
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Laboratory Capacity. Please enter a number."
            );

            return;
        }

        // Validate Laboratory Location
        if (Location.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Laboratory Location cannot be empty."
            );

            return;
        }

        // Validate Laboratory Specification
        if (Specification.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Laboratory Specification cannot be empty."
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
                                "Cannot update Laboratory Capacity.\n"
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

        // Find and Update Laboratory
        for (int i = 0; i < data.getLaboratories().size(); i++) {

            Laboratory laboratory = data.getLaboratories().get(i);

            if (laboratory.getLabID().equalsIgnoreCase(selectedID)) {

                Laboratory updatedLaboratory = new Laboratory(
                    selectedID,
                    Name,
                    Capacity,
                    Location,
                    Specification
                );

                data.getLaboratories().set(i, updatedLaboratory);

                refreshTable();

                clearFields();

                JOptionPane.showMessageDialog(
                    this,
                    "Laboratory updated successfully!"
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
            this,
            "Laboratory record not found."
        );
    }

    // ====================================
    // Delete Laboratory
    // ====================================

    private void deleteLaboratory() {

        int selectedRow = laboratoryTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a Laboratory to delete."
            );

            return;
        }

        int modelRow =
            laboratoryTable.convertRowIndexToModel(selectedRow);

        String selectedID =
            tableModel.getValueAt(modelRow, 0).toString();

        // Prevent deleting a Laboratory used by Class Sessions
        for (ClassSession session : data.getClassSessions()) {

            if (session.getRoomID().equalsIgnoreCase(selectedID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Cannot delete Laboratory " + selectedID
                    + " because it is assigned to a Class Session."
                );

                return;
            }
        }

        // Confirm Delete
        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete Laboratory "
                + selectedID + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        // Find and Delete Laboratory
        for (int i = 0; i < data.getLaboratories().size(); i++) {

            Laboratory laboratory = data.getLaboratories().get(i);

            if (laboratory.getLabID().equalsIgnoreCase(selectedID)) {

                data.getLaboratories().remove(i);

                refreshTable();

                clearFields();

                JOptionPane.showMessageDialog(
                    this,
                    "Laboratory deleted successfully!"
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
            this,
            "Laboratory record not found."
        );
    }

} // End LaboratoryPanel Class