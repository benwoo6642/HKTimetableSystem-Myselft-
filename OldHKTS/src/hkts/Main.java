package hkts;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class Main {

	public static void main (String[]args) {
		// for input
		Scanner input = new Scanner(System.in);
		// ArrayList
		ArrayList<Lecturer> lecturers = new ArrayList<>(); // ArrayList for lecturer (Lecturer是人， lecturer是人的资料， lecturers是list)
		ArrayList<Course> courses = new ArrayList<>(); // ArrayList for Course
		ArrayList<StudentGroup> studentGroups = new ArrayList<>(); // ArrayList for Student Group
		ArrayList<Classroom> classrooms = new ArrayList<>(); //ArrayList for Classroom
		ArrayList<Laboratory> laboratories = new ArrayList<>(); // ArrayList for Laboratory
		ArrayList<ClassSession> classSessions = new ArrayList<>(); // ArrayList for Class Session
		
		boolean running = true; //Lecturer.java
		
		boolean loginSuccess = loginProgrammeLeader(input); // Lecturer.java
		// if statement for ProgrammeLeader Login (in Lecturer.java)
		if (!loginSuccess) { 
			
			System.out.println("Invalid Username or Password......");
			
			input.close();
			
			return;
		} // End for if statement
		
		System.out.println("Login Successful......");
		
// Main Menu part star
		while (running ) { 
					
			System.out.println("\n========== HKTS MAIN MENU ==========");
			System.out.println("1. Register Lecturer");
			System.out.println("2. View All Lecturer");
			System.out.println("3. Search Lecturer");
			System.out.println("4. Update Lecturer");
			System.out.println("5. Delete Lecturer");
			System.out.println("6. Register Course");
			System.out.println("7. View All Course");
			System.out.println("8. Search Course");
			System.out.println("9. Update Course");
			System.out.println("10. Delete Course");
			System.out.println("11. Register Student Group");
			System.out.println("12. View All Student Group");
			System.out.println("13. Search Student Group");
			System.out.println("14. Update Student Group");
			System.out.println("15. Delete Student Group");
			System.out.println("16. Register Classroom");
			System.out.println("17. View All Classroom");
			System.out.println("18. Search Classroom");
			System.out.println("19. Update Classroom");
			System.out.println("20. Delete Classroom");
			System.out.println("21. Register Laboratory");
			System.out.println("22. View All Laboratory");
			System.out.println("23. Search Laboratory");
			System.out.println("24. Update Laboratory");
			System.out.println("25. Delete Laboratory");
			System.out.println("26. Register Class Session");
			System.out.println("27. View All Class Session");
			System.out.println("28. Search Class Session");
			System.out.println("29. Update Class Session");
			System.out.println("30. Delete Class Session");
			System.out.println("31. Generate Timetable");
			System.out.println("32. View Timetable");
			System.out.println("33. Exit");
			System.out.println("====================================");
					
			System.out.print("Enter the menu choice: ");
			String menuChoice = input.nextLine().trim();
			// switch for main menu
			switch (menuChoice) {  
					
				case "1": // For register lecturer
					
					registerLecturer(lecturers, input);
					break;
				
				case "2": // Display All Lecturer
					
					viewAllLecturer(lecturers);
					break;
				
				case "3": // Search Lecturer
					
					searchLecturer(lecturers, input);
					break;
				
				case "4": // Update Lecturer
					
					updateLecturer(lecturers, input);
					break;
					
				case "5": // Delete Lecturer
					
					deleteLecturer(lecturers, input);
					break;
					
				case "6": // Register Course
					
					registerCourse(courses, input);
					break;
				
				case "7": // View All Course
					
					viewAllCourse(courses);
					break;
					
				case "8": // Search Course
					
					searchCourse(courses, input);
					break;
					
				case "9": // Update Course
					
					updateCourse(courses, input);
					break;
					
				case "10": // Delete Course
					
					deleteCourse(courses, input);
					break;
					
				case "11": // Register Student Group
					
					registerStudentGroup(studentGroups, input);
					break;
					
				case "12": // View All Student Group
					
					viewAllStudentGroup(studentGroups);
					break;
					
				case "13": // Search Student Group
					
					searchStudentGroup(studentGroups, input);
					break;
								
				case "14": // Update Student Group
					
					updateStudentGroup(studentGroups, input);
					break;
					
				case "15": // Update Student Group
					
					deleteStudentGroup(studentGroups, input);
					break;	
					
				case "16": // Register Classroom
					
					registerClassroom(classrooms, input);
					break;
					
				case "17": // View All Classroom
					
					viewAllClassroom(classrooms);
					break;		
					
				case "18": // Search Classroom
					
					searchClassroom(classrooms, input);
					break;
					
				case "19": // Update Classroom
					
					updateClassroom(classrooms, input);
					break;
					
				case "20": // Delete Classroom
					
					deleteClassroom(classrooms, input);
					break;
					
				case "21": // Register Laboratory
					
					registerLaboratory(laboratories, input);
					break;
					
				case "22": // View All Laboratory
					
					viewAllLaboratory(laboratories);
					break;
					
				case "23": // Search Laboratory
					
					searchLaboratory(laboratories, input);
					break;
					
				case "24": // Update Laboratory
					
					updateLaboratory(laboratories, input);
					break;
					
				case "25": // Delete Laboratory
					
					deleteLaboratory(laboratories, input);
					break;
					
				case "26": // Register Class Session
					
					registerClassSession(classSessions, courses, lecturers, studentGroups, classrooms, laboratories, input);
					break;
					
				case "27": // View All Class Session
					
					viewAllClassSession(classSessions);
					break;
					
				case "28": // Search Class Session
					
					searchClassSession(classSessions, input);
					break;
					
				case "29": // Update Class Session
					
					updateClassSession(classSessions, courses, lecturers, studentGroups, classrooms, laboratories, input);
					break;
					
				case "30": // Delete Class Session
					
					deleteClassSession(classSessions, input);
					break;
					
				case "31": // Generate Timetable

				    generateTimetable(classSessions, courses, lecturers, studentGroups, classrooms, laboratories, input);

				    break;

				case "32": // View Generated Timetable

				    viewGeneratedTimetable(classSessions);
				    break;

				case "33": // Exit System

				    System.out.println("Exit System");
				    running = false;
				    break;
					
				default : // For the wrong choice format of the user
					
						System.out.println("Invalid choice");
				}
			}// End main menu loop
			
			input.close();
		}
// Main Menu part End
	
// Lecturer part Start	
	// View All Lecturer
	public static void viewAllLecturer(ArrayList<Lecturer>lecturers) { 
		
		if (lecturers.isEmpty()) { // for check empty
			
			System.out.println("No lecturers registered yet.");
		}
		
		for (Lecturer lecturer: lecturers) { //cDisplay all Lecturer
			
			System.out.println("\nLecturer ID: " + lecturer.getLecturerID());
			System.out.println("Lecturer Name: " + lecturer.getLecturerName());
			System.out.println("Lecturer Email: " + lecturer.getLecturerEmail());
			System.out.println("Lecturer Qualification: " + lecturer.getLecturerQualification());
			System.out.println("---------------------------");
		}

		System.out.println("Total Lecturer: " + lecturers.size());
	} // End viewAllLecturers 
	
 	// Search Lecturer
	public static void searchLecturer(ArrayList<Lecturer> lecturers, Scanner input) { 
		
		System.out.print("\nEnter Lecturer ID to search: ");
		String searchID = input.nextLine().trim();

		boolean found = false; //Lecturer not found 没找到老师

		for (Lecturer lecturer: lecturers) { // for-each loop
			
			if (lecturer.getLecturerID().equalsIgnoreCase(searchID)) {
				
				found = true;
				
				System.out.println("Lecturer ID: " + lecturer.getLecturerID());
				System.out.println("Lecturer Name: " + lecturer.getLecturerName());
				System.out.println("Lecturer Email: " + lecturer.getLecturerEmail());
				System.out.println("Lecturer Qualification: " + lecturer.getLecturerQualification());
				System.out.println("====================================");
				
				break;
			}
		}
		
				if (!found) {
					System.out.println("Lecturer not found......");
				}
	} // End of searchLecturer 
	
	// Register Lecturer
	public static void registerLecturer(ArrayList<Lecturer> lecturers, Scanner input) { 
		
		String choice = "y";
		
		while (choice.equalsIgnoreCase("Y")) {	// While loop to add other lecturer 忽略大小写 'Y' or 'y'			
			
			String id = ""; // can continue use the new lecturer
			boolean validID = false;
			
			while (!validID) { //while loop for duplicate validation
			
				System.out.print("Enter Lecturer ID: "); // Lecturer ID
				id = input.nextLine().trim(); // .trim() for 空格太多躲过 duplicate checking
					
				if (id.isBlank()) { // if statement to check the input is blank or not (Lecturer ID)
					System.out.print("Can not be empty......");
					continue;
				}
						
				boolean duplicate = false; // no have same ID (假设)
							
				for (Lecturer lecturer: lecturers) { // For-each to check duplicate
					
					if (lecturer.getLecturerID().equalsIgnoreCase(id)) {
						
						duplicate = true;
						break;
					}
				}
				
				if (duplicate ) {
					
					System.out.println("Lecturer ID already exists.");
				} 
				else {
					
					validID = true;
				}
			} // End while loop duplicate validate

				System.out.print("Enter Lecturer Name: "); //Lecturer Name
				String name = input.nextLine().trim();
							
				while (name.isBlank()) { // while loop to check the input is blank or not (Lecturer Name)
					
					System.out.print("Can not be empty......");
								
					System.out.print("\nPlease enter Lecturer Name again: ");
					name = input.nextLine().trim();
				}// End the while loop blank checking
							
				System.out.print("Enter Lecturer Email: "); //Lecturer Email
				String email = input.nextLine().trim();

				while (email.isBlank() || !email.contains("@") || !email.contains(".") ) { // while loop Email format checking
						
					System.out.print("Error......Email cannot be blank and must contain '@' and '.'");
										
					System.out.print("\nPlease enter Lecturer Email again: ");
					email = input.nextLine().trim();
				}// End the while loop email validation
					
				System.out.print("Enter Lecturer Qualification: "); //Lecturer Qualification
				String qualification = input.nextLine().trim();

				while (qualification.isBlank()) { // while loop to check the input is blank or not (Lecturer Qualification)
					
					System.out.print("Can not be empty......");
									
					System.out.print("\nPlease enter Lecturer Qualification again: ");
					qualification = input.nextLine().trim();
				}// End the while loop Qualification validation
								
				Lecturer lecturer = new Lecturer (id, name, email, qualification); // Create lecturer object
						
				lecturers.add(lecturer); // Add Lecturer Object to ArrayList
				System.out.println("Lecturer Registered Successfully......");
							
				System.out.print("Register another lecturer ? (Y/N): "); // Ask user to register another Lecturer
				choice = input.nextLine().trim();
			} // Register loop end
	} // End of regsterLecturer
	
	// Update Lecturer
	public static void updateLecturer(ArrayList<Lecturer> lecturers, Scanner input) { 
		
		System.out.print("Enter Lecturer ID to update: ");
		String updateID = input.nextLine().trim();
		
		boolean found = false;
		
		for (Lecturer lecturer: lecturers) {
			
			if (lecturer.getLecturerID().equalsIgnoreCase(updateID)) {
				
				found = true;
				
				System.out.println("Lecturer Found......");
				
				System.out.println("\n========== UPDATE LECTURER ==========");
				System.out.println("1. Update Lecturer Name");
				System.out.println("2. Update Lecturer Email");
				System.out.println("3. Update Lecturer Qualification");
				System.out.println("4. Back");
				System.out.println("=====================================");
				
				System.out.print("Enter your choice: ");
				String updateChoice = input.nextLine().trim();
				
				switch (updateChoice) {
				
				case "1": // New Lecturer Name
					
					System.out.println("Enter New Lecturer Name: ");
					String newName = input.nextLine().trim();
					
					while (newName.isBlank()) { // for check empty
						
						System.out.println("Lecturer Name connot be empty.");
						
						System.out.print("Please enter Lecturer Name again: ");
						newName = input.nextLine().trim();
					} // End while loop
					
					lecturer.setLecturerName(newName); // object
					
					System.out.println("Lecturer Name update successfully......");
					
					break;
					
				case "2": // New Lecturer Email
					
					System.out.println("Enter New Lecturer Email");
					String newEmail = input.nextLine().trim();
					
					while (newEmail.isBlank() || !newEmail.contains("@") || !newEmail.contains(".")) {
						
						System.out.println("Update cannot be blank and must be have '@' and '.'.");
						
						System.out.print("Enter New Lecturer Email again: ");
						newEmail = input.nextLine().trim();
					}
					
					lecturer.setLecturerEmail(newEmail);
					
					System.out.println("Lecturer Email updated successfully......");
					
					break;
					
				case "3": // New Lecturer Qualification
					
					System.out.println("Enter new Lecturer Qualification: ");
					String newQualification = input.nextLine().trim();
					
					while (newQualification.isBlank()) {
						
						System.out.println("The Qualification connot be blank.");
						
						System.out.print("Enter New Qualification again: ");
						newQualification = input.nextLine().trim();
					}
					
					lecturer.setLecturerQualification(newQualification);
					
					System.out.println("Lecturer Qualification updated successfully......");
					
					break;
					
				case "4":
					
					System.out.println("Back to Main Menu");
					break;
					
				default :
					
					System.out.println("Invalid choice");	
			}	
					break;
			}
			
		}
		
		if (!found) {
			
			System.out.println("Lecturer not found......");
		}	
			
	} // End public update lecturer
	
	// Delete Lecturer
	public static void deleteLecturer(ArrayList<Lecturer> lecturers, Scanner input) {
		
		System.out.print("Enter Lecturer ID to Delete: ");
		String deleteID = input.nextLine().trim();
		
		boolean found = false;
		
		for (Lecturer lecturer: lecturers) {
			
			if (lecturer.getLecturerID().equalsIgnoreCase(deleteID)) {
				
				found = true;
				
				System.out.println("Lecturer Found......");
				
				System.out.println("Lecturer ID: " + lecturer.getLecturerID());
				System.out.println("Lecturer Name: " + lecturer.getLecturerName());
				System.out.println("Lecturer Email: " + lecturer.getLecturerEmail());
				System.out.println("Lecturer Qualification: " + lecturer.getLecturerQualification());
				System.out.println("====================================");
				
				System.out.println("Are you sure you want to delete this lecturer ? (Y/N): "); // 删除决定
				String confirm = input.nextLine().trim();
				
				if (confirm.equalsIgnoreCase("Y")) {
					
					lecturers.remove(lecturer); // remove (从lecturers list 中删除)
					System.out.println("Lecturer deleted successfullu......");
				}
				
				else if (confirm.equalsIgnoreCase("N")) {
					
					System.out.println("Delete cancelled.");
				}
				
				else {
					System.out.println("Invalid confirmation");
				}
				
				break;
			}
		}
		
		if (!found) {
			
			System.out.println("Lecturer not found......");
		}
	} // End of deleteLecturer
	
	// Login ProgrammeLeader
	public static boolean loginProgrammeLeader(Scanner input) {
		
		String correctUsername = "Ben";
		String correctPassword = "12345";
		
		int attempts = 0;
		int maxAttempts = 3;
		
		while (attempts < maxAttempts) {
			
			System.out.print("Enter Username: ");
			String username = input.nextLine().trim();
			
			System.out.print("Enter Password: ");
			String password = input.nextLine();
			
			if (username.equals(correctUsername) && password.equals(correctPassword)) {
				
				return true;
			}
			
			else  {
				attempts++;
				System.out.println("Ivalid Username or Password......");
				System.out.println("Attempts Remaining: " + (maxAttempts - attempts));
			}
		}// End of attempts while loop
			System.out.println("Maximum login attempts reached !");
			System.out.println("Access Denied !");
			
			return false;
	} // End of loginProgrammeLeader
// Lecturer part end
	
//Course part start
	// Register Course
	public static void registerCourse(ArrayList<Course> courses, Scanner input) { 
			
			String id = ""; // can continue use the new Course
			boolean validID = false;
			
			while (!validID) { //while loop for duplicate validation
			
				System.out.print("Enter Course ID: "); // Course ID
				id = input.nextLine().trim(); // .trim() for 空格太多躲过 duplicate checking
					
				if (id.isBlank()) { // if statement to check the input is blank or not (Course ID)
					System.out.print("Can not be empty......");
					continue;
				}
						
				boolean duplicate = false; // no have same ID (假设)
							
				for (Course course: courses) { // For-each to check duplicate
					
					if (course.getCourseID().equalsIgnoreCase(id)) {
						
						duplicate = true;
						break;
					}
				}
				
				if (duplicate ) {
					
					System.out.println("Course ID already exists.");
				} 
				else {
					
					validID = true;
				}
			} // End while loop (validID)
			
			System.out.print("Enter Course Name: "); // Course Name
			String name = input.nextLine().trim();
			
			while (name.isBlank()) {
				
				System.out.println("Cannot be empty.......");
				
				System.out.println("Enter the Course Name again: ");
				name = input.nextLine().trim();
				
			} // End while (is Blank)
	
			int credit = 0;
			boolean validCredit = false;
			
			while (!validCredit) {
				
				System.out.print("Enter Course Credit: "); // Course credit
				String creditInput = input.nextLine().trim();
				
				try { // try statement (可以让程序处理某些运行时错误，而不是直接停止。)
					
					credit = Integer.parseInt(creditInput);
					
					if (credit > 0) {
						
						validCredit = true;
					}
					
					else {
						
						System.out.println("Course Credit must be greater than 0.");
					}
				} // End try Statement
				
				catch (NumberFormatException e) { // catch (捕获指定类型的错误)
					
					System.out.println("Invalid input. Please enter a number");
				}
			} // End while loop (validCredit)
			
			System.out.print("Enter Course Type (Lecture/Practical): "); // Course type
			String type = input.nextLine().trim();
			
			while (!type.equalsIgnoreCase("Lecture") && !type.equalsIgnoreCase("Practical") ) {
				
				System.out.println("Invalid Course Type.");
				
				System.out.println("Please enter Lecture or Practical: ");
				type = input.nextLine().trim();
			} // End while loop (Lecture/Practical)
			
			Course course = new Course(id, name, credit, type); // Course Object
			courses.add(course); // Link the ArrayList
			System.out.println("Course Registered Successfully.");
	} // End regitsreCourse
	
	// View All Course
	public static void viewAllCourse(ArrayList<Course> courses) { 
		
		if (courses.isEmpty()) {
			
			System.out.println("No Course registered yet.");
		}
		
		for (Course course: courses) { // Display all Course
			
			System.out.println("\nCourse ID: " + course.getCourseID());
			System.out.println("Course Name: " + course.getCourseName());
			System.out.println("Course Credit: " + course.getCourseCredit());
			System.out.println("Course Type: " + course.getCourseType());
			System.out.println("---------------------------");
		}

		System.out.println("Total Course: " + courses.size());
	} // End view all course
	
	// Search Course
	public static void searchCourse(ArrayList<Course> courses, Scanner input) { 
		
		System.out.print("\nEnter Course ID to search: ");
		String searchID = input.nextLine().trim();

		boolean found = false; //Lecturer not found 没找到老师

		for (Course course: courses) {
			
			if (course.getCourseID().equalsIgnoreCase(searchID)) {
				
				found = true;
				
				System.out.println("Course ID: " + course.getCourseID());
				System.out.println("Course Name: " + course.getCourseName());
				System.out.println("Course Credit: " + course.getCourseCredit());
				System.out.println("Course Type: " + course.getCourseType());
				System.out.println("====================================");
				
				break;
			}
		}
		
				if (!found) {
					System.out.println("Course not found......");
				}
	} // End of searchCourse
	
	// Update Course
	public static void updateCourse(ArrayList<Course> courses, Scanner input) { 
		
		System.out.print("Enter Course ID to update: ");
		String updateID = input.nextLine().trim();
		
		boolean found = false;
		
		for (Course course: courses) {
			
			if (course.getCourseID().equalsIgnoreCase(updateID)) {
				
				found = true;
				
				System.out.println("Course Found......");
				
				System.out.println("\n========== UPDATE COURSE ==========");
				System.out.println("1. Update Course Name");
				System.out.println("2. Update Course Credit");
				System.out.println("3. Update Course Type");
				System.out.println("4. Back");
				System.out.println("=====================================");
				
				System.out.print("Enter your choice: ");
				String updateChoice = input.nextLine().trim();
				
				switch (updateChoice) {
				
				case "1": // New Course Name
					
					System.out.println("Enter New Course Name: ");
					String newName = input.nextLine().trim();
					
					while (newName.isBlank()) { // test blank
						
						System.out.println("Course Name connot be empty.");
						
						System.out.print("Please enter Course Name again: ");
						newName = input.nextLine().trim();
					} // End while blank
					
					course.setCourseName(newName); // object
					
					System.out.println("Course Name update successfully......");
					
					break;
					
				case "2": // New Course Credit
					
					int newCredit = 0;
					boolean validCredit = false;
					
					while (!validCredit) {
						
						System.out.print("Enter New Course Credit: ");
						String creditInput = input.nextLine().trim();
						
						try {
							
							newCredit = Integer.parseInt(creditInput);
							
							if (newCredit > 0) {
								
								validCredit = true;
							}
							
							else {
								
								System.out.println("Course Credit must be greater than 0.");
							}
							
						}
						
						catch (NumberFormatException e) {
							
							System.out.println("Invalid input. Please entre a number.");
						}
					} //End while loop validCredit
					
					course.setCourseCredit(newCredit);
					System.out.println("Course Credit update successfully.");
					break;
					
				case "3": // New Course Type
					
					System.out.println("Enter new Course Type: ");
					String newType = input.nextLine().trim();
					
					while (!newType.equalsIgnoreCase("Lecture") && !newType.equalsIgnoreCase("Practical")) {
						
						System.out.println("Invalid Course Type.");
						
						System.out.print("Enter Lecture or Practical ");
						newType = input.nextLine().trim();
					} // End while loop 
					
					course.setCourseType(newType);
					
					System.out.println("Course Type updated successfully......");
					
					break;
					
				case "4":
					
					System.out.println("Back to Main Menu");
					break;
					
				default :
					
					System.out.println("Invalid choice");	
			}	
					break;
			}
			
		}
		
		if (!found) {
			
			System.out.println("Course not found......");
		}	
			
	} // End update Course
	
	// Delete Course
	public static void deleteCourse(ArrayList<Course> courses, Scanner input) { 
		
		System.out.print("Enter the Course ID to Delete: ");
		String deleteID = input.nextLine().trim();
		
		boolean found = false;
		
		for (Course course: courses) {
			
			if (course.getCourseID().equalsIgnoreCase(deleteID)) {
				
				found = true;
				
				System.out.println("Course Found......");
				
				System.out.println("Course ID: " + course.getCourseID());
				System.out.println("Course Name: " + course.getCourseName());
				System.out.println("Course Credit: " + course.getCourseCredit());
				System.out.println("Couse Type: " + course.getCourseType());
				System.out.println("====================================");
				
				System.out.println("Are you sure you want to delete this Course ? (Y/N): "); // 删除决定
				String confirm = input.nextLine().trim();
				
				if (confirm.equalsIgnoreCase("Y")) {
					
					courses.remove(course); // Remove Classroom Object from classrooms ArrayList
					System.out.println("Course deleted successfully......");
				}
				
				else if (confirm.equalsIgnoreCase("N")) {
					
					System.out.println("Delete cancelled.");
				}
				
				else {
					System.out.println("Invalid confirmation");
				}
				
				break;
			} // End if statement
		} // End for loop
		
		if (!found) {
			
			System.out.println("Course not found......");
		}
	} // End deteleCoure
// End Course part
	
// Student Group star
	// Register Student Group
	public static void registerStudentGroup (ArrayList<StudentGroup> studentGroups, Scanner input) {
		
		String id = ""; 
		boolean validID = false;
		
		while (!validID) {
			
			System.out.print("Enter the Student Group ID: "); // Student Group ID
			id = input.nextLine().trim();
			
			if (id.isBlank()) { 
				System.out.print("Connot be empty......");
				continue;
			}
					
			boolean duplicate = false; 
						
			for (StudentGroup group: studentGroups) { 
				
				if (group.getStudentGroupID().equalsIgnoreCase(id)) {
					
					duplicate = true;
					break;
				}
			}
			
			if (duplicate ) {
				
				System.out.println("Student Group ID already exists.");
			} 
			else {
				
				validID = true;
			}
		} // End while loop
		
		System.out.print("Enter Student Group Name: "); // Student Group Name
		String groupName = input.nextLine().trim();
		
		while (groupName.isBlank()) {
			
			System.out.println("Connot be empty......");
			
			System.out.println("Enter Student Group Name again: ");
			groupName = input.nextLine().trim();
		}
		
		System.out.print("Enter Student Group Intake Time: "); // Student Group IntakeTime
		String intakeTime = input.nextLine().trim();
		
		while (intakeTime.isBlank()) {
			
			System.out.println("Connot be empty......");
			
			System.out.println("Enter Student Group Intake Time again: ");
			intakeTime = input.nextLine().trim();
		}
		
		System.out.print("Enter Student Group Department: "); // Student Group Department 
		String department = input.nextLine().trim();
		
		while (department.isBlank()) {
			
			System.out.println("Connot be empty......");
			
			System.out.println("Enter Student Group Department again: ");
			department = input.nextLine().trim();
		}
		
		int size = 0;
		boolean validSize = false;
		
		while (!validSize) {
			
			System.out.print("Enter Student Group Size: ");
			String sizeInput = input.nextLine().trim();
			
			try { // try statement (可以让程序处理某些运行时错误，而不是直接停止。)
				
				size = Integer.parseInt(sizeInput);
				
				if (size > 0) {
					
					validSize = true;
				}
				
				else {
					
					System.out.println("Student Group Size must be greater than 0.");
				}
			} // End try Statement
			
			catch (NumberFormatException e) { // catch (捕获指定类型的错误)
				
				System.out.println("Invalid input. Please enter a number");
			}
		}
		
		StudentGroup group = new StudentGroup (id, groupName, intakeTime, department, size); // Object
		studentGroups.add(group);
		
		System.out.println("StudentGroup Registered Successfully.");
	} // End registerStudentGroup
	
	// View All Student Group
	public static void viewAllStudentGroup (ArrayList<StudentGroup> studentGroups) {
		
		if (studentGroups.isEmpty()) {
			
			System.out.println("No Student Group Registered yet.");
			return;
		}
		
		for (StudentGroup group: studentGroups) {
			
			System.out.println("Student Group ID: " + group.getStudentGroupID());
			System.out.println("Student Group Name: " + group.getStudentGroupName());
			System.out.println("Student Group Intake Time: " + group.getStudentGroupIntakeTime());
			System.out.println("Student Group Department: " + group.getStudentGroupDepartment());
			System.out.println("Student Group Size: " + group.getStudentGroupSize());
			System.out.println("====================================");
		}
		
		System.out.println("Total Student Group: " + studentGroups.size());
	} // End viewAllStudentGroup
	
	// Search Student Group
	public static void searchStudentGroup (ArrayList<StudentGroup> studentGroups, Scanner input) {
		
		System.out.print("Enter Student Group ID to search: ");
		String searchID = input.nextLine().trim();
		
		boolean found = false;
		
		for (StudentGroup group: studentGroups) {
			
			if (group.getStudentGroupID().equalsIgnoreCase(searchID)) {
				
				found = true;
				
				System.out.println("Student Group ID： " + group.getStudentGroupID());
				System.out.println("Student Group Name: " + group.getStudentGroupName());
				System.out.println("Student Group Intake Time: " + group.getStudentGroupIntakeTime());
				System.out.println("Student Group Department: " + group.getStudentGroupDepartment());
				System.out.println("Student Group Size: " + group.getStudentGroupSize());
				System.out.println("====================================");
				
				break;
			}
		}
		
		if (!found) {
			
			System.out.println("Student Group not found.");
		}
	} // End searchStudentGroup
	
	// Update Student Group
	public static void updateStudentGroup (ArrayList<StudentGroup> studentGroups, Scanner input) {
		
		System.out.print("Enter Student Group ID to update: ");
		String updateID = input.nextLine().trim();
		
		boolean found = false;
		
		for (StudentGroup group: studentGroups) {
			
			if (group.getStudentGroupID().equalsIgnoreCase(updateID)) {
				
				found =true;
				
				System.out.println("Student Group Found.");
				
				System.out.println("========== UPDATE STUDENT GROUP ==========");
				System.out.println("1. Update Student Group Name");
				System.out.println("2. Update Student Group Intake Time");
				System.out.println("3. Update Student Group Department");
				System.out.println("4. Update Student Group Size");
				System.out.println("5. Back to Main Manu");
				System.out.println("==========================================");
				System.out.println("Choice your update: ");
				String updateChoice = input.nextLine().trim();
				
				switch (updateChoice) {
				
					case "1":
						
						System.out.println("Enter New Student Group Name: "); // Student Group Name Update
						String newName = input.nextLine().trim();
						
						while (newName.isBlank()) {
							
							System.out.println("Connot be empty......");
							
							System.out.print("Please Enter New Student Group Name again: ");
							newName = input.nextLine().trim();
						}
						
						group.setStudentGroupName(newName); // object
						System.out.print("Update Student Group Name Successfully.");
						
						break;
						
					case "2":
						
						System.out.println("Enter New Student Group Intake Time: "); // Student Group IntakeTime Update
						String newIntakeTime = input.nextLine().trim();
						
						while (newIntakeTime.isBlank()) {
							
							System.out.println("Connot be empty......");
							
							System.out.print("Please Enter New Student Group Intake Time again: ");
							newIntakeTime = input.nextLine().trim();
						}
						
						group.setStudentGroupIntakeTime(newIntakeTime); // object
						System.out.print("Update Student Group Intake Time Successfully.");
						
						break;
						
					case "3":
						
						System.out.println("Enter New Student Group Department: "); // Student Group Department Update
						String newDepartment = input.nextLine().trim();
						
						while (newDepartment.isBlank()) {
							
							System.out.println("Connot be empty......");
							
							System.out.print("Please Enter New Student Group Deparment again: ");
							newDepartment = input.nextLine().trim();
						}
						
						group.setStudentGroupDepartment(newDepartment); // object
						System.out.print("Update Student Group Department Successfully.");
						
						break;
						
					case "4":

						int newSize = 0;
						boolean validSize = false;
						
						while (!validSize) {
							
							System.out.print("Enter New Student Group Size: ");
							String sizeInput = input.nextLine().trim();
							
							try {
								
								newSize = Integer.parseInt(sizeInput);
								
								if (newSize > 0) {
									
									validSize = true;
								}
								
								else {
									
									System.out.println("Student Group Size must be greater than 0.");
								}
								
							}
							
							catch (NumberFormatException e) {
								
								System.out.println("Invalid input. Please entre a number.");
							}
						} //End while loop validCredit
						
						group.setStudentGroupSize(newSize);
						System.out.println("Student Group Sizwe update successfully.");
						break;
						
					case "5":
						
						System.out.println("Back to Main Menu");
						break;
						
					default :
						
						System.out.println("Invalid choice");	
						break;
				}
				break;
			}
		} // End For-each loop
			
			if (!found) {
					
				System.out.println("Student Group not found......");
			}	
	} // End updateStudentGroup
	
	// Delete Student Group
	public static void deleteStudentGroup (ArrayList<StudentGroup> studentGroups, Scanner input) {
		
		System.out.print("Enter Student Group ID to Delate: ");
		String deleteID = input.nextLine().trim();
		
		boolean found = false;
		
		for (StudentGroup group: studentGroups) {
			
			if (group.getStudentGroupID().equalsIgnoreCase(deleteID)) {
				
				found = true;
				
				System.out.println("Student Group Found.");
				
				System.out.println("Student Group ID" + group.getStudentGroupID());
				System.out.println("Student Group Name" + group.getStudentGroupName());
				System.out.println("Student Group Intake Time" + group.getStudentGroupIntakeTime());
				System.out.println("Student Group Department" + group.getStudentGroupDepartment());
				System.out.println("Student Group Size" + group.getStudentGroupSize());
				
				System.out.println("Are you sure you want to delete this Student Group ? (Y/N): "); // 删除决定
				String confirm = input.nextLine().trim();
				
				if (confirm.equalsIgnoreCase("Y")) {
					
					studentGroups.remove(group); // Remove Classroom Object from classrooms ArrayList
					System.out.println("Student Group deleted successfully......");
				}
				
				else if (confirm.equalsIgnoreCase("N")) {
					
					System.out.println("Delete cancelled.");
				}
				
				else {
					System.out.println("Invalid confirmation");
				}
				
				break;
			}
		}
				
			if (!found) {
				
				System.out.print("Student Group not Found.");
			}
	} // End deleteStudentGroup
// Student Group End

// Classroom Start
	// Register Classroom 
	public static void registerClassroom (ArrayList<Classroom> classrooms, Scanner input) {
		
		String id = ""; // can continue use the new Classroom
		boolean validID = false;
		
		while (!validID) { //while loop for duplicate validation
		
			System.out.print("Enter Classroom ID: "); // Classroom ID
			id = input.nextLine().trim(); // .trim() for 空格太多躲过 duplicate checking
				
			if (id.isBlank()) { // if statement to check the input is blank or not (Classroom ID)
				System.out.print("Can not be empty......");
				continue;
			}
					
			boolean duplicate = false; // no have same ID (假设)
						
			for (Classroom classroom: classrooms) { // For-each to check duplicate
				
				if (classroom.getClassroomID().equalsIgnoreCase(id)) {
					
					duplicate = true;
					break;
				}
			}
			
			if (duplicate ) {
				
				System.out.println("Classroom ID already exists.");
			} 
			else {
				
				validID = true;
			}
		} // End while loop (validID)
		
		System.out.print("Enter Classroom Name: "); // Classroom Name
		String classroomName = input.nextLine().trim();
		
		while (classroomName.isBlank()) {
			
			System.out.println("Connot be empty......");
			
			System.out.println("Enter Classroom Name again: ");
			classroomName = input.nextLine().trim();
		}
		
		int capacity = 0;
		boolean validCapacity = false;
		
		while (!validCapacity) {
			
			System.out.print("Enter Classroom Capacity: "); // Classroom Capacity
			String capacityInput = input.nextLine().trim();
			
			try { // try statement (可以让程序处理某些运行时错误，而不是直接停止。)
				
				capacity = Integer.parseInt(capacityInput);
				
				if (capacity > 0) {
					
					validCapacity = true;
				}
				
				else {
					
					System.out.println("Classroom Capacity must be greater than 0.");
				}
			} // End try Statement
			
			catch (NumberFormatException e) { // catch (捕获指定类型的错误)
				
				System.out.println("Invalid input. Please enter a number");
			}
		} // End while loop (ValidCapacity)
		
		
		System.out.print("Enter Classroom Location: "); // Classroom Location
		String classroomLocation = input.nextLine().trim();
		
		while (classroomLocation.isBlank()) {
			
			System.out.println("Connot be empty......");
			
			System.out.println("Enter Classroom Location again: ");
			classroomLocation = input.nextLine().trim();
		}
		
		Classroom classroom = new Classroom (id, classroomName, capacity, classroomLocation); // Object
		classrooms.add(classroom);
		
		System.out.println("Registered Classroom Successfully.");
	} // End registerClassroom
	
	// View All Classroom
	public static void viewAllClassroom (ArrayList<Classroom> classrooms) {
		
		if (classrooms.isEmpty()) {
			
			System.out.println("No Classroom registered yet.");
			return;
		}
		
		for (Classroom classroom: classrooms) { // Display all Classroom
			
			System.out.println("\nClassroom ID: " + classroom.getClassroomID());
			System.out.println("Classroom Name: " + classroom.getClassroomName());
			System.out.println("Classroom Capacity: " + classroom.getClassroomCapacity());
			System.out.println("Classroom Location: " + classroom.getClassroomLocation());
			System.out.println("---------------------------");
		}

		System.out.println("Total Classroom: " + classrooms.size());
	} // End viewAllClassroom
	
	// Search Classroom
	public static void searchClassroom (ArrayList<Classroom> classrooms, Scanner input) {
		
		System.out.print("Enter Classroom ID to search: ");
		String searchID = input.nextLine().trim();
		
		boolean found = false;
		
		for (Classroom classroom: classrooms) {
			
			if (classroom.getClassroomID().equalsIgnoreCase(searchID)) {
				
				found = true;
				
				System.out.println("Classroom ID： " + classroom.getClassroomID());
				System.out.println("Classroom Name: " + classroom.getClassroomName());
				System.out.println("Classroom Capacity: " + classroom.getClassroomCapacity());
				System.out.println("Classroom Location: " + classroom.getClassroomLocation());
				System.out.println("====================================");
				
				break;
			}
		}
		
		if (!found) {
			
			System.out.println("Classroom not found.");
		}
	} // End searchClassroom
	
	// Update Classroom
	public static void updateClassroom (ArrayList<Classroom> classrooms, Scanner input) {
		
		System.out.print("Enter Classroom ID to update: "); 
		String updateID = input.nextLine().trim();
		
		boolean found = false;
		
		for (Classroom classroom: classrooms) {
			
			if (classroom.getClassroomID().equalsIgnoreCase(updateID)) {
				
				found =true;
				
				System.out.println("Classroom Found.");
				
				System.out.println("========== UPDATE CLASSROOM ==========");
				System.out.println("1. Update Classroom Name");
				System.out.println("2. Update Classrom Capacity");
				System.out.println("3. Update Classroom Location");
				System.out.println("4. Back to Main Manu");
				System.out.println("==========================================");
				System.out.println("Choice your update: ");
				String updateChoice = input.nextLine().trim();
				
				switch (updateChoice) {
				
					case "1":
						
						System.out.println("Enter New Classroom Name: "); // Classroom Name Update
						String newName = input.nextLine().trim();
						
						while (newName.isBlank()) {
							
							System.out.println("Connot be empty......");
							
							System.out.print("Please Enter New Classroom Name again: ");
							newName = input.nextLine().trim();
						}
						
						classroom.setClassroomName(newName); // object
						System.out.print("Update Classroom Name Successfully.");
						
						break;
						
					case "2":

						int newCapacity = 0;
						boolean validCapacity = false;
						
						while (!validCapacity) {
							
							System.out.print("Enter New Classroom Capacity: "); // Classroom Capacity Update
							String capacityInput = input.nextLine().trim();
							
							try {
								
								newCapacity = Integer.parseInt(capacityInput);
								
								if (newCapacity > 0) {
									
									validCapacity = true;
								}
								
								else {
									
									System.out.println("Classroom Capacity must be greater than 0.");
								}
								
							}
							
							catch (NumberFormatException e) {
								
								System.out.println("Invalid input. Please entre a number.");
							}
						} //End while loop validCapacity
						
						classroom.setClassroomCapacity(newCapacity);
						System.out.println("Classroom Cpacity update successfully.");
						break;
						
					case "3":
						
						System.out.println("Enter New Classroom Loction: "); // Classroom Location Update
						String newLocation = input.nextLine().trim();
						
						while (newLocation.isBlank()) {
							
							System.out.println("Connot be empty......");
							
							System.out.print("Please Enter New Classroom Location again: ");
							newLocation = input.nextLine().trim();
						}
						
						classroom.setClassroomLocation(newLocation); // object
						System.out.print("Update Classroom Location Successfully.");
						
						break;
						
					case "4":
						
						System.out.println("Back to Main Menu");
						break;
						
					default :
						
						System.out.println("Invalid choice");	
						break;
				}
				break;
			}
		} // End For-each loop
			
			if (!found) {
					
				System.out.println("Classroom not found......");
			}	
	} // End updateClassroom
	
	// Delete Classroom
	public static void deleteClassroom (ArrayList<Classroom> classrooms, Scanner input) {
		
		System.out.print("Enter Classroom ID to Delete: ");
		String deleteID = input.nextLine().trim();
		
		boolean found = false;
		
		for (Classroom classroom: classrooms) {
			
			if (classroom.getClassroomID().equalsIgnoreCase(deleteID)) {
				
				found = true;
				
				System.out.println("Classroom Found.");
				
				System.out.println("Classroom ID： " + classroom.getClassroomID());
				System.out.println("Classroom Name: " + classroom.getClassroomName());
				System.out.println("Classroom Capacity: " + classroom.getClassroomCapacity());
				System.out.println("Classroom Location: " + classroom.getClassroomLocation());
				
				System.out.println("Are you sure you want to delete this Classroom ? (Y/N): "); // 删除决定
				String confirm = input.nextLine().trim();
				
				if (confirm.equalsIgnoreCase("Y")) {
					
					classrooms.remove(classroom); // Remove Classroom Object from classrooms ArrayList
					System.out.println("Classroom deleted successfully......");
				}
				
				else if (confirm.equalsIgnoreCase("N")) {
					
					System.out.println("Delete cancelled.");
				}
				
				else {
					System.out.println("Invalid confirmation");
				}
				
				break;
			}
		}
				
			if (!found) {
				
				System.out.print("Classroom not Found.");
			}
	} // End deleteClassroom
// Classroom End
	
// Laboratory Start
	// Register Laboratory
	public static void registerLaboratory (ArrayList<Laboratory> laboratories, Scanner input) {
		
		String id = ""; 
		boolean validID = false;
		
		while (!validID) {
			
			System.out.print("Enter the Laboratory ID: "); // Laboratory ID
			id = input.nextLine().trim();
			
			if (id.isBlank()) { 
				System.out.print("Connot be empty......");
				continue;
			}
					
			boolean duplicate = false; 
						
			for (Laboratory laboratory: laboratories) { 
				
				if (laboratory.getLabID().equalsIgnoreCase(id)) {
					
					duplicate = true;
					break;
				}
			}
			
			if (duplicate ) {
				
				System.out.println("Laboratory ID already exists.");
			} 
			else {
				
				validID = true;
			}
		} // End while loop
		
		System.out.print("Enter Laboratory Name: "); // Laboratory Name
		String labName = input.nextLine().trim();
		
		while (labName.isBlank()) {
			
			System.out.println("Connot be empty......");
			
			System.out.println("Enter Laboratory Name again: ");
			labName = input.nextLine().trim();
		}
		
		int capacity = 0;
		boolean validCapacity = false;
		
		while (!validCapacity) {
			
			System.out.print("Enter Laboratory Capacity: "); // Laboratory Capacity
			String capacityInput = input.nextLine().trim();
			
			try { // try statement (可以让程序处理某些运行时错误，而不是直接停止。)
				
				capacity = Integer.parseInt(capacityInput);
				
				if (capacity > 0) {
					
					validCapacity = true;
				}
				
				else {
					
					System.out.println("Laboratory Capacity must be greater than 0.");
				}
			} // End try Statement
			
			catch (NumberFormatException e) { // catch (捕获指定类型的错误)
				
				System.out.println("Invalid input. Please enter a number");
			}
		} // End while loop (ValidCapacity)
		
		
		System.out.print("Enter Laboratory Location: "); // Laboratory Location
		String labLocation = input.nextLine().trim();
		
		while (labLocation.isBlank()) {
			
			System.out.println("Connot be empty......");
			
			System.out.println("Enter Laboratory Location again: ");
			labLocation = input.nextLine().trim();
		}
		
		System.out.print("Enter Laboratory Specification: "); // Laboratory Name
		String labSpecification = input.nextLine().trim();
		
		while (labSpecification.isBlank()) {
			
			System.out.println("Connot be empty......");
			
			System.out.println("Enter Laboratory Specification again: ");
			labSpecification = input.nextLine().trim();
		}
		
		Laboratory laboratory = new Laboratory (id, labName, capacity, labLocation, labSpecification); // Object
		laboratories.add(laboratory);
		
		System.out.println("Registered Laboratory Successfully.");
	} // End registerLaboratory
	
	// View All Laboratory
	public static void viewAllLaboratory (ArrayList<Laboratory> laboratories) {
		
		if (laboratories.isEmpty()) {
			
			System.out.println("No Laboratory registered yet.");
			return;
		}
		
		for (Laboratory laboratory: laboratories) { // Display all Laboratory
			
			System.out.println("\nLaboratory ID: " + laboratory.getLabID());
			System.out.println("Laboratory Name: " + laboratory.getLabName());
			System.out.println("Laboratory Capacity: " + laboratory.getLabCapacity());
			System.out.println("Laboratory Location: " + laboratory.getLabLocation());
			System.out.println("Laboratory Specification: " + laboratory.getLabSpecification());
			System.out.println("---------------------------");
		}

		System.out.println("Total Laboratory: " + laboratories.size());
	} // End viewAllLaboratory
	
	// Search Laboratory
	public static void searchLaboratory (ArrayList<Laboratory> laboratories, Scanner input) {
		
		System.out.print("Enter Laboratory ID to search: ");
		String searchID = input.nextLine().trim();
		
		boolean found = false;
		
		for (Laboratory laboratory: laboratories) {
			
			if (laboratory.getLabID().equalsIgnoreCase(searchID)) {
				
				found = true;
				
				System.out.println("Laboratory ID： " + laboratory.getLabID());
				System.out.println("Laboratory Name: " + laboratory.getLabName());
				System.out.println("Laboratory Capacity: " + laboratory.getLabCapacity());
				System.out.println("Laboratory Location: " + laboratory.getLabLocation());
				System.out.println("Laboratory Specification: " + laboratory.getLabSpecification());
				System.out.println("====================================");
				
				break;
			}
		}
		
		if (!found) {
			
			System.out.println("Laboratory not found.");
		}
	} // End searchLaboratory
	
	// Update Laboratory
	public static void updateLaboratory (ArrayList<Laboratory> laboratories, Scanner input) {
		
		System.out.print("Enter Laboratory ID to update: "); 
		String updateID = input.nextLine().trim();
		
		boolean found = false;
		
		for (Laboratory laboratory: laboratories) {
			
			if (laboratory.getLabID().equalsIgnoreCase(updateID)) {
				
				found =true;
				
				System.out.println("Laboratory Found.");
				
				System.out.println("========== UPDATE LABORATORY ==========");
				System.out.println("1. Update Laboratory Name");
				System.out.println("2. Update Laboratory Capacity");
				System.out.println("3. Update Laboratory Location");
				System.out.println("4. Update Laboratory Specification");
				System.out.println("5. Back to Main Manu");
				System.out.println("==========================================");
				System.out.println("Choice your update: ");
				String updateChoice = input.nextLine().trim();
				
				switch (updateChoice) {
				
					case "1":
						
						System.out.println("Enter New Laboratory Name: "); // Laboratory Name Update
						String newName = input.nextLine().trim();
						
						while (newName.isBlank()) {
							
							System.out.println("Connot be empty......");
							
							System.out.print("Please Enter New Laboratory Name again: ");
							newName = input.nextLine().trim();
						}
						
						laboratory.setLabName(newName); // object
						System.out.print("Update Laboratory Name Successfully.");
						
						break;
						
					case "2":

						int newCapacity = 0;
						boolean validCapacity = false;
						
						while (!validCapacity) {
							
							System.out.print("Enter New Laboratory Capacity: "); // Classroom Capacity Update
							String capacityInput = input.nextLine().trim();
							
							try {
								
								newCapacity = Integer.parseInt(capacityInput);
								
								if (newCapacity > 0) {
									
									validCapacity = true;
								}
								
								else {
									
									System.out.println("Laboratory Capacity must be greater than 0.");
								}
								
							}
							
							catch (NumberFormatException e) {
								
								System.out.println("Invalid input. Please entre a number.");
							}
						} //End while loop validCapacity
						
						laboratory.setLabCapacity(newCapacity);
						System.out.println("Laboratory Cpacity update successfully.");
						break;
						
					case "3":
						
						System.out.println("Enter New Laboratory Loction: "); // Laboratory Location Update
						String newLocation = input.nextLine().trim();
						
						while (newLocation.isBlank()) {
							
							System.out.println("Connot be empty......");
							
							System.out.print("Please Enter New Laboratory Location again: ");
							newLocation = input.nextLine().trim();
						}
						
						laboratory.setLabLocation(newLocation); // object
						System.out.print("Update Laboratory Location Successfully.");
						
						break;
						
					case "4":
						
						System.out.println("Enter New Laboratory Specification: "); // Laboratory Specification Update
						String newSpecification = input.nextLine().trim();
						
						while (newSpecification.isBlank()) {
							
							System.out.println("Connot be empty......");
							
							System.out.print("Please Enter New Laboratory Specification again: ");
							newSpecification = input.nextLine().trim();
						}
						
						laboratory.setLabSpecification(newSpecification); // object
						System.out.print("Update Laboratory Specification Successfully.");
						
						break;
						
					case "5":
						
						System.out.println("Back to Main Menu");
						break;
						
					default :
						
						System.out.println("Invalid choice");	
						break;
				}
				break;
			}
		} // End For-each loop
			
			if (!found) {
					
				System.out.println("Laboratory not found......");
			}	
	} // End updateLaboratory
	
	// Delete Laboratory
	public static void deleteLaboratory (ArrayList<Laboratory> laboratories, Scanner input) {
		
		System.out.print("Enter Laboratory ID to Delete: ");
		String deleteID = input.nextLine().trim();
		
		boolean found = false;
		
		for (Laboratory laboratory: laboratories) {
			
			if (laboratory.getLabID().equalsIgnoreCase(deleteID)) {
				
				found = true;
				
				System.out.println("Laboratory Found.");
				
				System.out.println("Laboratory ID： " + laboratory.getLabID());
				System.out.println("Laboratory Name: " + laboratory.getLabName());
				System.out.println("Laboratory Capacity: " + laboratory.getLabCapacity());
				System.out.println("Laboratory Location: " + laboratory.getLabLocation());
				System.out.println("Laboratory Specification: " + laboratory.getLabSpecification());
				
				System.out.println("Are you sure you want to delete this Laboratory ? (Y/N): "); // 删除决定
				String confirm = input.nextLine().trim();
				
				if (confirm.equalsIgnoreCase("Y")) {
					
					laboratories.remove(laboratory); // Remove Classroom Object from classrooms ArrayList
					System.out.println("Laboratory deleted successfully......");
				}
				
				else if (confirm.equalsIgnoreCase("N")) {
					
					System.out.println("Delete cancelled.");
				}
				
				else {
					System.out.println("Invalid confirmation");
				}
				
				break;
			}
		}
				
			if (!found) {
				
				System.out.print("Laboratory not Found.");
			}
	} // End deleteLaboratory
// Laboratory End

// Class Session Start
	// Register Class Session
	public static void registerClassSession (ArrayList<ClassSession> classSessions, ArrayList<Course> courses, ArrayList<Lecturer> lecturers, ArrayList<StudentGroup> studentGroups, ArrayList<Classroom> classrooms, ArrayList<Laboratory> laboratories, Scanner input) {
		
		String id = ""; 
		boolean validID = false;
		
		while (!validID) {
			
			System.out.print("Enter the Class Session ID: "); // Class Session ID
			id = input.nextLine().trim();
			
			if (id.isBlank()) { 
				System.out.print("Connot be empty......");
				continue;
			}
					
			boolean duplicate = false; 
						
			for (ClassSession classSession: classSessions) { 
				
				if (classSession.getSessionID().equalsIgnoreCase(id)) {
					
					duplicate = true;
					break;
				}
			}
			
			if (duplicate ) {
				
				System.out.println("Class Session ID already exists.");
			} 
			else {
				
				validID = true;
			}
		} // End while loop (validID)
		
		String courseID = "";
		boolean validCourse = false;
		
		while (!validCourse) {
			
			System.out.print("Enter Course ID: "); // Course ID
			courseID = input.nextLine().trim();
			
			if (courseID.isBlank()) {
				
				System.out.println("Course ID cannot be empty.");
				continue;
			}
			
			boolean found = false;
			
			for (Course course: courses) { // To check the Course ID already register or not
				
				if (course.getCourseID().equalsIgnoreCase(courseID)) {
					
					found = true;
					break;
				}
			}
			
			if (found) {
				
				System.out.println("Course Found.");
				validCourse = true;
			}
			
			else {
				
				System.out.println("Course ID not found.");
				System.out.println("Please enter a registered Course ID.");
			}
		} // End course ID validation
		
		String courseType = "";

		for (Course course : courses) {

		    if (course.getCourseID().equalsIgnoreCase(courseID)) {

		        courseType = course.getCourseType();
		        break;
		    }
		}
		
		String lecturerID = "";
		boolean validLecturer = false;
		
		while (!validLecturer) {
			
			System.out.print("Enter Lecturer ID: "); // Lecturer ID
			lecturerID = input.nextLine().trim();
			
			if (lecturerID.isBlank()) {
				
				System.out.println("Lecturer ID cannot be empty.");
				continue;
			}
			
			boolean found = false;
			
			for (Lecturer lecturer: lecturers) { // To check the Lecturer ID already register or not
				
				if (lecturer.getLecturerID().equalsIgnoreCase(lecturerID)) {
					
					found = true;
					break;
				}
			}
			
			if (found) {
				
				System.out.println("Lecturer Found.");
				validLecturer = true;
			}
			
			else {
				
				System.out.println("Lecturer ID not found.");
				System.out.println("Please enter a registered Lecturer ID.");
			}
		} // End Lecturer ID validation
		
		String studentGroupID = "";
		boolean validStudentGroup = false;
		
		while (!validStudentGroup) {
			
			System.out.print("Enter Student Group ID: "); // Student Group ID
			studentGroupID = input.nextLine().trim();
			
			if (studentGroupID.isBlank()) {
				
				System.out.println("Student Group ID cannot be empty.");
				continue;
			}
			
			boolean found = false;
			
			for (StudentGroup group: studentGroups) { // To check the Student Group ID already register or not
				
				if (group.getStudentGroupID().equalsIgnoreCase(studentGroupID)) {
					
					found = true;
					break;
				}
			}
			
			if (found) {
				
				System.out.println("Student Group Found.");
				validStudentGroup = true;
			}
			
			else {
				
				System.out.println("Student Group ID not found.");
				System.out.println("Please enter a registered Student Group ID.");
			}
		} // End Student Group ID validation
		
		// Student Group Size
		int groupSize = 0;

		for (StudentGroup group : studentGroups) {

		    if (group.getStudentGroupID().equalsIgnoreCase(studentGroupID)) {

		        groupSize = group.getStudentGroupSize();
		        break;
		    }
		} 
		
		String day = "";
		boolean validDay = false;
		
		while (!validDay) {
			
			System.out.print("Enter Class Session Day: "); // Class Session Day
			day = input.nextLine().trim();
			
			if (!day.equalsIgnoreCase("Monday") && !day.equalsIgnoreCase("Tuesday") && !day.equalsIgnoreCase("Wednesday") && !day.equalsIgnoreCase("Thursday") && !day.equalsIgnoreCase("Friday")) {
				
				System.out.println("Invalid Day.");
				System.out.println("Please enter Monday to Friday.");
			}
			
			else {
				
				System.out.println("Day valid");
				validDay = true;
			}
		} // End while loop (validDay)
		
		String startTime = "";
		boolean validStartTime = false;
		
		while (!validStartTime) {
			
			System.out.print("Enter Start Time (HH:mm): "); // Start Time
			startTime = input.nextLine().trim();
			
			try {
				// Convert String to LocalTime to validate the time format
				LocalTime.parse(startTime);
				
				System.out.println("Start Time Valid.");
				validStartTime = true;
			}
			
			catch (DateTimeParseException e) {
				
				System.out.println("Invalid Time Format.");
				System.out.println("Please enter time in HH:mm format.");
				
			}
		} // End while loop (validStartTime)
		
		String endTime = "";
		boolean validEndTime = false;
		
		while (!validEndTime) {
			
			System.out.print("Enter End Time (HH:mm): "); // End Time
			endTime = input.nextLine().trim();
			
			try {
				
				LocalTime end = LocalTime.parse(endTime);
				LocalTime start = LocalTime.parse(startTime);
				
				if (end.isAfter(start)) {
					
					System.out.println("End Time Valid.");
					validEndTime = true;
				}
				
				else {
					
					System.out.println("Invalid End Time.");
					System.out.println("End Time must be later than Start Time.");
				}
			}
			
			catch (DateTimeParseException e) {
				
				System.out.println("Invalid Time Format.");
				System.out.println("Please enter time in HH:mm format.");
				
			}
		} // End while loop (validEndTime)
		
		// Classroom ID + Capacity Validation
		
		String roomID = "";
		
		if (courseType.equalsIgnoreCase("Lecture")) {
			
		boolean validRoom = false;

		while (!validRoom) {

		    boolean roomFound = false;

		    System.out.print("Enter Classroom ID: ");
		    roomID = input.nextLine().trim();

		    for (Classroom classroom : classrooms) {

		        if (classroom.getClassroomID().equalsIgnoreCase(roomID)) {

		            roomFound = true;
		            System.out.println("Classroom Found.");

		            int roomCapacity = classroom.getClassroomCapacity();

		            if (roomCapacity >= groupSize) {

		                System.out.println("Classroom Capacity Valid.");
		                validRoom = true;

		            } else {

		                System.out.println("Classroom Capacity Insufficient.");
		            }

		            break;
		        }
		    }

		    if (!roomFound) {
		        System.out.println("Classroom not found.");
		    }
		} // End while loop (validRoom)
		} // End Lecture
		
		else if (courseType.equalsIgnoreCase("Practical")) {
		
			boolean validLab = false;
			
			while (!validLab) {
				
				boolean labFound = false;
				
				System.out.print("Enter Laboratory ID: ");
				roomID = input.nextLine().trim();
				
				for (Laboratory laboratory: laboratories) {
					
					if (laboratory.getLabID().equalsIgnoreCase(roomID)) {
						
						labFound = true;
						
						System.out.println("Laboratory Found.");
						
						int laboratoryCapacity = laboratory.getLabCapacity();
						
						if (laboratoryCapacity >= groupSize) {
							
							System.out.println("Laboratory Capacity Valid.");
							
							validLab = true;
						}
						
						else {
							
							System.out.println("Laboratory Capacity Insufficient.");
						}
						break;
					}
				}
				
				if (!labFound) {
					
					System.out.println("Laboratory not found.");
				}
			} // End while loop (validLab)
		} // End else-if (Practical)
		
		LocalTime newStart = LocalTime.parse(startTime);
		LocalTime newEnd = LocalTime.parse(endTime);
		
		boolean conflict = false;
		// Scheduling conflicts
		for (ClassSession session: classSessions) {
			// Day
			if (session.getDay().equalsIgnoreCase(day)) {
				// Time
				LocalTime existingStart = LocalTime.parse(session.getStartTime());
				LocalTime existingEnd = LocalTime.parse(session.getEndTime());
				
				boolean overlap = newStart.isBefore(existingEnd) && newEnd.isAfter(existingStart);
				
				if (overlap) {
					// Lecturer Conflict
					if (session.getLecturerID().equalsIgnoreCase(lecturerID)) {

				        System.out.println("Lecturer Scheduling Conflict Detected.");
				        conflict = true;
					}
					// Student Group Conflict
					if (session.getStudentGroupID().equalsIgnoreCase(studentGroupID)) {
	
					    System.out.println("Student Group Scheduling Conflict Detected.");
					    conflict = true;
					}
					// Room Conflict
					if (session.getRoomID().equalsIgnoreCase(roomID)) {

					    System.out.println("Room Scheduling Conflict Detected.");
					    conflict = true;
					}
				}
			}
		} // End for loop
		
		if (conflict) {

		    System.out.println("Class Session Registration Failed.");
		    System.out.println("Scheduling Conflict Detected.");

		    return;
		}
		
		ClassSession newSession = new ClassSession(id , courseID, lecturerID, studentGroupID, day, startTime.toString(), endTime.toString(), roomID);
		classSessions.add(newSession);

		System.out.println("Class Session Registered Successfully.");
	} // End registerClassSession
	
	// View All Class Session
	public static void viewAllClassSession(ArrayList<ClassSession> classSessions) {
			
		    if (classSessions.isEmpty()) {
		        System.out.println("No Class Session Registered Yet.");
		        return;
		    }
	
		    System.out.println("\n========== ALL CLASS SESSIONS ==========");
		        
		    for (ClassSession session : classSessions) {
		    	
		    	System.out.println("\nSession ID: " + session.getSessionID());
		        System.out.println("Course ID: " + session.getCourseID());
		        System.out.println("Lecturer ID: " + session.getLecturerID());
		        System.out.println("Student Group ID: " + session.getStudentGroupID());
		        System.out.println("Day: " + session.getDay());
		        System.out.println("Start Time: " + session.getStartTime());
		        System.out.println("End Time: " + session.getEndTime());
		        System.out.println("Room ID: " + session.getRoomID());
		        System.out.println("====================================");
	
		  }
			
		System.out.println("Total Class Sessions: " + classSessions.size());
	} // End viewAllClassSession
	
	// Search Class Session
	public static void searchClassSession (ArrayList<ClassSession> classSessions, Scanner input) {

	    System.out.print("\nEnter Class Session ID to search: ");
	    String searchID = input.nextLine().trim();

	    boolean found = false; // 假设还没有找到 Class Session

	    // For-each loop to search Class Session
	    for (ClassSession session : classSessions) {

	        // Compare Session ID
	        if (session.getSessionID().equalsIgnoreCase(searchID)) {

	            found = true; // Class Session Found

	            System.out.println("\nClass Session Found......");
	            System.out.println("========== CLASS SESSION DETAILS ==========");
	            System.out.println("Session ID: " + session.getSessionID());
	            System.out.println("Course ID: " + session.getCourseID());
	            System.out.println("Lecturer ID: " + session.getLecturerID());
	            System.out.println("Student Group ID: " + session.getStudentGroupID());
	            System.out.println("Day: " + session.getDay());
	            System.out.println("Start Time: " + session.getStartTime());
	            System.out.println("End Time: " + session.getEndTime());
	            System.out.println("Room ID: " + session.getRoomID());
	            System.out.println("===========================================");

	            break; // Stop searching
	        }
	    }

	    // If Class Session Not Found
	    if (!found) {
	        System.out.println("Class Session Not Found......");
	    }

	} // End searchClassSession
	
	// Update Class Session
	public static void updateClassSession(ArrayList<ClassSession> classSessions, ArrayList<Course> courses, ArrayList<Lecturer> lecturers, ArrayList<StudentGroup> studentGroups, ArrayList<Classroom> classrooms, ArrayList<Laboratory> laboratories, Scanner input) {

	    // Check empty ArrayList
	    if (classSessions.isEmpty()) {
	        System.out.println("No Class Session Registered Yet.");
	        return;
	    }

	    // Search Class Session
	    System.out.print("Enter Class Session ID to update: ");
	    String updateID = input.nextLine().trim();

	    ClassSession selectedSession = null;
	    int sessionIndex = -1;

	    for (int i = 0; i < classSessions.size(); i++) {

	        ClassSession session = classSessions.get(i);

	        if (session.getSessionID().equalsIgnoreCase(updateID)) {

	            selectedSession = session;
	            sessionIndex = i;
	            break;
	        }
	    }

	    if (selectedSession == null) {
	        System.out.println("Class Session Not Found.");
	        return;
	    }

	    System.out.println("\nClass Session Found......");

	    // Current Class Session Information
	    System.out.println("========== CURRENT CLASS SESSION ==========");
	    System.out.println("Session ID: " + selectedSession.getSessionID());
	    System.out.println("Course ID: " + selectedSession.getCourseID());
	    System.out.println("Lecturer ID: " + selectedSession.getLecturerID());
	    System.out.println("Student Group ID: " + selectedSession.getStudentGroupID());
	    System.out.println("Day: " + selectedSession.getDay());
	    System.out.println("Start Time: " + selectedSession.getStartTime());
	    System.out.println("End Time: " + selectedSession.getEndTime());
	    System.out.println("Room ID: " + selectedSession.getRoomID());
	    System.out.println("===========================================");

	    // Update Menu
	    System.out.println("\n========== UPDATE CLASS SESSION ==========");
	    System.out.println("1. Update Course");
	    System.out.println("2. Update Lecturer");
	    System.out.println("3. Update Student Group");
	    System.out.println("4. Update Day");
	    System.out.println("5. Update Start Time & End Time");
	    System.out.println("6. Update Room");
	    System.out.println("7. Back to Main Menu");
	    System.out.println("==========================================");

	    System.out.print("Enter your choice: ");
	    String updateChoice = input.nextLine().trim();

	    // Copy current values
	    String courseID = selectedSession.getCourseID();
	    String lecturerID = selectedSession.getLecturerID();
	    String studentGroupID = selectedSession.getStudentGroupID();
	    String day = selectedSession.getDay();
	    String startTime = selectedSession.getStartTime();
	    String endTime = selectedSession.getEndTime();
	    String roomID = selectedSession.getRoomID();

	    // Update selected information
	    switch (updateChoice) {

	        case "1": // Update Course

	            System.out.print("Enter New Course ID: ");
	            courseID = input.nextLine().trim();

	            break;

	        case "2": // Update Lecturer

	            System.out.print("Enter New Lecturer ID: ");
	            lecturerID = input.nextLine().trim();

	            break;

	        case "3": // Update Student Group

	            System.out.print("Enter New Student Group ID: ");
	            studentGroupID = input.nextLine().trim();

	            break;

	        case "4": // Update Day

	            System.out.print("Enter New Day: ");
	            day = input.nextLine().trim();

	            break;

	        case "5": // Update Time

	            System.out.print("Enter New Start Time (HH:mm): ");
	            startTime = input.nextLine().trim();

	            System.out.print("Enter New End Time (HH:mm): ");
	            endTime = input.nextLine().trim();

	            break;

	        case "6": // Update Room

	            System.out.print("Enter New Room ID: ");
	            roomID = input.nextLine().trim();

	            break;

	        case "7":

	            System.out.println("Back to Main Menu.");
	            return;

	        default:

	            System.out.println("Invalid Choice.");
	            return;
	    }

	    // Course Validation
	    String courseType = "";
	    boolean courseFound = false;

	    for (Course course : courses) {

	        if (course.getCourseID().equalsIgnoreCase(courseID)) {

	            courseFound = true;
	            courseType = course.getCourseType();
	            break;
	        }
	    }

	    if (!courseFound) {
	        System.out.println("Course ID Not Found.");
	        System.out.println("Update Failed.");
	        return;
	    }

	    // Lecturer Validation
	    boolean lecturerFound = false;

	    for (Lecturer lecturer : lecturers) {

	        if (lecturer.getLecturerID().equalsIgnoreCase(lecturerID)) {

	            lecturerFound = true;
	            break;
	        }
	    }

	    if (!lecturerFound) {
	        System.out.println("Lecturer ID Not Found.");
	        System.out.println("Update Failed.");
	        return;
	    }

	    // Student Group Validation
	    boolean groupFound = false;
	    int groupSize = 0;

	    for (StudentGroup group : studentGroups) {

	        if (group.getStudentGroupID().equalsIgnoreCase(studentGroupID)) {

	            groupFound = true;
	            groupSize = group.getStudentGroupSize();
	            break;
	        }
	    }

	    if (!groupFound) {
	        System.out.println("Student Group ID Not Found.");
	        System.out.println("Update Failed.");
	        return;
	    }

	    // Day Validation
	    if (!day.equalsIgnoreCase("Monday")
	            && !day.equalsIgnoreCase("Tuesday")
	            && !day.equalsIgnoreCase("Wednesday")
	            && !day.equalsIgnoreCase("Thursday")
	            && !day.equalsIgnoreCase("Friday")) {

	        System.out.println("Invalid Day.");
	        System.out.println("Please Enter Monday to Friday.");
	        System.out.println("Update Failed.");
	        return;
	    }

	    // Time Validation
	    LocalTime newStart;
	    LocalTime newEnd;

	    try {

	        newStart = LocalTime.parse(startTime);
	        newEnd = LocalTime.parse(endTime);

	        if (!newStart.isBefore(newEnd)) {

	            System.out.println("Invalid Time Range.");
	            System.out.println("Start Time must be before End Time.");
	            System.out.println("Update Failed.");
	            return;
	        }

	    } catch (DateTimeParseException e) {

	        System.out.println("Invalid Time Format.");
	        System.out.println("Please Enter Time in HH:mm Format.");
	        System.out.println("Update Failed.");
	        return;
	    }

	    // Room Type & Capacity Validation
	    boolean roomFound = false;
	    boolean validCapacity = false;

	    if (courseType.equalsIgnoreCase("Lecture")) {

	        for (Classroom classroom : classrooms) {

	            if (classroom.getClassroomID().equalsIgnoreCase(roomID)) {

	                roomFound = true;

	                if (classroom.getClassroomCapacity() >= groupSize) {

	                    validCapacity = true;

	                } 
	                
	                else {

	                    System.out.println("Classroom Capacity Insufficient.");
	                }

	                break;
	            }
	        }

	    } 
	    
	    else if (courseType.equalsIgnoreCase("Practical")) {

	        for (Laboratory laboratory : laboratories) {

	            if (laboratory.getLabID().equalsIgnoreCase(roomID)) {

	                roomFound = true;

	                if (laboratory.getLabCapacity() >= groupSize) {

	                    validCapacity = true;

	                } 
	                
	                else {

	                    System.out.println("Laboratory Capacity Insufficient.");
	                }

	                break;
	            }
	        }

	    } 
	    
	    else {

	        System.out.println("Invalid Course Type.");
	        System.out.println("Update Failed.");
	        return;
	    }

	    if (!roomFound) {

	        System.out.println("Room Not Found or Incorrect Room Type.");
	        System.out.println("Update Failed.");
	        return;
	    }

	    if (!validCapacity) {

	        System.out.println("Update Failed.");
	        return;
	    }

	    // Scheduling Conflict Validation
	    boolean conflict = false;

	    for (ClassSession session : classSessions) {

	        // Exclude the current Class Session
	        if (session == selectedSession) {
	            continue;
	        }

	        // Check same day
	        if (session.getDay().equalsIgnoreCase(day)) {

	            LocalTime existingStart =
	                    LocalTime.parse(session.getStartTime());

	            LocalTime existingEnd =
	                    LocalTime.parse(session.getEndTime());

	            // Check time overlap
	            boolean overlap =
	                    newStart.isBefore(existingEnd)
	                    && newEnd.isAfter(existingStart);

	            if (overlap) {

	                // Lecturer Conflict
	                if (session.getLecturerID().equalsIgnoreCase(lecturerID)) {

	                    System.out.println(
	                        "Lecturer Scheduling Conflict Detected."
	                    );

	                    conflict = true;
	                }

	                // Student Group Conflict
	                if (session.getStudentGroupID().equalsIgnoreCase(studentGroupID)) {

	                    System.out.println(
	                        "Student Group Scheduling Conflict Detected."
	                    );

	                    conflict = true;
	                }

	                // Room Conflict
	                if (session.getRoomID().equalsIgnoreCase(roomID)) {

	                    System.out.println(
	                        "Room Scheduling Conflict Detected."
	                    );

	                    conflict = true;
	                }
	            }
	        }
	    }

	    if (conflict) {

	        System.out.println("Class Session Update Failed.");
	        System.out.println("Scheduling Conflict Detected.");
	        return;
	    }
	    
	    // Save Updated Class Session
	    ClassSession updatedSession = new ClassSession(
	            selectedSession.getSessionID(),
	            courseID,
	            lecturerID,
	            studentGroupID,
	            day,
	            startTime,
	            endTime,
	            roomID
	    );

	    classSessions.set(sessionIndex, updatedSession);

	    System.out.println("\nClass Session Updated Successfully.");

	} // End updateClassSession
	
	// Delete Class Session
	public static void deleteClassSession(
	        ArrayList<ClassSession> classSessions,
	        Scanner input) {

	    // Check if ArrayList is empty
	    if (classSessions.isEmpty()) {

	        System.out.println("No Class Session Registered Yet.");
	        return;
	    }

	    // Enter Class Session ID
	    System.out.print("Enter Class Session ID to Delete: ");
	    String deleteID = input.nextLine().trim();

	    boolean found = false;

	    // Search Class Session
	    for (ClassSession session : classSessions) {

	        if (session.getSessionID().equalsIgnoreCase(deleteID)) {

	            found = true;

	            System.out.println("\nClass Session Found......");

	            // Display Class Session Details
	            System.out.println("========== CLASS SESSION DETAILS ==========");
	            System.out.println("Session ID: " + session.getSessionID());
	            System.out.println("Course ID: " + session.getCourseID());
	            System.out.println("Lecturer ID: " + session.getLecturerID());
	            System.out.println("Student Group ID: " + session.getStudentGroupID());
	            System.out.println("Day: " + session.getDay());
	            System.out.println("Start Time: " + session.getStartTime());
	            System.out.println("End Time: " + session.getEndTime());
	            System.out.println("Room ID: " + session.getRoomID());
	            System.out.println("===========================================");

	            // Delete Confirmation
	            System.out.print("Are you sure you want to delete this Class Session? (Y/N): ");
	            String confirm = input.nextLine().trim();

	            if (confirm.equalsIgnoreCase("Y")) {

	                classSessions.remove(session);

	                System.out.println("Class Session Deleted Successfully......");

	            } else if (confirm.equalsIgnoreCase("N")) {

	                System.out.println("Delete Cancelled.");

	            } else {

	                System.out.println("Invalid Confirmation.");
	                System.out.println("Delete Cancelled.");
	            }

	            break;
	        }

	    } // End for-each loop

	    // Class Session Not Found
	    if (!found) {

	        System.out.println("Class Session Not Found......");
	    }

	} // End deleteClassSession
	
	// Generate Entire Timetable
	public static void generateTimetable(
	        ArrayList<ClassSession> classSessions,
	        ArrayList<Course> courses,
	        ArrayList<Lecturer> lecturers,
	        ArrayList<StudentGroup> studentGroups,
	        ArrayList<Classroom> classrooms,
	        ArrayList<Laboratory> laboratories,
	        Scanner input) {

	    // ==========================================
	    // 1. Check Required Information
	    // ==========================================

	    if (courses.isEmpty()
	            || lecturers.isEmpty()
	            || studentGroups.isEmpty()) {

	        System.out.println("Insufficient Information.");
	        System.out.println("Please Register Course, Lecturer and Student Group First.");
	        return;
	    }

	    if (classrooms.isEmpty() && laboratories.isEmpty()) {

	        System.out.println("No Classroom or Laboratory Registered.");
	        return;
	    }

	    System.out.println("\n========== GENERATE ENTIRE TIMETABLE ==========");

	    // 2. Enter Number of Scheduling Requests
	    int totalRequests = 0;
	    boolean validNumber = false;

	    while (!validNumber) {

	        System.out.print("How many Class Sessions do you want to generate? ");

	        String numberInput = input.nextLine().trim();

	        try {

	            totalRequests = Integer.parseInt(numberInput);

	            if (totalRequests > 0) {

	                validNumber = true;

	            }
	            
	            else {

	                System.out.println("Number of Class Sessions must be greater than 0.");
	            }

	        } 
	        
	        catch (NumberFormatException e) {

	            System.out.println("Invalid Input. Please Enter a Number.");
	        }
	    }

	    // 3. Store Scheduling Requests
	    ArrayList<String[]> schedulingRequests = new ArrayList<>();

	    for (int i = 1; i <= totalRequests; i++) {

	        System.out.println("\n========== SESSION REQUEST " + i + " ==========");

	        // Course Validation
	        String courseID = "";
	        boolean validCourse = false;

	        while (!validCourse) {

	            System.out.print("Enter Course ID: ");
	            courseID = input.nextLine().trim();

	            for (Course course : courses) {

	                if (course.getCourseID().equalsIgnoreCase(courseID)) {

	                    courseID = course.getCourseID();
	                    validCourse = true;
	                    break;
	                }
	            }

	            if (!validCourse) {

	                System.out.println("Course ID Not Found. Please Try Again.");
	            }
	        }

	        // Lecturer Validation
	        String lecturerID = "";
	        boolean validLecturer = false;

	        while (!validLecturer) {

	            System.out.print("Enter Lecturer ID: ");
	            lecturerID = input.nextLine().trim();

	            for (Lecturer lecturer : lecturers) {

	                if (lecturer.getLecturerID().equalsIgnoreCase(lecturerID)) {

	                    lecturerID = lecturer.getLecturerID();
	                    validLecturer = true;
	                    break;
	                }
	            }

	            if (!validLecturer) {

	                System.out.println("Lecturer ID Not Found. Please Try Again.");
	            }
	        }

	        // Student Group Validation
	        String studentGroupID = "";
	        boolean validGroup = false;

	        while (!validGroup) {

	            System.out.print("Enter Student Group ID: ");
	            studentGroupID = input.nextLine().trim();

	            for (StudentGroup group : studentGroups) {

	                if (group.getStudentGroupID().equalsIgnoreCase(studentGroupID)) {

	                    studentGroupID = group.getStudentGroupID();
	                    validGroup = true;
	                    break;
	                }
	            }

	            if (!validGroup) {

	                System.out.println( "Student Group ID Not Found. Please Try Again.");
	            }
	        }

	        // Save Scheduling Request
	        String[] request = {courseID, lecturerID, studentGroupID};

	        schedulingRequests.add(request);

	        System.out.println("Scheduling Request Added Successfully.");

	    } // End Request Input Loop

	    // 4. Prepare Automatic Scheduling
	    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};

	    LocalTime[] timeSlots = {LocalTime.of(9, 0), LocalTime.of(11, 0), LocalTime.of(13, 0), LocalTime.of(15, 0)};

	    int successCount = 0;
	    int failedCount = 0;

	    ArrayList<String> successResults = new ArrayList<>();
	    ArrayList<String> failedResults = new ArrayList<>();

	    System.out.println( "\n========== START AUTOMATIC SCHEDULING ==========" );

	    // 5. Process Every Scheduling Request
	    for (String[] request : schedulingRequests) {String courseID = request[0]; String lecturerID = request[1]; String studentGroupID = request[2];

	        // Find Course Information
	        Course selectedCourse = null;

	        for (Course course : courses) {

	            if (course.getCourseID() .equalsIgnoreCase(courseID)) {

	                selectedCourse = course;
	                break;
	            }
	        }

	        // ======================================
	        // Find Student Group Information
	        // ======================================

	        StudentGroup selectedGroup = null;

	        for (StudentGroup group : studentGroups) {

	            if (group.getStudentGroupID().equalsIgnoreCase(studentGroupID)) {

	                selectedGroup = group;
	                break;
	            }
	        }

	        // Safety Check
	        if (selectedCourse == null || selectedGroup == null) {

	            failedCount++;

	            failedResults.add(courseID + " - Invalid Course or Student Group.");

	            continue;
	        }

	        String courseType = selectedCourse.getCourseType();
	        int groupSize = selectedGroup.getStudentGroupSize();

	        // 6. Find Suitable Rooms
	        ArrayList<String> suitableRooms = new ArrayList<>();

	        if (courseType.equalsIgnoreCase("Lecture")) {

	            for (Classroom classroom : classrooms) {

	                if (classroom.getClassroomCapacity() >= groupSize) {

	                    suitableRooms.add(
	                        classroom.getClassroomID()
	                    );
	                }
	            }

	        } else if (courseType.equalsIgnoreCase("Practical")) {

	            for (Laboratory laboratory : laboratories) {

	                if (laboratory.getLabCapacity() >= groupSize) {

	                    suitableRooms.add(
	                        laboratory.getLabID()
	                    );
	                }
	            }
	        }

	        // No Suitable Room
	        if (suitableRooms.isEmpty()) {

	            failedCount++;

	            failedResults.add(courseID + " - No Suitable Room Available." );

	            continue;
	        }

	        // 7. Automatic Scheduling
	        boolean generated = false;

	        for (String day : days) {

	            if (generated) {
	                break;
	            }

	            for (LocalTime startTime : timeSlots) {

	                if (generated) {
	                    break;
	                }

	                LocalTime endTime = startTime.plusHours(2);

	                for (String roomID : suitableRooms) {

	                    boolean conflict = false;

	                    // 8. Scheduling Conflict Validation
	                    for (ClassSession session : classSessions) {

	                        // Check Same Day
	                        if (session.getDay().equalsIgnoreCase(day)) {

	                            LocalTime existingStart =LocalTime.parse(session.getStartTime());

	                            LocalTime existingEnd = LocalTime.parse(session.getEndTime());

	                            // Check Time Overlap
	                            boolean overlap = startTime.isBefore(existingEnd) && endTime.isAfter(existingStart);

	                            if (overlap) {

	                                // Lecturer Conflict
	                                boolean lecturerConflict =session.getLecturerID().equalsIgnoreCase(lecturerID);

	                                // Student Group Conflict
	                                boolean groupConflict = session.getStudentGroupID().equalsIgnoreCase(studentGroupID);

	                                // Room Conflict
	                                boolean roomConflict = session.getRoomID().equalsIgnoreCase(roomID);

	                                if (lecturerConflict || groupConflict || roomConflict) {

	                                    conflict = true;
	                                    break;
	                                }
	                            }
	                        }
	                    }

	                    // 9. Generate Class Session
	                    if (!conflict) {

	                        // Generate Unique Session ID
	                        int sessionNumber = 1;
	                        String sessionID;

	                        boolean duplicateID;

	                        do {

	                            sessionID = String.format("S%03d",sessionNumber );

	                            duplicateID = false;

	                            for (ClassSession session : classSessions) {

	                                if (session.getSessionID().equalsIgnoreCase(sessionID)) {

	                                    duplicateID = true;
	                                    break;
	                                }
	                            }

	                            if (duplicateID) {
	                            	sessionNumber++;
	                            	
	                            }

	                        } while (duplicateID);


	                        // Create Class Session Object
	                        ClassSession generatedSession =
	                                new ClassSession(
	                                    sessionID,
	                                    courseID,
	                                    lecturerID,
	                                    studentGroupID,
	                                    day,
	                                    startTime.toString(),
	                                    endTime.toString(),
	                                    roomID
	                                );

	                        // Save to ArrayList
	                        classSessions.add(generatedSession);

	                        generated = true;
	                        successCount++;

	                        // Save Success Result
	                        String result = sessionID + " - " + courseID + " - " + day + " " + startTime + "-" + endTime + " - " + roomID;

	                        successResults.add(result);

	                        System.out.println("Generated Successfully: " + result);

	                        break;
	                    }

	                } // End Room Loop

	            } // End Time Loop

	        } // End Day Loop

	        // 10. Generation Failed
	        if (!generated) {

	            failedCount++;

	            String result = courseID + " - No Suitable Day, Time or Room Available.";
	            failedResults.add(result);

	            System.out.println("Generation Failed: " + result);
	        }

	    } // End Scheduling Request Loop

	    // 11. Display Generation Summary
	    System.out.println("\n========== TIMETABLE GENERATION SUMMARY ==========");

	    System.out.println("Total Requests: " + totalRequests);

	    System.out.println("Successfully Generated: " + successCount);

	    System.out.println("Failed: " + failedCount);

	    // Display Successful Sessions
	    System.out.println("\n========== SUCCESSFUL SESSIONS ==========");

	    if (successResults.isEmpty()) {

	        System.out.println("No Class Sessions Generated.");

	    } 
	    
	    else {

	        for (String result : successResults) {

	            System.out.println(result);
	        }
	    }

	    // Display Failed Sessions
	    System.out.println("\n========== FAILED SESSIONS ==========");

	    if (failedResults.isEmpty()) {

	        System.out.println("No Failed Scheduling Requests.");

	    }
	    
	    else {

	        for (String result : failedResults) {

	            System.out.println(result);
	        }
	    }

	    System.out.println("==================================================");

	} // End generateTimetable
	
	// View Generated Timetable
	public static void viewGeneratedTimetable(ArrayList<ClassSession> classSessions) {

	    // Check Empty Timetable
	    if (classSessions.isEmpty()) {

	        System.out.println("No Class Sessions Available.");
	        return;
	    }

	    System.out.println("\n========== GENERATED TIMETABLE ==========");

	    String[] days = {"Monday","Tuesday","Wednesday","Thursday","Friday"};

	    // Display Timetable by Day
	    for (String day : days) {

	        System.out.println("\n========== " + day + " ==========");

	        boolean found = false;

	        // Display Sessions in Time Order
	        ArrayList<ClassSession> dailySessions = new ArrayList<>();

	        for (ClassSession session : classSessions) {

	            if (session.getDay().equalsIgnoreCase(day)) {

	                dailySessions.add(session);
	            }
	        }

	        // Sort by Start Time
	        dailySessions.sort((session1, session2) ->LocalTime.parse(session1.getStartTime()).compareTo(LocalTime.parse(session2.getStartTime())));

	        for (ClassSession session : dailySessions) {

	            found = true;

	            System.out.println("Session ID: " + session.getSessionID());
	            System.out.println("Time: " + session.getStartTime() + " - " + session.getEndTime());
	            System.out.println("Course ID: " + session.getCourseID());
	            System.out.println("Lecturer ID: " + session.getLecturerID());
	            System.out.println("Student Group ID: " + session.getStudentGroupID());
	            System.out.println("Room ID: " + session.getRoomID());
	            System.out.println("---------------------------");
	        }

	        if (!found) {

	            System.out.println("No Class Scheduled.");
	        }
	    }

	    System.out.println("\nTotal Class Sessions: " + classSessions.size());

	} // End viewGeneratedTimetable
} // End of Main Class


