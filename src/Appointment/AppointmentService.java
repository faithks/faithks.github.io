package Appointment;

import java.util.HashMap;
import java.util.Map;

import Contact.ContactService;

import java.io.*;
import java.util.*;

public class AppointmentService {
    private final Map<String, Appointment> appointments = new HashMap<>();
    private final String FILE_PATH = "appointments.csv";
    private final ContactService contactService;
    
    public AppointmentService(ContactService contactService) {
    	this.contactService = contactService;
    	loadFromFile();
    }

    // Method to add a new appointment
    public void addAppointment(Appointment appointment) {
        // Validate Appointment
    	if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null.");
        }
    	if (!contactService.exists(appointment.getContactID())) {
            throw new IllegalArgumentException("Appointment must be associated with an existing contact.");
        }
        if (appointments.containsKey(appointment.getAppointmentID())) {
            throw new IllegalArgumentException("Appointment ID already exists.");
        }
        // Adding
        appointments.put(appointment.getAppointmentID(), appointment);
        saveToFile();
    }

    // Method to delete an appointment by Appointment ID
    public void deleteAppointment(String appointmentID) {
        // Search by appointmentID
    	if (appointmentID == null || !appointments.containsKey(appointmentID)) {
            throw new IllegalArgumentException("No appointment found with ID.");
        }
        // Removing appointment
        appointments.remove(appointmentID);
        saveToFile();
    }
    
    // Method to update an appointment's fields
    public void update(String appointmentID, Appointment updated) {
    	if (appointmentID == null || updated == null) {
    		throw new IllegalArgumentException("Appointment ID and/or updated appointment cannot be null.");
    	}
    	Appointment existingAppointment = appointments.get(appointmentID);
    	if (existingAppointment == null) {
    		throw new IllegalArgumentException("No appointment found with ID.");
    	}
    	
    	// Updating fields
    	existingAppointment.setDescription(updated.getDescription());
        
    	saveToFile();
    }
    
    private void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (Appointment a : appointments.values()) {
                pw.println(a.getAppointmentID() + "," +
                           a.getDate().getTime() + "," +
                           a.getDescription() + "," +
                           a.getContactID());
            }
        } catch (IOException e) {
            System.out.println("\u001B[31mError saving appointments: " + e.getMessage() + "\u001B[0m");
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
                    String id = parts[0];
                    Date date = new Date(Long.parseLong(parts[1]));
                    String desc = parts[2];
                    String contactID = parts[3];
                    Appointment a = new Appointment(id, date, desc, contactID);
                    appointments.put(id, a);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("\u001B[31mError loading appointments: " + e.getMessage() + "\u001B[0m");
        }
    }

    // Method to retrieve an appointment by ID (for testing purposes or later use)
    public Appointment getAppointment(String appointmentID) {
        return appointments.get(appointmentID); // Returns null if not found
    }
}
