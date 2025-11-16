package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Appointment.Appointment;
import Appointment.AppointmentService;
import Contact.Contact;
import Contact.ContactService;
import Task.Task;
import Task.TaskService;

class TaskServiceTest{
    private TaskService taskService;
    private AppointmentService appointmentService;
    private ContactService contactService;

    // Method to get a future date
    private Date getFutureDate(int daysAhead) {
        Calendar cal = Calendar.getInstance();
        // Adds the number of days to current date
        cal.add(Calendar.DATE, daysAhead); 
        return cal.getTime();
    }
    
    @BeforeEach
    void setUp() {
    	// Clear persisted files
        new File("contacts.csv").delete();
        new File("appointments.csv").delete();
        new File("tasks.csv").delete();
        
    	contactService = new ContactService();
        contactService.addContact(new Contact("C123", "John", "Doe", "1234567890", "123 Main St"));
        
        appointmentService = new AppointmentService(contactService);
        
        Date futureDate = getFutureDate(1);
        appointmentService.addAppointment(new Appointment("A123", futureDate, "Description", "C123"));
    	
        taskService = new TaskService (appointmentService);
    }

    @Test
    void testAddTask() {
        Task task = new Task("1234", "Name", "Description", "A123");
        taskService.addTask(task);
        
     // Verify that the task was added successfully
        Task retrieved = taskService.getTask("1234");
        assertEquals(task, retrieved);
    }

    @Test
    void testAddDuplicateTask() {
        Task task1 = new Task("1234", "Name", "Description", "A123");
        taskService.addTask(task1);

        // Same taskID
        Task task2 = new Task("1234", "NameAgain", "DescriptionAgain", "A123"); 
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.addTask(task2);
        });
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("12345", "Name", "Description", "A123");
        taskService.addTask(task);
        taskService.deleteTask("12345");
    }

    @Test
    void testUpdateTask() {
        Task task = new Task("123", "Name", "Description", "A123");
        taskService.addTask(task);
        
        Task updatedTask = new Task("123", "UpdatedName", "UpdatedDescription", "A123");
        taskService.update("123", updatedTask);
        
        // Test update was successful
        Task result = taskService.getTask("123");
        assertEquals("UpdatedName", result.getName());
        assertEquals("UpdatedDescription", result.getDescription());
        assertEquals("A123", result.getAppointmentID());

    }
    
    @Test
    void testAddNullTask() {
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.addTask(null);
        });
    }

    @Test
    void testDeleteNonExistentTask() {
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.deleteTask("5555");
        });
    }

    @Test
    void testUpdateNonExistentTask() {
        Task updatedTask = new Task("5555", "UpdatedName", "UpdatedDescription", "A123");
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.update("5555", updatedTask);
        });
    }

    @Test
    void testAddMultipleTask() {
        Task task1 = new Task("1", "Name", "Description", "A123");
        Task task2 = new Task("2", "Name2", "Description2", "A123");
        taskService.addTask(task1);
        taskService.addTask(task2);
        
        //test both tasks were added
        assertEquals(task1, taskService.getTask("1"));
        assertEquals(task2, taskService.getTask("2"));

    }
    
    @Test
    void testTaskCreationAndRetrieval() {
        Task task = new Task("246", "Name", "Description", "A123");
        taskService.addTask(task);

        // Test the integration between Task and TaskService
        Task retrievedTask = taskService.getTask("246");
        assertNotNull(retrievedTask);
        assertEquals("246", retrievedTask.getTaskID());
        assertEquals("Name", retrievedTask.getName());
        assertEquals("Description", retrievedTask.getDescription());
        assertEquals("A123", retrievedTask.getAppointmentID());
    }
    
    @Test
    void testAddAndDeleteMultipleTasks () {
        Task task1 = new Task("1234", "Name", "Description", "A123");
        Task task2 = new Task("4321", "Name2", "Description2", "A123");
        
    	// Add multiple tasks
        taskService.addTask(task1);
        taskService.addTask(task2);
        
        // Ensure both were added
        assertEquals(task1, taskService.getTask("1234"));
        assertEquals(task2, taskService.getTask("4321"));
        
        // Delete one task
        taskService.deleteTask("1234");
        
        // Ensure task one was deleted and task two remains
        assertNull(taskService.getTask("1234"));
        assertEquals(task2, taskService.getTask("4321"));
        
        // Delete the second task
        taskService.deleteTask("4321");
        
        // Ensure both were deleted
        assertNull(taskService.getTask("1234"));
        assertNull(taskService.getTask("4321"));
    }
    
    @Test
    void testAddTaskWithNonExistentAppointment() {
        // Attempt to add a task with an appointment ID that does not exist
        Task task = new Task("1234", "Name", "Description", "0000");

        assertThrows(IllegalArgumentException.class, () -> {
            taskService.addTask(task);
        });
    }
    
    @Test
    void testSaveToFileAndLoadFromFile() {
        // Add tasks
        Task task = new Task("1234", "Name", "Description", "A123");
        
        taskService.addTask(task);
        
        // Create a new instance to trigger loadFromFile()
        TaskService loadedService = new TaskService(appointmentService);

        // Verify tasks were properly loaded
        assertNotNull(loadedService.getTask("1234"));

        assertEquals("Name", loadedService.getTask("1234").getName());
    }

}
