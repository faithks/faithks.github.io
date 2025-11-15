package Task;

public class Task {
	private final String taskID;
	private String name;
	private String description;
	
	// Constructor with validation
	public Task(String taskID, String name, String description) {
		 if (taskID == null || taskID.isEmpty() || taskID.length() > 10) {
	            throw new IllegalArgumentException("Task ID must not be null, empty, and must be 10 characters or less.");
	        }
	        if (name == null || name.isEmpty() || name.length() > 20) {
	            throw new IllegalArgumentException("Name must not be null, empty, and must be 20 characters or less.");
	        }
	        if (description == null || description.isEmpty() || description.length() > 50) {
	            throw new IllegalArgumentException("Description must not be null, empty, and must be 50 characters or less.");
	        }
		this.taskID = taskID;
		this.name = name;
		this.description = description;
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

	
	//Setters
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

}
