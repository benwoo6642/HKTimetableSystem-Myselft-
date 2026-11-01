package hkts;

public class ClassSession {
	
	// Attribute
	private String sessionID;
	private String courseID;
	private String lecturerID;
	private String studentGroupID;
	private String day;
	private String startTime;
	private String endTime;
	private String roomID;
	
	// Constructor
	public ClassSession (String id, String course, String lecturer, String group, String sessionDay, String start, String end, String room) {
		
		sessionID = id;
		courseID = course;
		lecturerID = lecturer;
		studentGroupID = group;
		day = sessionDay;
		startTime = start;
		endTime = end;
		roomID = room;
	}
	
	// Getter Method
	public String getSessionID () {
		
		return sessionID;
	}
	
	public String getCourseID () {
		
		return courseID;
	}
	
	public String getLecturerID () {
		
		return lecturerID;
	}
	
	public String getStudentGroupID () {
		
		return studentGroupID;
	}
	
	public String getDay () {
		
		return day;
	}
	
	public String getStartTime () {
		
		return startTime;
	}
	
	public String getEndTime () {
		
		return endTime;
	}
	
	public String getRoomID () {
		
		return roomID;
	}
	
	// Setter Method
	public void setCourseID (String course) {
		
		courseID = course;
	}
	
	public void setLecturerID (String lecturer) {
		
		lecturerID = lecturer;
	}
	
	public void setStudentGroupID (String group) {
		
		studentGroupID = group;
	}
	
	public void setDay (String sessionDay) {
		
		day = sessionDay;
	}
	
	public void setStartTime (String start) {
		
		startTime = start;
	}
	
	public void setEndTime (String end) {
		
		endTime = end;
	}
	
	public void setRoomID (String room) {
		
		roomID = room;
	}
	
} 
