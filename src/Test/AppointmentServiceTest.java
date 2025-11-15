package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Appointment.Appointment;
import Appointment.AppointmentService;

import java.util.Date;
import java.util.Calendar;

class AppointmentServiceTest {
    private AppointmentService appointmentService;

 // Method to get a future date
    private Date getFutureDate(int daysAhead) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, daysAhead); // adds the number of days to current date
        return cal.getTime();
    }
    
 // Method to get a past date
    private Date getPastDate(int daysAgo) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -daysAgo); // subtracts the number of days from current date
        return cal.getTime();
    }

    @BeforeEach
    void setUp() {
        appointmentService = new AppointmentService();
    }

    @Test
    void testAddAppointment() {
        Date futureDate = getFutureDate(1);
        Appointment appointment = new Appointment("1234", futureDate, "Description");
        appointmentService.addAppointment(appointment);

        // Verify that the appointment was added successfully
        Appointment retrieved = appointmentService.getAppointment("1234");
        assertEquals(appointment, retrieved);
    }

    @Test
    void testAddDuplicateAppointment() {
        Date futureDate = getFutureDate(1);
        Appointment appointment1 = new Appointment("1234", futureDate, "Description");
        appointmentService.addAppointment(appointment1);

        //Attempting to add duplicate appointment ID
        Appointment appointment2 = new Appointment("1234", futureDate, "Description 2"); // Same ID
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment(appointment2);
        });
    }

    @Test
    void testDeleteAppointment() {
        Date futureDate = getFutureDate(1);
        Appointment appointment = new Appointment("12345", futureDate, "Description");
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
        Appointment appointment1 = new Appointment("1", futureDate, "First Description");
        Appointment appointment2 = new Appointment("2", futureDate, "Second Description");

        appointmentService.addAppointment(appointment1);
        appointmentService.addAppointment(appointment2);

        // Verify both appointments were added
        assertEquals(appointment1, appointmentService.getAppointment("1"));
        assertEquals(appointment2, appointmentService.getAppointment("2"));
    }

    @Test
    void testAppointmentCreationAndRetrieval() {
        Date futureDate = getFutureDate(1);
        Appointment appointment = new Appointment("246", futureDate, "Description");
        appointmentService.addAppointment(appointment);

        // Test the integration between Appointment and AppointmentService
        Appointment retrievedAppointment = appointmentService.getAppointment("246");
        assertNotNull(retrievedAppointment);
        assertEquals("246", retrievedAppointment.getAppointmentID());
        assertEquals("Description", retrievedAppointment.getApptDescription());
        assertEquals(futureDate, retrievedAppointment.getAppointmentDate());
    }
    
    @Test
    void testAddAndDeleteMultipleAppointments() {
        Date futureDate = getFutureDate(1);
        Appointment appointment1 = new Appointment("1", futureDate, "Description 1");
        Appointment appointment2 = new Appointment("2", futureDate, "Description 2");
        
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
        Date pastDate = getPastDate(1); // Get a date in the past

        // Attempt to add an appointment with a past date
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("1234", pastDate, "Description");
        });
    }


}
