package Task;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<String, Task> tasks = new HashMap<>();

    // Method to add a new task
    public void addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        if (tasks.containsKey(task.getTaskID())) {
            throw new IllegalArgumentException("Task ID already exists.");
        }
        //adding
        tasks.put(task.getTaskID(), task);
    }

    // Method to delete a task by Task ID
    public void deleteTask(String taskID) {
        if (taskID == null || !tasks.containsKey(taskID)) {
            throw new IllegalArgumentException("No task found with ID");
        }
        //removing
        tasks.remove(taskID);
    }

    // Method to update a Task's fields
    public void update(String taskID, Task updated) {
        if (taskID == null || updated == null) {
            throw new IllegalArgumentException("Task ID and/or updated task cannot be null.");
        }
        Task existingTask = tasks.get(taskID);
        if (existingTask == null) {
            throw new IllegalArgumentException("No task found with ID.");
        }

        // Updating fields 
        existingTask.setName(updated.getName());
        existingTask.setDescription(updated.getDescription());
    }
    
    // Method to retrieve a task by ID for testing
    public Task getTask(String taskID) {
        return tasks.get(taskID); // Returns null if not found
    }
}

