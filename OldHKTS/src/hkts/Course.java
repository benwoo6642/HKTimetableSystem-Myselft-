package hkts;

public class Course {
	// Attribute
	private String courseID; 
	private String courseName;
	private int courseCredit;
	private String courseType;
	 //Constructor
	public Course (String id, String name, int credit, String type) { 
		
		courseID = id;
		courseName = name;
		courseType = type;
		courseCredit = credit;
	}
	// setter method
	public void setCourseName (String name) { 
		
		courseName = name;
	}
	
	public void setCourseCredit (int credit) {
		
		courseCredit = credit;
	}
	
	public void setCourseType (String type) {
		
		courseType = type;
	}
	// get method
	public String getCourseID() { 
		
		return courseID;
	}
	
	public String getCourseName() {
		
		return courseName;
	}
	
	public int getCourseCredit() {
		
		return courseCredit;
	}
	
	public String getCourseType() {
		
		return courseType;
	}
} // End of public class Course
