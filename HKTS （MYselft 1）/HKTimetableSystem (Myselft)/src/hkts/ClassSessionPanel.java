package hkts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class ClassSessionPanel extends JPanel {

    // ====================================
    // Shared Data
    // ====================================

    private HKTSData data;

    // ====================================
    // Input Fields
    // ====================================

    private JTextField idField;
    private JTextField searchField;

    private JComboBox<String> courseComboBox;
    private JComboBox<String> lecturerComboBox;
    private JComboBox<String> groupComboBox;
    private JComboBox<String> dayComboBox;
    private JComboBox<String> startTimeComboBox;
    private JComboBox<String> endTimeComboBox;
    private JComboBox<String> roomComboBox;

    // ====================================
    // Buttons
    // ====================================

    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton searchButton;
    private JButton refreshButton;

    // ====================================
    // Table
    // ====================================

    private JTable sessionTable;
    private DefaultTableModel tableModel;

    // ====================================
    // Constructor
    // ====================================

    public ClassSessionPanel(HKTSData data) {

        this.data = data;

        setLayout(new BorderLayout(10, 10));

        setBorder(
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        );

        // ====================================
        // Page Title
        // ====================================

        JLabel title = new JLabel("Class Session Management");

        title.setFont(
            new Font("Arial", Font.BOLD, 24)
        );

        // ====================================
        // Input Form
        // ====================================

        JPanel formPanel = new JPanel(
            new GridLayout(8, 2, 10, 10)
        );

        idField = new JTextField();

        courseComboBox = new JComboBox<>();
        lecturerComboBox = new JComboBox<>();
        groupComboBox = new JComboBox<>();
        roomComboBox = new JComboBox<>();

        String[] days = {
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
        };

        dayComboBox = new JComboBox<>(days);

        String[] times = {
            "08:00",
            "09:00",
            "10:00",
            "11:00",
            "12:00",
            "13:00",
            "14:00",
            "15:00",
            "16:00",
            "17:00",
            "18:00",
            "19:00",
            "20:00"
        };

        startTimeComboBox = new JComboBox<>(times);
        endTimeComboBox = new JComboBox<>(times);

        formPanel.add(new JLabel("Session ID:"));
        formPanel.add(idField);

        formPanel.add(new JLabel("Course:"));
        formPanel.add(courseComboBox);

        formPanel.add(new JLabel("Lecturer:"));
        formPanel.add(lecturerComboBox);

        formPanel.add(new JLabel("Student Group:"));
        formPanel.add(groupComboBox);

        formPanel.add(new JLabel("Day:"));
        formPanel.add(dayComboBox);

        formPanel.add(new JLabel("Start Time:"));
        formPanel.add(startTimeComboBox);

        formPanel.add(new JLabel("End Time:"));
        formPanel.add(endTimeComboBox);

        formPanel.add(new JLabel("Room:"));
        formPanel.add(roomComboBox);

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
        refreshButton = new JButton("Refresh");

        refreshButton.setPreferredSize(new Dimension(205, 40));
        refreshButton.setMinimumSize(new Dimension(205, 40));
        
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(refreshButton);

        // ====================================
        // Search Panel
        // ====================================

        JPanel searchPanel = new JPanel(
            new FlowLayout(FlowLayout.LEFT)
        );

        searchField = new JTextField(20);
        searchButton = new JButton("Search");

        searchPanel.add(new JLabel("Search Session ID:"));
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
        topPanel.add(Box.createVerticalStrut(15));

        topPanel.add(formPanel);
        topPanel.add(Box.createVerticalStrut(10));

        topPanel.add(buttonPanel);
        topPanel.add(Box.createVerticalStrut(5));

        topPanel.add(searchPanel);

     // ====================================
     // Class Session Table
     // ====================================

     String[] columnNames = {
         "Session ID",
         "Course ID",
         "Lecturer ID",
         "Student Group ID",
         "Day",
         "Start Time",
         "End Time",
         "Room ID"
     };

     tableModel = new DefaultTableModel(columnNames, 0) {

         @Override
         public boolean isCellEditable(int row, int column) {
             return false;
         }
     };

     sessionTable = new JTable(tableModel);

     sessionTable.setRowHeight(30);

     // Keep column widths fixed
     sessionTable.setAutoResizeMode(
         JTable.AUTO_RESIZE_OFF
     );

     // Allow single row selection
     sessionTable.setSelectionMode(
         ListSelectionModel.SINGLE_SELECTION
     );

     // ====================================
     // Set Column Widths
     // ====================================

     int[] columnWidths = {
         110,  // Session ID
         110,  // Course ID
         120,  // Lecturer ID
         160,  // Student Group ID
         110,  // Day
         120,  // Start Time
         120,  // End Time
         110   // Room ID
     };

     for (int i = 0; i < columnWidths.length; i++) {

         sessionTable.getColumnModel()
             .getColumn(i)
             .setPreferredWidth(columnWidths[i]);
     }

     // ====================================
     // Table Scroll Pane
     // ====================================

     JScrollPane scrollPane = new JScrollPane(sessionTable);

     scrollPane.setPreferredSize(
         new Dimension(800, 300)
     );

     // Enable horizontal scrolling
     scrollPane.setHorizontalScrollBarPolicy(
         JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
     );

     // Enable vertical scrolling
     scrollPane.setVerticalScrollBarPolicy(
         JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
     );
     // ====================================
     // Scrollable Class Session Page
     // ====================================

     JPanel pageContent = new JPanel(new BorderLayout(10, 15));

     pageContent.add(topPanel, BorderLayout.NORTH);

     // Give the table enough height
     scrollPane.setPreferredSize(new Dimension(600, 320));

     pageContent.add(scrollPane, BorderLayout.CENTER);

     // Allow the entire page to scroll vertically
     JScrollPane pageScroll = new JScrollPane(pageContent);

     pageScroll.setVerticalScrollBarPolicy(
         JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
     );

     pageScroll.setHorizontalScrollBarPolicy(
         JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
     );

     pageScroll.getVerticalScrollBar().setUnitIncrement(16);

     pageScroll.setBorder(null);

     add(pageScroll, BorderLayout.CENTER);

        // ====================================
        // Load Existing Data
        // ====================================

        refreshOptions();
        refreshTable();

        // ====================================
        // Course Selection Listener
        // ====================================

        courseComboBox.addActionListener(e -> {
            refreshRoomOptions();
        });

        // ====================================
        // Table Selection Listener
        // ====================================

        sessionTable.getSelectionModel().addListSelectionListener(e -> {

            if (!e.getValueIsAdjusting()) {

                int selectedRow = sessionTable.getSelectedRow();

                if (selectedRow != -1) {

                    int modelRow =
                        sessionTable.convertRowIndexToModel(selectedRow);

                    idField.setText(
                        tableModel.getValueAt(modelRow, 0).toString()
                    );

                    courseComboBox.setSelectedItem(
                        tableModel.getValueAt(modelRow, 1).toString()
                    );

                    lecturerComboBox.setSelectedItem(
                        tableModel.getValueAt(modelRow, 2).toString()
                    );

                    groupComboBox.setSelectedItem(
                        tableModel.getValueAt(modelRow, 3).toString()
                    );

                    dayComboBox.setSelectedItem(
                        tableModel.getValueAt(modelRow, 4).toString()
                    );

                    startTimeComboBox.setSelectedItem(
                        tableModel.getValueAt(modelRow, 5).toString()
                    );

                    endTimeComboBox.setSelectedItem(
                        tableModel.getValueAt(modelRow, 6).toString()
                    );

                    roomComboBox.setSelectedItem(
                        tableModel.getValueAt(modelRow, 7).toString()
                    );
                }
            }
        });

        // ====================================
        // Action Listeners
        // ====================================

        addButton.addActionListener(e -> {
            addClassSession();
        });

        updateButton.addActionListener(e -> {
            updateClassSession();
        });

        deleteButton.addActionListener(e -> {
            deleteClassSession();
        });

        searchButton.addActionListener(e -> {
            searchClassSession();
        });

        clearButton.addActionListener(e -> {
            clearFields();
        });

        refreshButton.addActionListener(e -> {
            refreshOptions();
            refreshTable();
            clearFields();
        });

    } // End Constructor

    // ====================================
    // Refresh ComboBox Options
    // ====================================

    public void refreshOptions() {

        courseComboBox.removeAllItems();
        lecturerComboBox.removeAllItems();
        groupComboBox.removeAllItems();

        for (Course course : data.getCourses()) {
            courseComboBox.addItem(course.getCourseID());
        }

        for (Lecturer lecturer : data.getLecturers()) {
            lecturerComboBox.addItem(lecturer.getLecturerID());
        }

        for (StudentGroup group : data.getStudentGroups()) {
            groupComboBox.addItem(group.getStudentGroupID());
        }

        refreshRoomOptions();
    }

    // ====================================
    // Refresh Room Options
    // ====================================

    private void refreshRoomOptions() {

        roomComboBox.removeAllItems();

        String courseID =
            (String) courseComboBox.getSelectedItem();

        if (courseID == null) {
            return;
        }

        Course selectedCourse = null;

        for (Course course : data.getCourses()) {

            if (course.getCourseID().equalsIgnoreCase(courseID)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse == null) {
            return;
        }

        String courseType = selectedCourse.getCourseType();

        if (courseType.equalsIgnoreCase("Lecture")) {

            for (Classroom classroom : data.getClassrooms()) {
                roomComboBox.addItem(classroom.getClassroomID());
            }

        } else if (courseType.equalsIgnoreCase("Practical")) {

            for (Laboratory laboratory : data.getLaboratories()) {
                roomComboBox.addItem(laboratory.getLabID());
            }
        }
    }

    // ====================================
    // Refresh JTable
    // ====================================

    public void refreshTable() {

        tableModel.setRowCount(0);

        for (ClassSession session : data.getClassSessions()) {

            Object[] row = {
                session.getSessionID(),
                session.getCourseID(),
                session.getLecturerID(),
                session.getStudentGroupID(),
                session.getDay(),
                session.getStartTime(),
                session.getEndTime(),
                session.getRoomID()
            };

            tableModel.addRow(row);
        }
    }

    // ====================================
    // Clear Fields
    // ====================================

    private void clearFields() {

        sessionTable.clearSelection();

        idField.setText("");
        searchField.setText("");

        if (courseComboBox.getItemCount() > 0) {
            courseComboBox.setSelectedIndex(0);
        }

        if (lecturerComboBox.getItemCount() > 0) {
            lecturerComboBox.setSelectedIndex(0);
        }

        if (groupComboBox.getItemCount() > 0) {
            groupComboBox.setSelectedIndex(0);
        }

        dayComboBox.setSelectedIndex(0);
        startTimeComboBox.setSelectedIndex(0);
        endTimeComboBox.setSelectedIndex(0);

        refreshRoomOptions();

        idField.requestFocusInWindow();
    }

    // ====================================
    // Validate Class Session
    // ====================================

    private boolean validateSession(
            String sessionID,
            String courseID,
            String lecturerID,
            String groupID,
            String day,
            String startTime,
            String endTime,
            String roomID,
            boolean isUpdate) {

        // ====================================
        // Validate Required Fields
        // ====================================

        if (sessionID.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Session ID cannot be empty."
            );

            return false;
        }

        if (courseID == null ||
            lecturerID == null ||
            groupID == null ||
            roomID == null) {

            JOptionPane.showMessageDialog(
                this,
                "Please register Course, Lecturer, Student Group and Room first."
            );

            return false;
        }

        // ====================================
        // Check Duplicate Session ID
        // ====================================

        if (!isUpdate) {

            for (ClassSession session : data.getClassSessions()) {

                if (session.getSessionID().equalsIgnoreCase(sessionID)) {

                    JOptionPane.showMessageDialog(
                        this,
                        "Session ID already exists."
                    );

                    return false;
                }
            }
        }

        // ====================================
        // Validate Time
        // ====================================

        LocalTime start;
        LocalTime end;

        try {

            start = LocalTime.parse(startTime);
            end = LocalTime.parse(endTime);

        } catch (DateTimeParseException e) {

            JOptionPane.showMessageDialog(
                this,
                "Invalid time format."
            );

            return false;
        }

        if (!start.isBefore(end)) {

            JOptionPane.showMessageDialog(
                this,
                "End Time must be later than Start Time."
            );

            return false;
        }

        // ====================================
        // Find Selected Course
        // ====================================

        Course selectedCourse = null;

        for (Course course : data.getCourses()) {

            if (course.getCourseID().equalsIgnoreCase(courseID)) {
                selectedCourse = course;
                break;
            }
        }

        if (selectedCourse == null) {

            JOptionPane.showMessageDialog(
                this,
                "Selected Course does not exist."
            );

            return false;
        }

        // ====================================
        // Validate Lecturer
        // ====================================

        boolean lecturerExists = false;

        for (Lecturer lecturer : data.getLecturers()) {

            if (lecturer.getLecturerID().equalsIgnoreCase(lecturerID)) {
                lecturerExists = true;
                break;
            }
        }

        if (!lecturerExists) {

            JOptionPane.showMessageDialog(
                this,
                "Selected Lecturer does not exist."
            );

            return false;
        }

        // ====================================
        // Find Selected Student Group
        // ====================================

        StudentGroup selectedGroup = null;

        for (StudentGroup group : data.getStudentGroups()) {

            if (group.getStudentGroupID().equalsIgnoreCase(groupID)) {
                selectedGroup = group;
                break;
            }
        }

        if (selectedGroup == null) {

            JOptionPane.showMessageDialog(
                this,
                "Selected Student Group does not exist."
            );

            return false;
        }

        // ====================================
        // Validate Room Type and Capacity
        // ====================================

        int roomCapacity = -1;

        if (selectedCourse.getCourseType().equalsIgnoreCase("Lecture")) {

            for (Classroom classroom : data.getClassrooms()) {

                if (classroom.getClassroomID().equalsIgnoreCase(roomID)) {
                    roomCapacity = classroom.getClassroomCapacity();
                    break;
                }
            }

        } else if (selectedCourse.getCourseType().equalsIgnoreCase("Practical")) {

            for (Laboratory laboratory : data.getLaboratories()) {

                if (laboratory.getLabID().equalsIgnoreCase(roomID)) {
                    roomCapacity = laboratory.getLabCapacity();
                    break;
                }
            }

        } else {

            JOptionPane.showMessageDialog(
                this,
                "Invalid Course Type."
            );

            return false;
        }

        if (roomCapacity == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Selected Room does not match the Course Type."
            );

            return false;
        }

        if (selectedGroup.getStudentGroupSize() > roomCapacity) {

            JOptionPane.showMessageDialog(
                this,
                "Room Capacity is insufficient.\n"
                + "Student Group Size: "
                + selectedGroup.getStudentGroupSize()
                + "\nRoom Capacity: "
                + roomCapacity
            );

            return false;
        }

        // ====================================
        // Scheduling Conflict Validation
        // ====================================

        for (ClassSession existing : data.getClassSessions()) {

            // Skip the current session during Update
            if (isUpdate &&
                existing.getSessionID().equalsIgnoreCase(sessionID)) {
                continue;
            }

            // Different days cannot conflict
            if (!existing.getDay().equalsIgnoreCase(day)) {
                continue;
            }

            LocalTime existingStart;
            LocalTime existingEnd;

            try {

                existingStart = LocalTime.parse(existing.getStartTime());
                existingEnd = LocalTime.parse(existing.getEndTime());

            } catch (DateTimeParseException e) {

                JOptionPane.showMessageDialog(
                    this,
                    "Existing Session "
                    + existing.getSessionID()
                    + " has an invalid time format."
                );

                return false;
            }

            // Time overlap condition
            boolean timeOverlap =
                start.isBefore(existingEnd) &&
                end.isAfter(existingStart);

            if (!timeOverlap) {
                continue;
            }

            // Lecturer Conflict
            if (existing.getLecturerID().equalsIgnoreCase(lecturerID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Lecturer Conflict Detected!\n"
                    + "Lecturer " + lecturerID
                    + " is already assigned to Session "
                    + existing.getSessionID()
                );

                return false;
            }

            // Student Group Conflict
            if (existing.getStudentGroupID().equalsIgnoreCase(groupID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Student Group Conflict Detected!\n"
                    + "Student Group " + groupID
                    + " is already assigned to Session "
                    + existing.getSessionID()
                );

                return false;
            }

            // Room Conflict
            if (existing.getRoomID().equalsIgnoreCase(roomID)) {

                JOptionPane.showMessageDialog(
                    this,
                    "Room Conflict Detected!\n"
                    + "Room " + roomID
                    + " is already assigned to Session "
                    + existing.getSessionID()
                );

                return false;
            }
        }

        return true;
    }

    // ====================================
    // Add Class Session
    // ====================================

    private void addClassSession() {

        String ID = idField.getText().trim();

        String CourseID =
            (String) courseComboBox.getSelectedItem();

        String LecturerID =
            (String) lecturerComboBox.getSelectedItem();

        String GroupID =
            (String) groupComboBox.getSelectedItem();

        String Day =
            (String) dayComboBox.getSelectedItem();

        String Start =
            (String) startTimeComboBox.getSelectedItem();

        String End =
            (String) endTimeComboBox.getSelectedItem();

        String RoomID =
            (String) roomComboBox.getSelectedItem();

        boolean valid = validateSession(
            ID,
            CourseID,
            LecturerID,
            GroupID,
            Day,
            Start,
            End,
            RoomID,
            false
        );

        if (!valid) {
            return;
        }

        ClassSession session = new ClassSession(
            ID,
            CourseID,
            LecturerID,
            GroupID,
            Day,
            Start,
            End,
            RoomID
        );

        data.getClassSessions().add(session);

        refreshTable();
        clearFields();

        JOptionPane.showMessageDialog(
            this,
            "Class Session registered successfully!"
        );
    }

    // ====================================
    // Search Class Session
    // ====================================

    private void searchClassSession() {

        String searchID = searchField.getText().trim();

        if (searchID.isBlank()) {

            JOptionPane.showMessageDialog(
                this,
                "Please enter Session ID."
            );

            return;
        }

        boolean found = false;

        for (int i = 0; i < sessionTable.getRowCount(); i++) {

            String sessionID =
                sessionTable.getValueAt(i, 0).toString();

            if (sessionID.equalsIgnoreCase(searchID)) {

                sessionTable.setRowSelectionInterval(i, i);

                sessionTable.scrollRectToVisible(
                    sessionTable.getCellRect(i, 0, true)
                );

                found = true;
                break;
            }
        }

        if (!found) {

            sessionTable.clearSelection();

            JOptionPane.showMessageDialog(
                this,
                "Class Session not found."
            );
        }
    }

    // ====================================
    // Update Class Session
    // ====================================

    private void updateClassSession() {

        int selectedRow = sessionTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a Class Session to update."
            );

            return;
        }

        int modelRow =
            sessionTable.convertRowIndexToModel(selectedRow);

        String selectedID =
            tableModel.getValueAt(modelRow, 0).toString();

        String CourseID =
            (String) courseComboBox.getSelectedItem();

        String LecturerID =
            (String) lecturerComboBox.getSelectedItem();

        String GroupID =
            (String) groupComboBox.getSelectedItem();

        String Day =
            (String) dayComboBox.getSelectedItem();

        String Start =
            (String) startTimeComboBox.getSelectedItem();

        String End =
            (String) endTimeComboBox.getSelectedItem();

        String RoomID =
            (String) roomComboBox.getSelectedItem();

        boolean valid = validateSession(
            selectedID,
            CourseID,
            LecturerID,
            GroupID,
            Day,
            Start,
            End,
            RoomID,
            true
        );

        if (!valid) {
            return;
        }

        for (int i = 0; i < data.getClassSessions().size(); i++) {

            ClassSession session = data.getClassSessions().get(i);

            if (session.getSessionID().equalsIgnoreCase(selectedID)) {

                ClassSession updatedSession = new ClassSession(
                    selectedID,
                    CourseID,
                    LecturerID,
                    GroupID,
                    Day,
                    Start,
                    End,
                    RoomID
                );

                data.getClassSessions().set(i, updatedSession);

                refreshTable();
                clearFields();

                JOptionPane.showMessageDialog(
                    this,
                    "Class Session updated successfully!"
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
            this,
            "Class Session record not found."
        );
    }

    // ====================================
    // Delete Class Session
    // ====================================

    private void deleteClassSession() {

        int selectedRow = sessionTable.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                this,
                "Please select a Class Session to delete."
            );

            return;
        }

        int modelRow =
            sessionTable.convertRowIndexToModel(selectedRow);

        String selectedID =
            tableModel.getValueAt(modelRow, 0).toString();

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "Are you sure you want to delete Session "
                + selectedID + "?",
            "Confirm Delete",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        for (int i = 0; i < data.getClassSessions().size(); i++) {

            ClassSession session = data.getClassSessions().get(i);

            if (session.getSessionID().equalsIgnoreCase(selectedID)) {

                data.getClassSessions().remove(i);

                refreshTable();
                clearFields();

                JOptionPane.showMessageDialog(
                    this,
                    "Class Session deleted successfully!"
                );

                return;
            }
        }

        JOptionPane.showMessageDialog(
            this,
            "Class Session record not found."
        );
    }

} // End ClassSessionPanel Class