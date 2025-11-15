package Appointment;

import java.util.HashMap;
import java.util.Map;

public class AppointmentService {
    private final Map<String, Appointment> appointments = new HashMap<>();

    // Method to add a new appointment
    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null.");
        }
        if (appointments.containsKey(appointment.getAppointmentID())) {
            throw new IllegalArgumentException("Appointment ID already exists.");
        }
        // Adding
        appointments.put(appointment.getAppointmentID(), appointment);
    }

    // Method to delete an appointment by Appointment ID
    public void deleteAppointment(String appointmentID) {
        if (appointmentID == null || !appointments.containsKey(appointmentID)) {
            throw new IllegalArgumentException("No appointment found with ID.");
        }
        // Removing appointment
        appointments.remove(appointmentID);
    }

    // Method to retrieve an appointment by ID (for testing purposes or later use)
    public Appointment getAppointment(String appointmentID) {
        return appointments.get(appointmentID); // Returns null if not found
    }
}
