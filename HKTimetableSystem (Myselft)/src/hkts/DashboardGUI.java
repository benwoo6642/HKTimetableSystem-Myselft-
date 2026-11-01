package hkts;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class DashboardGUI extends JFrame {
	
    // Attributes
    private HKTSData data;
    private CardLayout cardLayout;
    private JPanel contentPanel;
    private JButton selectedButton;

    // Theme Colors
    private final Color BACKGROUND = new Color(255, 240, 245);
    private final Color PRIMARY = new Color(235, 105, 150);
    private final Color PRIMARY_DARK = new Color(210, 75, 125);
    private final Color LIGHT_PINK = new Color(255, 225, 235);
    private final Color TEXT = new Color(70, 70, 70);
    private final Color SIDEBAR_BG = Color.WHITE;

    // Constructor
    public DashboardGUI(HKTSData data) {
        this.data = data;

        // Window Settings
        setTitle("HK Timetable System - Dashboard");
        setSize(1150, 720);
        setMinimumSize(new Dimension(1000, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Main Background
        JPanel mainPanel = new JPanel( new BorderLayout() );
        mainPanel.setBackground(BACKGROUND);

        // Top Header
        JPanel header = new JPanel( new BorderLayout() );
        header.setBackground(Color.WHITE);
        header.setPreferredSize( new Dimension(1150, 75) );
        header.setBorder( new EmptyBorder(15, 30, 15, 30) );
        
        JLabel systemTitle = new JLabel( "HK TIMETABLE SYSTEM" );
        systemTitle.setFont( new Font("Arial", Font.BOLD, 24) );
        systemTitle.setForeground(PRIMARY_DARK);
        
        JLabel roleLabel = new JLabel( "Programme Leader" );
        roleLabel.setFont( new Font("Arial", Font.BOLD, 14) );
        roleLabel.setForeground(TEXT);
        header.add( systemTitle, BorderLayout.WEST );
        header.add( roleLabel, BorderLayout.EAST );

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout( new BoxLayout( sidebar, BoxLayout.Y_AXIS ) );
        sidebar.setBackground(SIDEBAR_BG);
        sidebar.setPreferredSize( new Dimension(220, 645) );
        sidebar.setBorder( new EmptyBorder(25, 15, 20, 15) );

        // Sidebar Title
        JLabel menuTitle = new JLabel( "MENU" );
        menuTitle.setFont( new Font("Arial", Font.BOLD, 13) );
        menuTitle.setForeground(Color.GRAY);
        menuTitle.setAlignmentX( Component.CENTER_ALIGNMENT );
        
        sidebar.add(menuTitle);
        sidebar.add( Box.createVerticalStrut(20) );

        // Navigation Buttons
        JButton dashboardButton = createSidebarButton("Dashboard");
        
        JButton lecturerButton = createSidebarButton("Lecturer");
        
        JButton courseButton = createSidebarButton("Course");
        
        JButton groupButton = createSidebarButton("Student Group");
        
        JButton classroomButton = createSidebarButton("Classroom");
        
        JButton laboratoryButton = createSidebarButton("Laboratory");
        
        JButton sessionButton = createSidebarButton("Class Session");
        
        JButton timetableButton = createSidebarButton("Timetable");
        
        JButton[] buttons = {dashboardButton, lecturerButton, courseButton, groupButton, classroomButton, laboratoryButton, sessionButton, timetableButton};

        for (JButton button : buttons) {
        	
            sidebar.add(button);
            sidebar.add( Box.createVerticalStrut(10) );
        }

        // Logout Button
        sidebar.add( Box.createVerticalGlue() );
        JButton logoutButton = createLogoutButton();
        sidebar.add(logoutButton);

        // CardLayout
        cardLayout = new CardLayout();
        contentPanel = new JPanel( cardLayout );
        contentPanel.setBackground(BACKGROUND);

        // Dashboard Page
        JPanel dashboardPage = createDashboardPage();

        // Existing Management Panels
        LecturerPanel lecturerPage = new LecturerPanel(data);
        
        CoursePanel coursePage = new CoursePanel(data);
        
        StudentGroupPanel groupPage = new StudentGroupPanel(data);
        
        ClassroomPanel classroomPage = new ClassroomPanel(data);
        
        LaboratoryPanel laboratoryPage = new LaboratoryPanel(data);
        
        ClassSessionPanel sessionPage = new ClassSessionPanel(data);
        
        TimetablePanel timetablePage = new TimetablePanel(data);

        // Apply HKTS Theme to Management Panels
        HKTSTheme.applyTheme(lecturerPage);
       
        HKTSTheme.applyTheme(coursePage);
        
        HKTSTheme.applyTheme(groupPage);
        
        HKTSTheme.applyTheme(classroomPage);
        
        HKTSTheme.applyTheme(laboratoryPage);
        
        HKTSTheme.applyTheme(sessionPage);
        
        HKTSTheme.applyTheme(timetablePage);

        // Add Pages
        contentPanel.add( dashboardPage, "Dashboard" );
       
        contentPanel.add( lecturerPage, "Lecturer" );
        
        contentPanel.add( coursePage, "Course" );
        
        contentPanel.add( groupPage, "StudentGroup" );
        
        contentPanel.add( classroomPage, "Classroom" );
        
        contentPanel.add( laboratoryPage, "Laboratory" );
        
        contentPanel.add(sessionPage, "ClassSession");
        
        contentPanel.add( timetablePage, "Timetable" );

        // Navigation Actions
        dashboardButton.addActionListener(e -> {showPage( "Dashboard", dashboardButton );});
        
        lecturerButton.addActionListener(e -> {showPage( "Lecturer", lecturerButton ); });
        
        courseButton.addActionListener(e -> {showPage( "Course", courseButton );});
        
        groupButton.addActionListener(e -> {showPage( "StudentGroup", groupButton );});
        
        classroomButton.addActionListener(e -> {showPage( "Classroom", classroomButton );});
        
        laboratoryButton.addActionListener(e -> {showPage( "Laboratory", laboratoryButton );});
        
        sessionButton.addActionListener(e -> {showPage( "ClassSession", sessionButton );});
        
        timetableButton.addActionListener(e -> {showPage( "Timetable", timetableButton );});

        // Logout Action
        logoutButton.addActionListener(e -> {
        	
            int choice = JOptionPane.showConfirmDialog( this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION );
            if (choice == JOptionPane.YES_OPTION) {
                new LoginGUI(data);
                dispose();
            }
        });

        // Default Selected Page
        showPage( "Dashboard", dashboardButton );

        // Main Layout
        mainPanel.add( header, BorderLayout.NORTH );
        mainPanel.add( sidebar, BorderLayout.WEST );
        mainPanel.add( contentPanel, BorderLayout.CENTER );
        
        add(mainPanel);
        setVisible(true);
    }

    // Create Sidebar Button
    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setFont( new Font("Arial", Font.BOLD, 14) );
        button.setForeground(TEXT);
        button.setBackground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor( new Cursor(Cursor.HAND_CURSOR) );
        button.setHorizontalAlignment( SwingConstants.LEFT );
        button.setBorder( new EmptyBorder(12, 20, 12, 10) );
        Dimension size = new Dimension(190, 45);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setMinimumSize(size);
        button.setAlignmentX( Component.CENTER_ALIGNMENT );

        // Hover Effect
        button.addMouseListener( new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (button != selectedButton) {
                    button.setBackground( LIGHT_PINK );
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (button != selectedButton) {
                    button.setBackground( Color.WHITE );
                }
            }
        }
    );
        return button;
    }

    // Selected Navigation Button
    private void showPage( String pageName, JButton button) {
    	
        if (selectedButton != null) {
            selectedButton.setBackground( Color.WHITE );
            selectedButton.setForeground( TEXT );
        }

        selectedButton = button;
        selectedButton.setBackground( LIGHT_PINK );
        selectedButton.setForeground( PRIMARY_DARK );
        cardLayout.show( contentPanel, pageName );
    }

    // Create Logout Button
    private JButton createLogoutButton() {
    	
        JButton button = new JButton("LOGOUT") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
                if (getModel().isRollover()) {
                    g2.setColor(PRIMARY_DARK);
                } else {
                    g2.setColor(PRIMARY);
                }

                g2.fillRoundRect( 0, 0, getWidth(), getHeight(), 18, 18 );
                g2.dispose();
                super.paintComponent(g);
            }
        };

        button.setFont( new Font("Arial", Font.BOLD, 14) );
        button.setForeground(Color.WHITE);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setOpaque(false);
        button.setCursor( new Cursor(Cursor.HAND_CURSOR) );
        Dimension size = new Dimension(190, 45);
        button.setPreferredSize(size);
        button.setMaximumSize(size);
        button.setMinimumSize(size);
        button.setAlignmentX( Component.CENTER_ALIGNMENT );
        return button;
    }

    // Create Dashboard Page
    private JPanel createDashboardPage() {
    	
        JPanel dashboardPage = new JPanel( new BorderLayout() );
        dashboardPage.setBackground(BACKGROUND);
        dashboardPage.setBorder( new EmptyBorder(35, 40, 35, 40) );

        // Welcome Section
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout( new BoxLayout( welcomePanel, BoxLayout.Y_AXIS ) );
        welcomePanel.setOpaque(false);
        
        JLabel welcomeTitle = new JLabel( "Welcome Back, Programme Leader!" );
        welcomeTitle.setFont( new Font("Arial", Font.BOLD, 28) );
        welcomeTitle.setForeground(TEXT);
        welcomeTitle.setAlignmentX( Component.LEFT_ALIGNMENT );
        
        JLabel welcomeSubtitle = new JLabel( "Academic Scheduling Management" );
        welcomeSubtitle.setFont( new Font("Arial", Font.PLAIN, 15) );
        welcomeSubtitle.setForeground( Color.GRAY );
        welcomeSubtitle.setAlignmentX( Component.LEFT_ALIGNMENT );
        welcomePanel.add(welcomeTitle);
        welcomePanel.add( Box.createVerticalStrut(8) );
        welcomePanel.add(welcomeSubtitle);

        // Welcome Card
        JPanel welcomeCard = new JPanel( new GridBagLayout() ) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
                g2.setColor(Color.WHITE);
                g2.fillRoundRect( 0, 0, getWidth(), getHeight(), 25, 25 );
                g2.dispose();
            }
        };

        welcomeCard.setOpaque(false);
        welcomeCard.setPreferredSize( new Dimension(600, 300) );

        // Card Content
        JPanel cardContent = new JPanel();
        cardContent.setOpaque(false);
        cardContent.setLayout( new BoxLayout( cardContent, BoxLayout.Y_AXIS ) );
        
        JLabel cardTitle = new JLabel( "HK TIMETABLE SYSTEM" );
        cardTitle.setFont( new Font("Arial", Font.BOLD, 25) );
        cardTitle.setForeground( PRIMARY_DARK );
        cardTitle.setAlignmentX( Component.CENTER_ALIGNMENT );
       
        JLabel cardText = new JLabel( "Manage your academic timetable" );
        cardText.setFont( new Font("Arial", Font.PLAIN, 16) );
        cardText.setForeground(TEXT);
        cardText.setAlignmentX( Component.CENTER_ALIGNMENT );
      
        JLabel cardText2 = new JLabel( "efficiently and easily." );
        cardText2.setFont( new Font("Arial", Font.PLAIN, 16) );
        cardText2.setForeground(TEXT);
        cardText2.setAlignmentX( Component.CENTER_ALIGNMENT );
        cardContent.add(cardTitle);
        cardContent.add( Box.createVerticalStrut(20) );
        cardContent.add(cardText);
        cardContent.add( Box.createVerticalStrut(5) );
        cardContent.add(cardText2);
        welcomeCard.add(cardContent);

        // Add Dashboard Components
        dashboardPage.add( welcomePanel, BorderLayout.NORTH );
        JPanel cardWrapper = new JPanel( new GridBagLayout() );
        cardWrapper.setOpaque(false);
        cardWrapper.add(welcomeCard);
        dashboardPage.add( cardWrapper, BorderLayout.CENTER );
        return dashboardPage;
    }

    // Main Method
    public static void main(String[] args) {
    	
        SwingUtilities.invokeLater(() -> {
            HKTSData data = new HKTSData();
            new LoginGUI(data);
        });
    }
}
