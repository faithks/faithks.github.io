package Test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import Appointment.Appointment;

import java.util.Date;
import java.util.Calendar;

class AppointmentTest {

    // Method to get a future date
    private Date getFutureDate(int daysAhead) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, daysAhead); // Adds the number of days to current date
        return cal.getTime();
    }

    // Method to get a past date
    private Date getPastDate(int daysAgo) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -daysAgo); // Subtracts the number of days from current date
        return cal.getTime();
    }

    @Test
    void testSuccessfulGetters() {
        Date futureDate = getFutureDate(1); // Date 1 day in the future
        Appointment appointment = new Appointment("123", futureDate, "Description", "C123");

        assertEquals("123", appointment.getAppointmentID());
        assertEquals(futureDate, appointment.getDate());
        assertEquals("Description", appointment.getDescription());
        assertEquals("C123", appointment.getContactID());

    }

    @Test
    void testSuccessfulSetters() {
        Date futureDate = getFutureDate(1);
        Appointment appointment = new Appointment("123", futureDate, "Description", "C123");

        appointment.setDescription("Updated Description");

        assertEquals("Updated Description", appointment.getDescription());
    }

    @Test
    void testMaxLength() {
        Date futureDate = getFutureDate(1);
        Appointment appointment = new Appointment("1234567890", futureDate, "Description123456789012345678901234567890123456789", "Contact123");

        assertEquals("1234567890", appointment.getAppointmentID()); // Max Length of 10
        assertEquals("Description123456789012345678901234567890123456789", appointment.getDescription()); // Max Length of 50
        assertEquals("Contact123", appointment.getContactID()); // Max length of 10
    }

    @Test
    void testMinLength() {
        Date futureDate = getFutureDate(1);
        Appointment appointment = new Appointment("1", futureDate, "D", "1"); // Minimum length of 1

        assertEquals("1", appointment.getAppointmentID());
        assertEquals("D", appointment.getDescription());
        assertEquals("1", appointment.getContactID());
    }

    @Test
    void testAppointmentIDExceedsMaxLength() {
        Date futureDate = getFutureDate(1);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("12345678901", futureDate, "Description", "C456"); // ID is 11 characters
        });
        
        assertEquals("Appointment ID must not be null, empty, and must be 10 characters or less.", exception.getMessage());
    }


    @Test
    void testDescriptionExceedsMaxLength() {
    	Date futureDate = getFutureDate(1);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", futureDate, "Description1234567890123456789012345678901234567890", "C456"); // Description 51 characters
        });
        
        assertEquals("The appointment�s description must not be null, empty, and must be 50 characters or less.", exception.getMessage());
    }
        

    @Test
    void testAppointmentIDIsNull() {
    	Date futureDate = getFutureDate(1);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("", futureDate, "Description", "C456"); // Empty Appointment ID
        });
        
        assertEquals("Appointment ID must not be null, empty, and must be 10 characters or less.", exception.getMessage());
    }

    @Test
    void testDescriptionIsNull() {
    	Date futureDate = getFutureDate(1);
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", futureDate, "", "C456"); // Empty Description
        });
        
        assertEquals("The appointment�s description must not be null, empty, and must be 50 characters or less.", exception.getMessage());
    }
    
    @Test
    void testPastDate() {
        Date pastDate = getPastDate(1); // A date 1 day in the past

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234567890", pastDate, "Description", "C456");
        });
        
        assertEquals("Appointment date cannot be in the past.", exception.getMessage());
    }
    
    @Test
    void testInvalidSetterLength() {
        Date futureDate = getFutureDate(1);
        Appointment appointment = new Appointment("123", futureDate, "Description", "C456");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDescription("Description1234567890123456789012345678901234567890"); // Exceeds 50 characters
        });

        assertEquals("The appointment�s description must not be null, empty, and must be 50 characters or less.", exception.getMessage());
    }
    
    @Test
    void testEmptySetter() {
    	Date futureDate = getFutureDate(1);
        Appointment appointment = new Appointment("123", futureDate, "Description", "C456");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDescription(""); // Empty description
        });

        assertEquals("The appointment�s description must not be null, empty, and must be 50 characters or less.", exception.getMessage());
    }

}
