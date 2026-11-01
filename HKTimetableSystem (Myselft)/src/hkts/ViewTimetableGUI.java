package hkts;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

public class ViewTimetableGUI extends JFrame {

    private HKTSData data;
    private JTable timetableTable;
    private DefaultTableModel tableModel;

    // Constructor
    public ViewTimetableGUI(HKTSData data, String role) {
    	
        this.data = data;

        setTitle("HKTS - " + role + " Timetable");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main Panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Title
        JLabel titleLabel = new JLabel("HK TIMETABLE SYSTEM - VIEW TIMETABLE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        JLabel roleLabel = new JLabel("Logged in as: " + role);

        JPanel titlePanel = new JPanel(new GridLayout(2, 1, 5, 5));
        titlePanel.add(titleLabel);
        titlePanel.add(roleLabel);
        mainPanel.add(titlePanel, BorderLayout.NORTH);

        // Table Columns
        String[] columns = {
            "Session ID", "Course ID", "Lecturer ID", "Student Group ID",
            "Day", "Start Time", "End Time", "Room ID"
        };

        // Table Model
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // JTable
        timetableTable = new JTable(tableModel);
        timetableTable.setRowHeight(25);
        timetableTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane scrollPane = new JScrollPane(timetableTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Buttons
        JButton refreshButton = new JButton("Refresh Timetable");
        JButton logoutButton = new JButton("Logout");
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(refreshButton);
        buttonPanel.add(logoutButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);

        // Refresh Button
        refreshButton.addActionListener(e -> {
            refreshTimetable();
        });

        // Logout Button
        logoutButton.addActionListener(e -> {
            new LoginGUI(data);
            dispose();
        });

        // Load Timetable
        refreshTimetable();
        setVisible(true);
    }

    // Refresh Timetable
    private void refreshTimetable() {
    	
        // Clear Table
        tableModel.setRowCount(0);

        // Copy Sessions
        ArrayList<ClassSession> sessions = new ArrayList<>(data.getClassSessions());

        // Days Order
        String[] days = {
            "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        // Sort by Day and Start Time
        sessions.sort(Comparator.comparingInt((ClassSession session) -> {
        	
            for (int i = 0; i < days.length; i++) {
            	
                if (days[i].equalsIgnoreCase(session.getDay())) {
                	
                    return i;
                }
            }
            
            return days.length;
            
        }).thenComparing(ClassSession::getStartTime));

        // Display Sessions
        for (ClassSession session : sessions) {
        	
            tableModel.addRow(new Object[] {
            		
                session.getSessionID(), session.getCourseID(), session.getLecturerID(),
                session.getStudentGroupID(), session.getDay(), session.getStartTime(),
                session.getEndTime(), session.getRoomID()
            });
        }
    }
}
