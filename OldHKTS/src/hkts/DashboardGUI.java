package hkts;

import javax.swing.*;
import java.awt.*;

public class DashboardGUI extends JFrame {
	
	private HKTSData data;

    public DashboardGUI() {
    	
    	data = new HKTSData();

        // Window Title
        setTitle("HK Timetable System");

        // Window Size
        setSize(1100, 700);

        // Window Position
        setLocationRelativeTo(null);

        // Close Application
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main Layout
        setLayout(new BorderLayout());

        
        // ====================================
        // Sidebar Panel
        // ====================================

        JPanel sidebar = new JPanel();

        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        sidebar.setPreferredSize(new Dimension(200, 700));

        sidebar.setBackground(new Color(240, 240, 240));

        // Navigation Buttons
        JButton dashboardButton = new JButton("Dashboard");
        JButton lecturerButton = new JButton("Lecturer");
        JButton courseButton = new JButton("Course");
        JButton groupButton = new JButton("Student Group");
        JButton classroomButton = new JButton("Classroom");
        JButton laboratoryButton = new JButton("Laboratory");
        JButton sessionButton = new JButton("Class Session");
        JButton timetableButton = new JButton("Timetable");

     // Set Button Size
        Dimension buttonSize = new Dimension(180, 45);

        // Store Buttons in Array
        JButton[] buttons = {
            dashboardButton,
            lecturerButton,
            courseButton,
            groupButton,
            classroomButton,
            laboratoryButton,
            sessionButton,
            timetableButton
        };

        // Apply Same Size to All Buttons
        for (JButton button : buttons) {

            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setMinimumSize(buttonSize);

            button.setAlignmentX(Component.CENTER_ALIGNMENT);
        }
        
     // Add Buttons to Sidebar with Spacing

        sidebar.add(Box.createVerticalStrut(20));

        for (JButton button : buttons) {

            sidebar.add(button);

            sidebar.add(Box.createVerticalStrut(10));
        }

        // ====================================
        // Content Panel
        // ====================================

     // CardLayout for Switching Pages
        CardLayout cardLayout = new CardLayout();

        JPanel contentPanel = new JPanel(cardLayout);

     // Dashboard Page
        JPanel dashboardPage = new JPanel(new BorderLayout());

        JLabel dashboardTitle = new JLabel(
            "Welcome to HK Timetable System",
            SwingConstants.CENTER
        );

        dashboardTitle.setFont(new Font("Arial", Font.BOLD, 28));

        dashboardPage.add(dashboardTitle, BorderLayout.CENTER);

        // Lecturer Page
        LecturerPanel lecturerPage = new LecturerPanel(data);

        // Course Page
        CoursePanel coursePage = new CoursePanel(data);

        // Student Group Page
        StudentGroupPanel groupPage = new StudentGroupPanel(data);
        
        // Classroom page
        ClassroomPanel classroomPage = new ClassroomPanel(data);

        // Laboratory Page
        LaboratoryPanel laboratoryPage = new LaboratoryPanel(data);

        // Class Session Page
        ClassSessionPanel sessionPage = new ClassSessionPanel(data);

        // Timetable Page
        TimetablePanel timetablePage = new TimetablePanel(data);

        // Add Pages to CardLayout
        contentPanel.add(dashboardPage, "Dashboard");
        contentPanel.add(lecturerPage, "Lecturer");
        contentPanel.add(coursePage, "Course");
        contentPanel.add(groupPage, "StudentGroup");
        contentPanel.add(classroomPage, "Classroom");
        contentPanel.add(laboratoryPage, "Laboratory");
        contentPanel.add(sessionPage, "ClassSession");
        contentPanel.add(timetablePage, "Timetable");

     // Sidebar Navigation

        dashboardButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "Dashboard");
        });

        lecturerButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "Lecturer");
        });

        courseButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "Course");
        });

        groupButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "StudentGroup");
        });

        classroomButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "Classroom");
        });

        laboratoryButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "Laboratory");
        });

        sessionButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "ClassSession");
        });

        timetableButton.addActionListener(e -> {
            cardLayout.show(contentPanel, "Timetable");
        });
        // ====================================
        // Add Panels to Window
        // ====================================

        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        // Show Window
        setVisible(true);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new DashboardGUI();

        });
    }
}
