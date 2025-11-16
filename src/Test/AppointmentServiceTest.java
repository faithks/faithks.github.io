package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Appointment.Appointment;
import Appointment.AppointmentService;
import Contact.Contact;
import Contact.ContactService;

import java.util.Date;
import java.io.File;
import java.util.Calendar;

class AppointmentServiceTest {
    private AppointmentService appointmentService;
    private ContactService contactService;

 // Method to get a future date
    private Date getFutureDate(int daysAhead) {
        Calendar cal = Calendar.getInstance();
        // Adds the number of days to current date
        cal.add(Calendar.DATE, daysAhead);
        return cal.getTime();
    }
    
 // Method to get a past date
    private Date getPastDate(int daysAgo) {
        Calendar cal = Calendar.getInstance();
        // Subtracts the number of days from current date
        cal.add(Calendar.DATE, -daysAgo);
        return cal.getTime();
    }

    @BeforeEach
    void setUp() {
    	// Clear persisted files
        new File("contacts.csv").delete();
        new File("appointments.csv").delete();
        
    	contactService = new ContactService();
        contactService.addContact(new Contact("C123", "John", "Doe", "1234567890", "123 Main St"));
        
        // Create contact to attach to appointment
        appointmentService = new AppointmentService(contactService);
    }

    @Test
    void testAddAppointment() {
        Date futureDate = getFutureDate(1);
        Appointment appointment = new Appointment("1234", futureDate, "Description", "C123");
        appointmentService.addAppointment(appointment);

        // Verify that the appointment was added successfully
        Appointment retrieved = appointmentService.getAppointment("1234");
        assertEquals(appointment, retrieved);
    }

    @Test
    void testAddDuplicateAppointment() {
        Date futureDate = getFutureDate(1);
        Appointment appointment1 = new Appointment("1234", futureDate, "Description", "C123");
        appointmentService.addAppointment(appointment1);

        // Attempting to add duplicate appointment ID
        Appointment appointment2 = new Appointment("1234", futureDate, "Description 2", "C123"); // Same ID
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment(appointment2);
        });
    }

    @Test
    void testDeleteAppointment() {
        Date futureDate = getFutureDate(1);
        Appointment appointment = new Appointment("12345", futureDate, "Description", "C123");
        appointmentService.addAppointment(appointment);
        
        // Deleting the appointment
        appointmentService.deleteAppointment("12345");

        // Verify that the appointment was deleted
        assertNull(appointmentService.getAppointment("12345"));
    }

    @Test
    void testDeleteNonExistentAppointment() {
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.deleteAppointment("9999");
        });
    }

    @Test
    void testAddNullAppointment() {
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment(null);
        });
    }

    @Test
    void testAddMultipleAppointments() {
        Date futureDate = getFutureDate(1);
        Appointment appointment1 = new Appointment("1", futureDate, "First Description", "C123");
        Appointment appointment2 = new Appointment("2", futureDate, "Second Description", "C123");

        appointmentService.addAppointment(appointment1);
        appointmentService.addAppointment(appointment2);

        // Verify both appointments were added
        assertEquals(appointment1, appointmentService.getAppointment("1"));
        assertEquals(appointment2, appointmentService.getAppointment("2"));
    }

    @Test
    void testAppointmentCreationAndRetrieval() {
        Date futureDate = getFutureDate(1);
        Appointment appointment = new Appointment("246", futureDate, "Description", "C123");
        appointmentService.addAppointment(appointment);

        // Test the integration between Appointment and AppointmentService
        Appointment retrievedAppointment = appointmentService.getAppointment("246");
        assertNotNull(retrievedAppointment);
        assertEquals("246", retrievedAppointment.getAppointmentID());
        assertEquals("Description", retrievedAppointment.getDescription());
        assertEquals(futureDate, retrievedAppointment.getDate());
        assertEquals("C123", retrievedAppointment.getContactID());
    }
    
    @Test
    void testAddAndDeleteMultipleAppointments() {
        Date futureDate = getFutureDate(1);
        Appointment appointment1 = new Appointment("1", futureDate, "Description 1", "C123");
        Appointment appointment2 = new Appointment("2", futureDate, "Description 2", "C123");
        
        // Add both appointments
        appointmentService.addAppointment(appointment1);
        appointmentService.addAppointment(appointment2);

        // Ensure both are added
        assertEquals(appointment1, appointmentService.getAppointment("1"));
        assertEquals(appointment2, appointmentService.getAppointment("2"));

        // Delete first appointment
        appointmentService.deleteAppointment("1");

        // Make sure first is deleted and second still exists
        assertNull(appointmentService.getAppointment("1"));
        assertEquals(appointment2, appointmentService.getAppointment("2"));

        // Delete second appointment
        appointmentService.deleteAppointment("2");

        // Make sure both are deleted
        assertNull(appointmentService.getAppointment("1"));
        assertNull(appointmentService.getAppointment("2"));
    }
    
    @Test
    void testAddAppointmentWithPastDate() {
    	// Get a date in the past
        Date pastDate = getPastDate(1); 

        // Attempt to add an appointment with a past date
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234", pastDate, "Description", "C123");
        });
    }
    
    @Test 
    void testUpdateAppointment() {
    	 Date futureDate = getFutureDate(1);
         Appointment appointment = new Appointment("1234", futureDate, "Description", "C123");
         appointmentService.addAppointment(appointment);

         // Verify that the appointment was added successfully
         Appointment retrieved = appointmentService.getAppointment("1234");
         assertEquals(appointment, retrieved);
         
      // Create an updated version
         Appointment updatedAppointment = new Appointment("1234", futureDate, "Updated Description", "C123");

         // Perform the update
         appointmentService.update("1234", updatedAppointment);

         // Retrieve and verify
         Appointment result = appointmentService.getAppointment("1234");
         assertNotNull(result);
         assertEquals("Updated Description", result.getDescription());
         assertEquals(futureDate, result.getDate());
         assertEquals("C123", result.getContactID());
    }
    
    @Test
    void testUpdateNonExistentAppointment() {
        // Attempt to update an appointment that doesn't exist
        Date futureDate = getFutureDate(1);
        Appointment updatedAppointment = new Appointment("321", futureDate, "Description", "C123");

        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.update("321", updatedAppointment);
        });
    }

    @Test
    void testUpdateAppointmentWithNullValues() {
        Date futureDate = getFutureDate(1);
        Appointment appointment = new Appointment("123", futureDate, "Description", "C123");
        appointmentService.addAppointment(appointment);

        // Passing null updated appointment
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.update("123", null);
        });

        // Passing null ID
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.update(null, appointment);
        });
    }
    
    @Test
    void testAddAppointmentWithNonExistentContact() {
        Date futureDate = getFutureDate(1);
        
        // Attempt to add an appointment with a contact ID that doesn't exist
        Appointment appointment = new Appointment("123", futureDate, "Description", "C000");
        
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment(appointment);
        });
    }
    
    @Test
    void testSaveToFileAndLoadFromFile() {
    	Date futureDate = getFutureDate(1);
        Appointment appointment1 = new Appointment("1", futureDate, "Description 1", "C123");
        
        appointmentService.addAppointment(appointment1);
        
        AppointmentService loadedService = new AppointmentService(contactService);
        
        assertNotNull(loadedService.getAppointment("1"));

        assertEquals("Description 1", loadedService.getAppointment("1").getDescription());
    }


}
