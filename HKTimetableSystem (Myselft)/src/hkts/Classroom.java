package hkts;

public class Classroom {
	
	// Attribute
	private String classroomID;
	private String classroomName;
	private int classroomCapacity;
	private String classroomLocation;
	
	// Constructor
	public Classroom (String id, String name, int capacity, String location) {
		
		classroomID = id;
		classroomName = name;
		classroomCapacity = capacity;
		classroomLocation = location;
	}
	
	// Getter Method
	public String getClassroomID() {
		
		return classroomID;
	}
	
	public String getClassroomName() {
		
		return classroomName;
	}
	
	public int getClassroomCapacity() {
		
		return classroomCapacity;
	}
	
	public String getClassroomLocation() {
		
		return classroomLocation;
	}
	
	// Setter Method
	public void setClassroomName (String name) {
		
		classroomName = name;
	}
	
	public void setClassroomCapacity (int capacity) {
		
		classroomCapacity = capacity;
	}
	
	public void setClassroomLocation (String location) {
		
		classroomLocation = location;
	}
	
} 