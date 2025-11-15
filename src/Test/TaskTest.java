package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import Task.Task;

class TaskTest {

	@Test
    void testSuccessfulGetters() {
        Task task = new Task("1234", "Name", "Description");
        
        assertEquals("1234", task.getTaskID());
        assertEquals("Name", task.getName());
        assertEquals("Description", task.getDescription());
    }
	
	@Test
    void testSuccessfulSetters() {
        Task task = new Task("1234", "Name", "Description");
        
        task.setName("Updated Name");
        task.setDescription("Updated Description");
        
        assertEquals("Updated Name", task.getName());
        assertEquals("Updated Description", task.getDescription());
	}
	
	@Test
	void testMaxLength() {
		Task task = new Task("1234567890", "Name1234567890123456", "Description123456789012345678901234567890123456789");
        
        assertEquals("1234567890", task.getTaskID()); //Exactly 10 characters
        assertEquals("Name1234567890123456", task.getName()); //Exactly 20 characters
        assertEquals("Description123456789012345678901234567890123456789", task.getDescription()); //Exactly 50 characters
	}
	
	@Test
	void testMinLength() {
		Task task = new Task("1", "N", "D"); //Minimum of 1 Character Each
		
		assertEquals("1", task.getTaskID()); 
		assertEquals("N", task.getName());
		assertEquals("D", task.getDescription());
	}
	
	@ParameterizedTest
    @CsvSource({
        "12345678901, Name, Description", 										// TaskID too long
        "1234567890, Name12345678901234567, Description", 						// Name too long
        "1234567890, Name, Description1234567890123456789012345678901234567890" // Description too long
    })
    void testLongLengths(String taskID, String name, String description) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(taskID, name, description);
        });
    }
	
   
    @ParameterizedTest
    @CsvSource({
        " '', Name, Description",		// Empty taskID
        "1234567890, '', Description", // Empty Name
        "1234567890, Name, ''" 		// Empty Description
    })
    void testEmptyFields (String taskID, String name, String description) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Task(taskID, name, description);
        });
    }
    
    @Test
    void testInvalidLengthNameSetters() {
    	Task task = new Task("1234", "Name", "Description");
        
    	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            task.setName("Name12345678901234567"); // Exceeds Maximum Characters 
        });

        assertEquals("Name must not be null or empty, and must be 20 characters or less.", exception.getMessage());
    }
    
    @Test
    void testInvalidEmptyNameSetters() {
    	Task task = new Task("1234", "Name", "Description");
        
    	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            task.setName(""); // Empty Name
        });

        assertEquals("Name must not be null or empty, and must be 20 characters or less.", exception.getMessage());
    }
    
    @Test
    void testInvalidLengthDescriptionSetters() {
    	Task task = new Task("1234", "Name", "Description");
        
    	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription("Description1234567890123456789012345678901234567890"); // Exceeds Maximum Characters
        });

        assertEquals("Description must not be null or empty, and must be 50 characters or less.", exception.getMessage());
    }
    
    @Test
    void testInvalidEmptyDescriptionSetters() {
    	Task task = new Task("1234", "Name", "Description");
        
    	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(""); // Empty Description
        });

        assertEquals("Description must not be null or empty, and must be 50 characters or less.", exception.getMessage());
    }
}
