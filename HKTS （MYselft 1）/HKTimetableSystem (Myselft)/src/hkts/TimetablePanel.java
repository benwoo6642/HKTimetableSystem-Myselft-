package hkts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TimetablePanel extends JPanel {
    private final HKTSData data;
    private final JComboBox<String> courseBox = new JComboBox<>();
    private final JComboBox<String> lecturerBox = new JComboBox<>();
    private final JComboBox<String> groupBox = new JComboBox<>();
    private final DefaultTableModel requestModel = new DefaultTableModel(
            new String[]{"Course ID", "Lecturer ID", "Student Group ID"}, 0) {
        @Override public boolean isCellEditable(int row, int column) { return false; }
    };
    private final DefaultTableModel timetableModel = new DefaultTableModel(
            new String[]{"Session ID", "Day", "Start", "End", "Course", "Lecturer", "Group", "Room"}, 0) {
        @Override public boolean isCellEditable(int row, int column) { return false; }
    };
    private final JTable requestTable = new JTable(requestModel);
    private final JTable timetableTable = new JTable(timetableModel);
    private final JTextArea summaryArea = new JTextArea(6, 30);
    private static final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    private static final LocalTime[] SLOTS = {
            LocalTime.of(9, 0), LocalTime.of(11, 0),
            LocalTime.of(13, 0), LocalTime.of(15, 0)
    };

    public TimetablePanel(HKTSData data) {
        this.data = data;
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        JLabel title = new JLabel("Generate & View Timetable");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        add(title, BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane();
        JPanel generatePage = new JPanel(new BorderLayout(8, 8));
        JPanel selectors = new JPanel(new GridLayout(3, 2, 8, 8));
        selectors.add(new JLabel("Course:")); selectors.add(courseBox);
        selectors.add(new JLabel("Lecturer:")); selectors.add(lecturerBox);
        selectors.add(new JLabel("Student Group:")); selectors.add(groupBox);
     // ====================================
     // Timetable Control Buttons
     // ====================================

     // FlowLayout allows buttons to use their preferred widths
        JPanel controls = new JPanel(
        	    new GridLayout(2, 3, 10, 10)
        	);

     // Create Buttons
     JButton refreshButton = new JButton("Refresh Options");
     JButton addRequestButton = new JButton("Add Request");
     JButton removeButton = new JButton("Remove Selected");
     JButton clearButton = new JButton("Clear Requests");
     JButton generateButton = new JButton("Generate Timetable");

     // ====================================
     // Set Button Sizes
     // ====================================

     refreshButton.setPreferredSize(new Dimension(164, 42));

     addRequestButton.setPreferredSize(new Dimension(145, 42));

     removeButton.setPreferredSize(new Dimension(170, 42));

     clearButton.setPreferredSize(new Dimension(155, 42));

     generateButton.setPreferredSize(new Dimension(190, 42));

     // ====================================
     // Add Buttons
     // ====================================

     controls.add(refreshButton);
     controls.add(addRequestButton);
     controls.add(removeButton);
     controls.add(clearButton);
     controls.add(generateButton);
     
        controls.add(refreshButton);
        controls.add(addRequestButton);
        controls.add(removeButton);
        controls.add(clearButton);
        controls.add(generateButton);
        JPanel inputArea = new JPanel(new BorderLayout(5, 5));
        inputArea.add(selectors, BorderLayout.NORTH);
        inputArea.add(controls, BorderLayout.SOUTH);
        generatePage.add(inputArea, BorderLayout.NORTH);
        generatePage.add(new JScrollPane(requestTable), BorderLayout.CENTER);
        summaryArea.setEditable(false);
        summaryArea.setLineWrap(true);
        summaryArea.setWrapStyleWord(true);
        summaryArea.setBorder(BorderFactory.createTitledBorder("Generation Summary"));
        generatePage.add(new JScrollPane(summaryArea), BorderLayout.SOUTH);

     // ====================================
     // View Timetable Page
     // ====================================

     JPanel viewPage = new JPanel(new BorderLayout(8, 8));

     // ====================================
     // Refresh Timetable Button
     // ====================================

     JButton viewRefreshButton = new JButton("Refresh Timetable");

     // Make button wide enough to display full text
     viewRefreshButton.setPreferredSize(
         new Dimension(200, 42)
     );

     viewRefreshButton.setMinimumSize(
         new Dimension(200, 42)
     );

     // ====================================
     // Top Button Panel
     // ====================================

     JPanel viewTop = new JPanel(
         new FlowLayout(FlowLayout.LEFT, 10, 10)
     );

     viewTop.add(viewRefreshButton);

     viewPage.add(
         viewTop,
         BorderLayout.NORTH
     );

     // ====================================
     // Timetable Table
     // ====================================

     // Automatically fill available width
     timetableTable.setAutoResizeMode(
         JTable.AUTO_RESIZE_ALL_COLUMNS
     );

     // Set row height
     timetableTable.setRowHeight(30);

     // Single row selection
     timetableTable.setSelectionMode(
         ListSelectionModel.SINGLE_SELECTION
     );

     // ====================================
     // Scroll Pane
     // ====================================

     JScrollPane timetableScroll =
         new JScrollPane(timetableTable);

     timetableScroll.setVerticalScrollBarPolicy(
         JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED
     );

     timetableScroll.setHorizontalScrollBarPolicy(
         JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED
     );

     viewPage.add(
         timetableScroll,
         BorderLayout.CENTER
     );
        tabs.addTab("Generate Timetable", generatePage);
        tabs.addTab("View Timetable", viewPage);
        add(tabs, BorderLayout.CENTER);

        refreshOptions();
        refreshTimetable();
        refreshButton.addActionListener(e -> refreshOptions());
        addRequestButton.addActionListener(e -> addRequest());
        removeButton.addActionListener(e -> {
            int row = requestTable.getSelectedRow();
            if (row >= 0) requestModel.removeRow(requestTable.convertRowIndexToModel(row));
            else showMessage("Please select a scheduling request to remove.");
        });
        clearButton.addActionListener(e -> requestModel.setRowCount(0));
        generateButton.addActionListener(e -> generateTimetable());
        viewRefreshButton.addActionListener(e -> refreshTimetable());
        tabs.addChangeListener(e -> {
            if (tabs.getSelectedIndex() == 1) refreshTimetable();
        });
        
     // ====================================
     // Fix Refresh Timetable Button Width
     // ====================================

     viewRefreshButton.setPreferredSize(
         new Dimension(210, 45)
     );

     viewRefreshButton.setMinimumSize(
         new Dimension(210, 45)
     );

     viewTop.revalidate();
     viewTop.repaint();
    }

    public void refreshOptions() {
        String oldCourse = (String) courseBox.getSelectedItem();
        String oldLecturer = (String) lecturerBox.getSelectedItem();
        String oldGroup = (String) groupBox.getSelectedItem();
        courseBox.removeAllItems();
        lecturerBox.removeAllItems();
        groupBox.removeAllItems();
        for (Course c : data.getCourses()) courseBox.addItem(c.getCourseID());
        for (Lecturer l : data.getLecturers()) lecturerBox.addItem(l.getLecturerID());
        for (StudentGroup g : data.getStudentGroups()) groupBox.addItem(g.getStudentGroupID());
        if (oldCourse != null) courseBox.setSelectedItem(oldCourse);
        if (oldLecturer != null) lecturerBox.setSelectedItem(oldLecturer);
        if (oldGroup != null) groupBox.setSelectedItem(oldGroup);
    }

    private void addRequest() {
        String course = (String) courseBox.getSelectedItem();
        String lecturer = (String) lecturerBox.getSelectedItem();
        String group = (String) groupBox.getSelectedItem();
        if (course == null || lecturer == null || group == null) {
            showMessage("Register Course, Lecturer and Student Group first, then Refresh Options.");
            return;
        }
        requestModel.addRow(new Object[]{course, lecturer, group});
    }

    private Course findCourse(String id) {
        for (Course c : data.getCourses()) if (c.getCourseID().equalsIgnoreCase(id)) return c;
        return null;
    }
    private StudentGroup findGroup(String id) {
        for (StudentGroup g : data.getStudentGroups()) if (g.getStudentGroupID().equalsIgnoreCase(id)) return g;
        return null;
    }
    private boolean lecturerExists(String id) {
        for (Lecturer l : data.getLecturers()) if (l.getLecturerID().equalsIgnoreCase(id)) return true;
        return false;
    }
    private String nextSessionID() {
        int number = 1;
        while (true) {
            String candidate = String.format("S%03d", number);
            boolean exists = false;
            for (ClassSession session : data.getClassSessions()) {
                if (session.getSessionID().equalsIgnoreCase(candidate)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) return candidate;
            number++;
        }
    }

    private boolean conflicts(String day, LocalTime start, LocalTime end,
                              String lecturer, String group, String room) {
        for (ClassSession session : data.getClassSessions()) {
            if (!session.getDay().equalsIgnoreCase(day)) continue;
            LocalTime oldStart;
            LocalTime oldEnd;
            try {
                oldStart = LocalTime.parse(session.getStartTime());
                oldEnd = LocalTime.parse(session.getEndTime());
            } catch (DateTimeParseException ex) {
                throw new IllegalStateException("Session " + session.getSessionID()
                        + " has an invalid time. Please correct it first.");
            }
            boolean overlap = start.isBefore(oldEnd) && end.isAfter(oldStart);
            if (overlap && (session.getLecturerID().equalsIgnoreCase(lecturer)
                    || session.getStudentGroupID().equalsIgnoreCase(group)
                    || session.getRoomID().equalsIgnoreCase(room))) return true;
        }
        return false;
    }

    private void generateTimetable() {
        if (requestModel.getRowCount() == 0) {
            showMessage("Please add at least one scheduling request.");
            return;
        }
        if (data.getClassrooms().isEmpty() && data.getLaboratories().isEmpty()) {
            showMessage("No Classroom or Laboratory registered.");
            return;
        }
        int success = 0;
        int failed = 0;
        StringBuilder details = new StringBuilder();
        int total = requestModel.getRowCount();
        // Each successful request is immediately saved, so later requests
        // are checked against both existing and newly generated sessions.
        for (int i = 0; i < total; i++) {
            String courseID = requestModel.getValueAt(i, 0).toString();
            String lecturerID = requestModel.getValueAt(i, 1).toString();
            String groupID = requestModel.getValueAt(i, 2).toString();
            Course course = findCourse(courseID);
            StudentGroup group = findGroup(groupID);
            if (course == null || group == null || !lecturerExists(lecturerID)) {
                failed++;
                details.append("FAILED: ").append(courseID)
                        .append(" - Course, Lecturer or Group no longer exists.\n");
                continue;
            }
            List<String> rooms = new ArrayList<>();
            if (course.getCourseType().equalsIgnoreCase("Lecture")) {
                for (Classroom room : data.getClassrooms()) {
                    if (room.getClassroomCapacity() >= group.getStudentGroupSize())
                        rooms.add(room.getClassroomID());
                }
            } else if (course.getCourseType().equalsIgnoreCase("Practical")) {
                for (Laboratory room : data.getLaboratories()) {
                    if (room.getLabCapacity() >= group.getStudentGroupSize())
                        rooms.add(room.getLabID());
                }
            }
            if (rooms.isEmpty()) {
                failed++;
                details.append("FAILED: ").append(courseID)
                        .append(" - No suitable room (type/capacity).\n");
                continue;
            }
            boolean scheduled = false;
            try {
                for (String day : DAYS) {
                    if (scheduled) break;
                    for (LocalTime start : SLOTS) {
                        if (scheduled) break;
                        LocalTime end = start.plusHours(2);
                        for (String room : rooms) {
                            if (conflicts(day, start, end, lecturerID, groupID, room)) continue;
                            String sessionID = nextSessionID();
                            data.getClassSessions().add(new ClassSession(
                                    sessionID, courseID, lecturerID, groupID,
                                    day, start.toString(), end.toString(), room));
                            success++;
                            scheduled = true;
                            details.append("SUCCESS: ").append(sessionID).append(" | ")
                                    .append(courseID).append(" | ").append(day).append(" ")
                                    .append(start).append("-").append(end)
                                    .append(" | ").append(room).append("\n");
                            break;
                        }
                    }
                }
            } catch (IllegalStateException ex) {
                // Stop processing rather than silently ignore invalid existing sessions.
                summaryArea.setText("Generation stopped: " + ex.getMessage()
                        + "\nSuccessfully generated before stopping: " + success);
                refreshTimetable();
                showMessage(ex.getMessage());
                return;
            }
            if (!scheduled) {
                failed++;
                details.append("FAILED: ").append(courseID)
                        .append(" - No available day, time or room.\n");
            }
        }
        summaryArea.setText("Total Requests: " + total + "\nSuccessfully Generated: "
                + success + "\nFailed: " + failed + "\n\n" + details);
        summaryArea.setCaretPosition(0);
        requestModel.setRowCount(0); // Prevent accidental regeneration of the same batch.
        refreshTimetable();
        showMessage("Generation complete. Success: " + success + ", Failed: " + failed);
    }

    public void refreshTimetable() {
        timetableModel.setRowCount(0);
        List<ClassSession> sorted = new ArrayList<>(data.getClassSessions());
        sorted.sort(Comparator.comparingInt((ClassSession s) -> dayOrder(s.getDay()))
                .thenComparing(s -> parseTimeForSorting(s.getStartTime()))
                .thenComparing(ClassSession::getSessionID));
        for (ClassSession s : sorted) {
            timetableModel.addRow(new Object[]{s.getSessionID(), s.getDay(),
                    s.getStartTime(), s.getEndTime(), s.getCourseID(),
                    s.getLecturerID(), s.getStudentGroupID(), s.getRoomID()});
        }
    }

    private int dayOrder(String day) {
        for (int i = 0; i < DAYS.length; i++) {
            if (DAYS[i].equalsIgnoreCase(day)) return i;
        }
        return DAYS.length; // e.g. manually created Saturday sessions
    }
    private LocalTime parseTimeForSorting(String time) {
        try { return LocalTime.parse(time); }
        catch (DateTimeParseException ex) { return LocalTime.MAX; }
    }
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
