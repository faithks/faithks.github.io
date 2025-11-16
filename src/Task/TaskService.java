package Task;

import java.util.HashMap;
import java.util.Map;
import java.io.*;
import Appointment.AppointmentService;

public class TaskService {
    private final Map<String, Task> tasks = new HashMap<>();
    private final String FILE_PATH = "tasks.csv";
    private final AppointmentService appointmentService;
    
    public TaskService(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    	loadFromFile();
    }

    // Method to add a new task
    public void addTask(Task task) {
    	// Validate Task
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        if (tasks.containsKey(task.getTaskID())) {
            throw new IllegalArgumentException("Task ID already exists.");
        }
        if (appointmentService.getAppointment(task.getAppointmentID()) == null) {
            throw new IllegalArgumentException("Appointment ID does not exist. Task must be associated with a valid appointment.");
        }
        // Adding
        tasks.put(task.getTaskID(), task);
        saveToFile();
    }

    // Method to delete a task by Task ID
    public void deleteTask(String taskID) {
    	// Search by taskID
        if (taskID == null || !tasks.containsKey(taskID)) {
            throw new IllegalArgumentException("No task found with ID");
        }
        // Removing task
        tasks.remove(taskID);
        saveToFile();
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
        
        saveToFile();
    }
    
    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Task t : tasks.values()) {
            	pw.println(t.getTaskID() + "," +
            				t.getName() + "," +
            				t.getDescription() + "," +
            				t.getAppointmentID());
            }
        } catch (IOException e) {
            System.out.println("\u001B[31mError saving tasks: " + e.getMessage() + "\u001B[0m");
        }
    }

    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",", 4);
                if (parts.length == 4) {
                    Task t = new Task(parts[0], parts[1], parts[2], parts[3]);
                    tasks.put(t.getTaskID(), t);
                }
            }
        } catch (IOException e) {
            System.out.println("\u001B[31mError loading tasks: " + e.getMessage() + "\u001B[0m");
        }
    }
    
    // Method to retrieve a task by ID for testing
    public Task getTask(String taskID) {
        return tasks.get(taskID); // Returns null if not found
    }
}

