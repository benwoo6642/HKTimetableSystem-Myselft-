package hkts;

public class Lecturer {
	
	// Attribute
	private String lecturerID; 
	private String lecturerName;
	private String lecturerEmail;
	private String lecturerQualification;
	
	// Constructor
	public Lecturer (String id, String name, String email, String qualification) { 
		
		lecturerID = id;
		lecturerName = name;
		lecturerEmail = email;
		lecturerQualification = qualification;
	}
	
	// get Method
	public String getLecturerID () { 
		
		return lecturerID; // Result give back to caller (return)
	}
	
	public String getLecturerName () {
		
		return lecturerName;
	}
	
	public String getLecturerEmail () {
		
		return lecturerEmail;
	}
	
	public String getLecturerQualification () {
		
		return lecturerQualification;
	}
	
	// setter method
	public void setLecturerName (String name) { 
		
		lecturerName = name;
	}
	
	public void setLecturerEmail (String email) {
		
		lecturerEmail = email;
	}
	
	public void setLecturerQualification (String qualification) {
		
		lecturerQualification = qualification;
	}
}
	
