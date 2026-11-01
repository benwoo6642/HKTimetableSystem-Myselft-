package hkts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class LecturerPanel extends JPanel {

    // Shared Data
    private HKTSData data;

    // Input Fields
    private JTextField idField;
    private JTextField nameField;
    private JTextField emailField;
    private JTextField qualificationField;
    private JTextField searchField;

    // Buttons
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton searchButton;

    // Table
    private JTable lecturerTable;
    private DefaultTableModel tableModel;

    // Constructor
    public LecturerPanel(HKTSData data) {

        this.data = data;

        setLayout(new BorderLayout(10, 10));

        setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // ====================================
        // Page Title
        // ====================================

        JLabel title = new JLabel("Lecturer Management");

        title.setFont(new Font("Arial", Font.BOLD, 24));

        // ====================================
        // Input Form
        // ====================================

        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        idField = new JTextField();
        nameField = new JTextField();
        emailField = new JTextField();
        qualificationField = new JTextField();

        formPanel.add(new JLabel("Lecturer ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Lecturer Name:"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Lecturer Email:"));
        formPanel.add(emailField);

        formPanel.add(new JLabel("Qualification:"));
        formPanel.add(qualificationField);

        // ====================================
        // CRUD Buttons
        // ====================================

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

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

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        searchField = new JTextField(20);

        searchButton = new JButton("Search");

        searchPanel.add(new JLabel("Search Lecturer:"));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);

        // ====================================
        // Top Section
        // ====================================

        JPanel topPanel = new JPanel();

        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

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
        // Lecturer Table
        // ====================================

        String[] columnNames = {
            "Lecturer ID",
            "Lecturer Name",
            "Email",
            "Qualification"
        };

        tableModel = new DefaultTableModel(columnNames, 0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        lecturerTable = new JTable(tableModel);

        lecturerTable.setRowHeight(25);

        lecturerTable.setSelectionMode(
            ListSelectionModel.SINGLE_SELECTION
        );

        JScrollPane scrollPane = new JScrollPane(lecturerTable);

        // ====================================
        // Add Components
        // ====================================

        add(topPanel, BorderLayout.NORTH);

        add(scrollPane, BorderLayout.CENTER);

        // ====================================
        // Display Lecturer Records
        // ====================================

        refreshTable();

        // ====================================
        // Select Lecturer from Table
        // ====================================

        lecturerTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int selectedRow = lecturerTable.getSelectedRow();

                if (selectedRow != -1) {

                    int modelRow =
                        lecturerTable.convertRowIndexToModel(selectedRow);

                    idField.setText(
                        tableModel.getValueAt(modelRow, 0).toString()
                    );

                    nameField.setText(
                        tableModel.getValueAt(modelRow, 1).toString()
                    );

                    emailField.setText(
                        tableModel.getValueAt(modelRow, 2).toString()
                    );

                    qualificationField.setText(
                        tableModel.getValueAt(modelRow, 3).toString()
                    );
                }
            }
        });

        // ====================================
        // Clear Button
        // ====================================

        clearButton.addActionListener(e -> {

            clearFields();

        });

     // ====================================
     // Add Lecturer Button
     // ====================================

     addButton.addActionListener(e -> {

         addLecturer();

     });
  // ====================================
  // Search Lecturer Button
  // ====================================

  searchButton.addActionListener(e -> {

      searchLecturer();

  });
  
//====================================
//Update Lecturer Button
//====================================

updateButton.addActionListener(e -> {

   updateLecturer();

});

//====================================
//Delete Lecturer Button
//====================================

deleteButton.addActionListener(e -> {

 deleteLecturer();

});
    }
    
 // ====================================
 // Delete Lecturer
 // ====================================

 private void deleteLecturer() {

     int selectedRow = lecturerTable.getSelectedRow();

     if (selectedRow == -1) {

         JOptionPane.showMessageDialog(
             this,
             "Please select a Lecturer to delete."
         );

         return;
     }

     int modelRow =
         lecturerTable.convertRowIndexToModel(selectedRow);

     String selectedID =
         tableModel.getValueAt(modelRow, 0).toString();

     int confirm = JOptionPane.showConfirmDialog(
         this,
         "Are you sure you want to delete Lecturer " + selectedID + "?",
         "Confirm Delete",
         JOptionPane.YES_NO_OPTION
     );

     if (confirm != JOptionPane.YES_OPTION) {

         return;
     }

     // Find and Delete Lecturer
     for (int i = 0; i < data.getLecturers().size(); i++) {

         Lecturer lecturer = data.getLecturers().get(i);

         if (lecturer.getLecturerID().equalsIgnoreCase(selectedID)) {

             data.getLecturers().remove(i);

             refreshTable();

             clearFields();

             JOptionPane.showMessageDialog(
                 this,
                 "Lecturer deleted successfully!"
             );

             return;
         }
     }

     JOptionPane.showMessageDialog(
         this,
         "Lecturer record not found."
     );
 }
 // ====================================
 // Update Lecturer
 // ====================================

 private void updateLecturer() {

     int selectedRow = lecturerTable.getSelectedRow();

     if (selectedRow == -1) {

         JOptionPane.showMessageDialog(
             this,
             "Please select a Lecturer to update."
         );

         return;
     }

     int modelRow =
         lecturerTable.convertRowIndexToModel(selectedRow);

     String selectedID =
         tableModel.getValueAt(modelRow, 0).toString();

     String Name = nameField.getText().trim();
     String Email = emailField.getText().trim();
     String Qualification = qualificationField.getText().trim();

     // Validate Name
     if (Name.isBlank()) {

         JOptionPane.showMessageDialog(
             this,
             "Lecturer Name cannot be empty."
         );

         return;
     }

     // Validate Email
     if (Email.isBlank() ||
         !Email.contains("@") ||
         !Email.contains(".")) {

         JOptionPane.showMessageDialog(
             this,
             "Invalid Lecturer Email."
         );

         return;
     }

     // Validate Qualification
     if (Qualification.isBlank()) {

         JOptionPane.showMessageDialog(
             this,
             "Qualification cannot be empty."
         );

         return;
     }

     // Find and Update Lecturer
     for (int i = 0; i < data.getLecturers().size(); i++) {

         Lecturer lecturer = data.getLecturers().get(i);

         if (lecturer.getLecturerID().equalsIgnoreCase(selectedID)) {

             Lecturer updatedLecturer = new Lecturer(
                 selectedID,
                 Name,
                 Email,
                 Qualification
             );

             data.getLecturers().set(i, updatedLecturer);

             refreshTable();

             clearFields();

             JOptionPane.showMessageDialog(
                 this,
                 "Lecturer updated successfully!"
             );

             return;
         }
     }

     JOptionPane.showMessageDialog(
         this,
         "Lecturer record not found."
     );
 }
    
 // ====================================
 // Search Lecturer
 // ====================================

 private void searchLecturer() {

     String searchID = searchField.getText().trim();

     if (searchID.isBlank()) {

         JOptionPane.showMessageDialog(
             this,
             "Please enter Lecturer ID."
         );

         return;
     }

     boolean found = false;

     for (int i = 0; i < lecturerTable.getRowCount(); i++) {

         String lecturerID =
             lecturerTable.getValueAt(i, 0).toString();

         if (lecturerID.equalsIgnoreCase(searchID)) {

             lecturerTable.setRowSelectionInterval(i, i);

             lecturerTable.scrollRectToVisible(
                 lecturerTable.getCellRect(i, 0, true)
             );

             found = true;

             break;
         }
     }

     if (!found) {

         JOptionPane.showMessageDialog(
             this,
             "Lecturer not found."
         );
     }
 }

 // ====================================
 // Add Lecturer
 // ====================================

 private void addLecturer() {

     String ID = idField.getText().trim();
     String Name = nameField.getText().trim();
     String Email = emailField.getText().trim();
     String Qualification = qualificationField.getText().trim();

     // Validate Lecturer ID
     if (ID.isBlank()) {

         JOptionPane.showMessageDialog(
             this,
             "Lecturer ID cannot be empty."
         );

         return;
     }

     // Check Duplicate Lecturer ID
     for (Lecturer lecturer : data.getLecturers()) {

         if (lecturer.getLecturerID().equalsIgnoreCase(ID)) {

             JOptionPane.showMessageDialog(
                 this,
                 "Lecturer ID already exists."
             );

             return;
         }
     }

     // Validate Lecturer Name
     if (Name.isBlank()) {

         JOptionPane.showMessageDialog(
             this,
             "Lecturer Name cannot be empty."
         );

         return;
     }

     // Validate Lecturer Email
     if (Email.isBlank() ||
         !Email.contains("@") ||
         !Email.contains(".")) {

         JOptionPane.showMessageDialog(
             this,
             "Invalid Lecturer Email."
         );

         return;
     }

     // Validate Qualification
     if (Qualification.isBlank()) {

         JOptionPane.showMessageDialog(
             this,
             "Qualification cannot be empty."
         );

         return;
     }

     // Create Lecturer Object
     Lecturer lecturer = new Lecturer(
         ID,
         Name,
         Email,
         Qualification
     );

     // Add Lecturer to ArrayList
     data.getLecturers().add(lecturer);

     // Update JTable
     refreshTable();

     // Clear Input Fields
     clearFields();

     JOptionPane.showMessageDialog(
         this,
         "Lecturer registered successfully!"
     );
 }
    // ====================================
    // Refresh Lecturer Table
    // ====================================

    public void refreshTable() {

        tableModel.setRowCount(0);

        for (Lecturer lecturer : data.getLecturers()) {

            Object[] row = {

                lecturer.getLecturerID(),
                lecturer.getLecturerName(),
                lecturer.getLecturerEmail(),
                lecturer.getLecturerQualification()

            };

            tableModel.addRow(row);
        }
    }

    // ====================================
    // Clear Input Fields
    // ====================================

    private void clearFields() {

        idField.setText("");
        nameField.setText("");
        emailField.setText("");
        qualificationField.setText("");
        searchField.setText("");

        lecturerTable.clearSelection();

        idField.requestFocusInWindow();
    }
}