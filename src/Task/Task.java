package Task;

public class Task {
	private final String taskID; // Cannot be modified after initialization
	private String name;
	private String description;
	private final String appointmentID;
	
	// Constructor with validation
	public Task(String taskID, String name, String description, String appointmentID) {
		// Validate taskID 
		if (taskID == null || taskID.isEmpty() || taskID.length() > 10) {
	            throw new IllegalArgumentException("Task ID must not be null, empty, and must be 10 characters or less.");
	        }
		// Validate name
	     if (name == null || name.isEmpty() || name.length() > 20) {
	            throw new IllegalArgumentException("Name must not be null, empty, and must be 20 characters or less.");
	        }
	     // Validate description
	     if (description == null || description.isEmpty() || description.length() > 50) {
	            throw new IllegalArgumentException("Description must not be null, empty, and must be 50 characters or less.");
	        }
	     // Validate appointmentID
	     if (appointmentID == null || appointmentID.isEmpty()) {
	    	 throw new IllegalArgumentException("Task must be associated with an appointment.");
	     }
	        
		this.taskID = taskID;
		this.name = name;
		this.description = description;
		this.appointmentID = appointmentID;
	}

	//Getters
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getTaskID() {
		return taskID;
	}
	
	public String getAppointmentID() {
		return appointmentID;
	}

	
	//Setters with validation
	public void setName(String name) {
		if (name == null || name.isEmpty() || name.length() > 20) {
            throw new IllegalArgumentException("Name must not be null or empty, and must be 20 characters or less.");
        }
		this.name = name;
	}

	public void setDescription(String description) {
		if (description == null || description.isEmpty() || description.length() > 50) {
            throw new IllegalArgumentException("Description must not be null or empty, and must be 50 characters or less.");
        }
		this.description = description;
	}
	
	// No setter for taskID or appointmentID to keep them immutable
}
