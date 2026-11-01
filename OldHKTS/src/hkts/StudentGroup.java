package hkts;

public class StudentGroup {
	// Attributes
	private String studentGroupID;
	private String studentGroupName;
	private String studentGroupIntakeTime;
	private String studentGroupDepartment;
	private int studentGroupSize;
	// Constructor
	public StudentGroup (String id, String name, String intakeTime, String department, int size) {
		
		studentGroupID = id;
		studentGroupName = name;
		studentGroupIntakeTime = intakeTime;
		studentGroupDepartment = department;
		studentGroupSize = size;
	}
	// Getter Method
	public String getStudentGroupID() {
		
		return studentGroupID;
	}
	
	public String getStudentGroupName() {
		
		return studentGroupName;
	}
	
	public String getStudentGroupIntakeTime() {
		
		return studentGroupIntakeTime;
	}
	
	public String getStudentGroupDepartment() {
		
		return studentGroupDepartment;
	}
	
	public int getStudentGroupSize() {
		
		return studentGroupSize;
	}
	// Setter Method
	public void setStudentGroupName(String name) {
		
		studentGroupName = name;
	}
	
	public void setStudentGroupIntakeTime(String intakeTime) {
		
		studentGroupIntakeTime = intakeTime;
	}
	
	public void setStudentGroupDepartment(String department) {
		
		studentGroupDepartment = department;
	}
	
	public void setStudentGroupSize(int size) {
		
		studentGroupSize = size;
	}
	
} // End StudentGroup
