/*
 *  CS 320 
 *  This program creates contacts, appointments, and tasks 
 *  Each service has multiple variables which are all validated
 *  Each item and services is tested using JUnit testing
 *  Programmer: Faith Sheppard
 *  Code to use for Artifact 1 Category 1
 */

package Appointment;

import java.util.Date;

public class Appointment {
    private final String appointmentID; // Cannot be modified after initialization
    private final Date date;
    private String description;
    private final String contactID;

    // Constructor with validation
    public Appointment(String appointmentID, Date date, String description, String contactID) {
        // Validate appointmentID
        if (appointmentID == null || appointmentID.isEmpty() || appointmentID.length() > 10) {
            throw new IllegalArgumentException("Appointment ID must not be null, empty, and must be 10 characters or less.");
        }

        // Validate appointmentDate
        if (date == null) {
            throw new IllegalArgumentException("Date must not be null.");
        }
        if (date.before(new Date())) {
            throw new IllegalArgumentException("Appointment date cannot be in the past.");
        }

        // Validate description
        if (description == null || description.isEmpty() || description.length() > 50) {
            throw new IllegalArgumentException("The appointment�s description must not be null, empty, and must be 50 characters or less.");
        }
        
        // Validate contactID
        if (contactID == null || contactID.isEmpty()) {
        	throw new IllegalArgumentException("Appointment must be associated with a contact.");
        }

        this.appointmentID = appointmentID;
        this.date = date;
        this.description = description;
        this.contactID = contactID;
    }

    // Getters
    public String getAppointmentID() {
        return appointmentID;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
    
    public String getContactID() {
    	return contactID;
    }

    // Setters with validation
    public void setDescription(String Description) {
        if (Description == null || Description.isEmpty() || Description.length() > 50) {
            throw new IllegalArgumentException("The appointment�s description must not be null, empty, and must be 50 characters or less.");
        }
        this.description = Description;
    }

    // No setter for appointmentID, Date, or contactID to keep them immutable
}
