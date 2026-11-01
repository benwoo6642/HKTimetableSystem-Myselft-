package hkts;

public class Laboratory {
	
	// Attribute
	private String labID;
	private String labName;
	private int labCapacity;
	private String labLocation;
	private String labSpecification;
	
	// Constructor
	public Laboratory (String id, String name, int capacity, String location, String specification ) {
		
		labID = id;
		labName = name;
		labCapacity = capacity;
		labLocation = location;
		labSpecification = specification;
	}
	
	// Getter Method
	public String getLabID () {
		
		return labID;
	}
	
	public String getLabName () {
		
		return labName;
	}
	
	public int getLabCapacity () {
		
		return labCapacity;
	}
	
	public String getLabLocation () {
		
		return labLocation;
	}
	
	public String getLabSpecification () {
		
		return labSpecification;
	}
	
	// Setter Method
	public void setLabName (String name) {
		
		labName = name;
	}
	public void setLabCapacity (int capacity) {
		
		labCapacity = capacity;
	}
	public void setLabLocation (String location) {
		
		labLocation = location;
	}
	public void setLabSpecification (String specification) {
		
		labSpecification = specification;
	}

} 
