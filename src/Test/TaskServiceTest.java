package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Task.Task;
import Task.TaskService;

class TaskServiceTest{
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService ();
    }

    @Test
    void testAddTask() {
        Task task = new Task("1234", "Name", "Description");
        taskService.addTask(task);
        
     // Verify that the task was added successfully
        Task retrieved = taskService.getTask("1234");
        assertEquals(task, retrieved);
    }

    @Test
    void testAddDuplicateTask() {
        Task task1 = new Task("1234", "Name", "Description");
        taskService.addTask(task1);

        Task task2 = new Task("1234", "NameAgain", "DescriptionAgain"); //Same taskID
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.addTask(task2);
        });
    }

    @Test
    void testDeleteTask() {
        Task task = new Task("12345", "Name", "Description");
        taskService.addTask(task);
        taskService.deleteTask("12345");
    }

    @Test
    void testUpdateTask() {
        Task task = new Task("123", "Name", "Description");
        taskService.addTask(task);
        
        Task updatedTask = new Task("123", "UpdatedName", "UpdatedDescription");
        taskService.update("123", updatedTask);
        
        //Test update was successful
        Task result = taskService.getTask("123");
        assertEquals("UpdatedName", result.getName());
        assertEquals("UpdatedDescription", result.getDescription());

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
        Task updatedTask = new Task("5555", "UpdatedName", "UpdatedDescription");
        assertThrows(IllegalArgumentException.class, () -> {
            taskService.update("5555", updatedTask);
        });
    }

    @Test
    void testAddMultipleTask() {
        Task task1 = new Task("1", "Name", "Description");
        Task task2 = new Task("2", "Name2", "Description2");
        taskService.addTask(task1);
        taskService.addTask(task2);
        
        //test both tasks were added
        assertEquals(task1, taskService.getTask("1"));
        assertEquals(task2, taskService.getTask("2"));

    }
    
    @Test
    void testTaskCreationAndRetrieval() {
        Task task = new Task("246", "Name", "Description");
        taskService.addTask(task);

        //test the integration between Task and TaskService
        Task retrievedTask = taskService.getTask("246");
        assertNotNull(retrievedTask);
        assertEquals("246", retrievedTask.getTaskID());
        assertEquals("Name", retrievedTask.getName());
        assertEquals("Description", retrievedTask.getDescription());
    }
    
    @Test
    void testAddAndDeleteMultipleTasks () {
        Task task1 = new Task("1234", "Name", "Description");
        Task task2 = new Task("4321", "Name2", "Description2");
        
    	//add multiple tasks
        taskService.addTask(task1);
        taskService.addTask(task2);
        
        //Ensure both were added
        assertEquals(task1, taskService.getTask("1234"));
        assertEquals(task2, taskService.getTask("4321"));
        
        //Delete one task
        taskService.deleteTask("1234");
        
        //Ensure task one was deleted and task two remains
        assertNull(taskService.getTask("1234"));
        assertEquals(task2, taskService.getTask("4321"));
        
        //Delete the second task
        taskService.deleteTask("4321");
        
        //Ensure both were deleted
        assertNull(taskService.getTask("1234"));
        assertNull(taskService.getTask("4321"));
    }

}
